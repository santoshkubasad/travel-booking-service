package com.travel.booking; 
 
import com.travel.booking.dto.BookingResponse;
import com.travel.booking.dto.CreateBookingRequest; 
import com.travel.booking.mapper.BookingMapper;
import com.travel.booking.mapper.PaymentEventMapper;
import com.travel.booking.model.Booking; 
import com.travel.booking.model.PaymentStatus; 
import com.travel.booking.repository.BookingRepository; 
import com.travel.booking.repository.PaymentEventRepository; 
import com.travel.booking.service.BookingService; 
import org.junit.jupiter.api.Test; 
import org.junit.jupiter.api.extension.ExtendWith; 
import org.mockito.InjectMocks; 
import org.mockito.Mock; 
import org.mockito.junit.jupiter.MockitoExtension; 
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*; 
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when; 
 
@ExtendWith(MockitoExtension.class) 
public class BookingServiceTest { 
 
    @Mock 
    private BookingRepository bookingRepository; 
 
    @Mock 
    private PaymentEventRepository paymentEventRepository; 

    @Mock
    private BookingMapper bookingMapper;

    @Mock
    private PaymentEventMapper paymentEventMapper;
 
    @InjectMocks 
    private BookingService bookingService; 
 
    @Test 
    public void testCreateBooking() { 
        CreateBookingRequest request = new CreateBookingRequest(); 
        request.setBookingId("BK001"); 
        request.setCustomerName("Santosh"); 
        request.setDestination("Prague"); 
        request.setAmountDue(BigDecimal.valueOf(500.00)); 
 
        Booking entity = new Booking(); 
        entity.setBookingReference("BK001"); 
        entity.setCurrentPaymentStatus(PaymentStatus.PENDING); 

        Booking savedBooking = new Booking(); 
        savedBooking.setBookingReference("BK001"); 
        savedBooking.setCurrentPaymentStatus(PaymentStatus.PENDING); 

        BookingResponse expectedResponse = new BookingResponse();
        expectedResponse.setBookingId("BK001");
        expectedResponse.setPaymentStatus(PaymentStatus.PENDING);

        when(bookingMapper.toEntity(request)).thenReturn(entity);
        when(bookingRepository.save(any(Booking.class))).thenReturn(savedBooking); 
        when(bookingMapper.toResponse(savedBooking)).thenReturn(expectedResponse);
 
        BookingResponse result = bookingService.createBooking(request); 
 
        assertNotNull(result); 
        assertEquals("BK001", result.getBookingId()); 
        assertEquals(PaymentStatus.PENDING, result.getPaymentStatus()); 
    } 
    
    @Test
    public void testGetBookingByBookingId_Found() {
		String bookingId = "BK001";

		Booking booking = new Booking();
		booking.setBookingReference(bookingId);
		booking.setCurrentPaymentStatus(PaymentStatus.PAID);

		BookingResponse expectedResponse = new BookingResponse();
		expectedResponse.setBookingId(bookingId);
		expectedResponse.setPaymentStatus(PaymentStatus.PAID);

		when(bookingRepository.findByBookingReference(bookingId)).thenReturn(java.util.Optional.of(booking));
		when(bookingMapper.toResponse(booking)).thenReturn(expectedResponse);

		java.util.Optional<BookingResponse> result = bookingService.getBookingByBookingId(bookingId);

		assertTrue(result.isPresent());
		assertEquals(bookingId, result.get().getBookingId());
		assertEquals(PaymentStatus.PAID, result.get().getPaymentStatus());
	}
    
    @Test
    public void testGetBookingByBookingId_NotFound() {
        String bookingId = "BK002";
        
        when(bookingRepository.findByBookingReference(bookingId)).thenReturn(java.util.Optional.empty());
        
        java.util.Optional<BookingResponse> result = bookingService.getBookingByBookingId(bookingId);
        
        assertFalse(result.isPresent());
        assertTrue(result.isEmpty());
    }
    
    @Test
    public void testGetAllBookings_Success() {
        List<Booking> bookingEntities = Arrays.asList(
            createBookingEntity("BK001", PaymentStatus.PAID),
            createBookingEntity("BK002", PaymentStatus.PENDING)           
        );
        
               
        when(bookingRepository.findAll()).thenReturn(bookingEntities);        
        List<BookingResponse> result = bookingService.getAllBookings();
        
        assertNotNull(result);
        assertEquals(2, result.size());
      
    }
    
    private Booking createBookingEntity(String bookingId, PaymentStatus status) {
        Booking booking = new Booking();
        booking.setBookingReference(bookingId);
        booking.setCurrentPaymentStatus(status);
        booking.setClientName("Customer " + bookingId);
        booking.setTravelDestination("Destination " + bookingId);
        booking.setTotalAmount(BigDecimal.valueOf(500.00));
        return booking;
    }
    
    private BookingResponse createBookingResponse(String bookingId, PaymentStatus status) {
        BookingResponse response = new BookingResponse();
        response.setBookingId(bookingId);
        response.setPaymentStatus(status);
        response.setCustomerName("Customer " + bookingId);
        response.setDestination("Destination " + bookingId);
        response.setAmountDue(BigDecimal.valueOf(500.00));
        return response;
    }

   
   
}