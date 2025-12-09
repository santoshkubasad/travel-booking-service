package com.travel.booking.service; 
 
import com.travel.booking.dto.BookingResponse;
import com.travel.booking.dto.CreateBookingRequest; 
import com.travel.booking.dto.PaymentEventResponse;
import com.travel.booking.dto.WebhookPaymentRequest; 
import com.travel.booking.exception.BookingAlreadyExistsException;
import com.travel.booking.mapper.BookingMapper;
import com.travel.booking.mapper.PaymentEventMapper;
import com.travel.booking.model.Booking; 
import com.travel.booking.model.PaymentEvent; 
import com.travel.booking.model.PaymentStatus; 
import com.travel.booking.repository.BookingRepository; 
import com.travel.booking.repository.PaymentEventRepository; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Transactional; 
import java.util.List; 
import java.util.Optional; 
import java.util.stream.Collectors;
import java.math.BigDecimal;
 
@Service 
public class BookingService { 
 
    @Autowired 
    private BookingRepository bookingRepository; 
 
    @Autowired 
    private PaymentEventRepository paymentEventRepository; 

    @Autowired
    private BookingMapper bookingMapper;

    @Autowired
    private PaymentEventMapper paymentEventMapper;
 
    public BookingResponse createBooking(CreateBookingRequest request) {
        // Check if booking with the same booking reference already exists
        if (bookingRepository.findByBookingReference(request.getBookingId()).isPresent()) {
            throw new BookingAlreadyExistsException(request.getBookingId());
        }
        
        Booking booking = bookingMapper.toEntity(request);
        booking.setCurrentPaymentStatus(PaymentStatus.PENDING);
        Booking savedBooking = bookingRepository.save(booking); 
        return bookingMapper.toResponse(savedBooking);
    } 
 
    public Optional<BookingResponse> getBookingByBookingId(String bookingId) { 
        return bookingRepository.findByBookingReference(bookingId)
            .map(bookingMapper::toResponse);
    } 
 
    public List<BookingResponse> getAllBookings() { 
        return bookingRepository.findAll().stream()
            .map(bookingMapper::toResponse)
            .collect(Collectors.toList());
    } 
 
    public List<PaymentEventResponse> getPaymentEventsByBookingId(String bookingId) { 
        return paymentEventRepository.findByAssociatedBookingIdOrderByEventCreatedAtDesc(bookingId)
            .stream()
            .map(paymentEventMapper::toResponse)
            .collect(Collectors.toList());
    } 
 
    @Transactional 
    public String processPaymentWebhook(WebhookPaymentRequest request) { 
        // Check for duplicate transaction 
        if (paymentEventRepository.existsByPaymentTransactionId(request.getTransactionId())) { 
            return "TransactionId: "+request.getTransactionId()+ " already exists"; // Idempotency - ignore duplicate 
        } 
        
        if(bookingRepository.findByBookingReference(request.getBookingId()).isEmpty()) {
			return "BookingId: "+request.getBookingId()+ " does not exist"; // Ignore payments for non-existent bookings
		}
 
        // Store payment event using MapStruct mapper
        PaymentEvent event = paymentEventMapper.toEntity(request);
        paymentEventRepository.save(event); 
 
        // Update booking payment status based on total payments received 
        Optional<Booking> bookingOpt = bookingRepository.findByBookingReference(request.getBookingId()); 
        if (bookingOpt.isPresent()) { 
            Booking booking = bookingOpt.get(); 
            // Calculate total amount paid including this payment
            BigDecimal totalPaidAmount = paymentEventRepository.sumTotalPaidAmountByBookingId(request.getBookingId());
            
            if (totalPaidAmount.compareTo(booking.getTotalAmount()) >= 0) { 
                booking.setCurrentPaymentStatus(PaymentStatus.PAID); 
                bookingRepository.save(booking); 
            } 
        } 
        return "payment processed";
    } 
}