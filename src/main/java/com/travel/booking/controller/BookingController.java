package com.travel.booking.controller; 
 
import com.travel.booking.dto.BookingResponse;
import com.travel.booking.dto.CreateBookingRequest; 
import com.travel.booking.dto.PaymentEventResponse;
import com.travel.booking.service.BookingService; 
import io.swagger.v3.oas.annotations.Operation; 
import io.swagger.v3.oas.annotations.tags.Tag; 
import jakarta.validation.Valid; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.*; 
import java.util.List; 
 
@RestController 
@RequestMapping("/api/bookings") 
@Tag(name = "Booking Management", description = "APIs for managing travel bookings") 
public class BookingController { 
 
    @Autowired 
    private BookingService bookingService; 
 
    @PostMapping 
    @Operation(summary = "Create a new booking") 
    public ResponseEntity<BookingResponse> createBooking(@Valid @RequestBody CreateBookingRequest request) { 
        BookingResponse booking = bookingService.createBooking(request); 
        return ResponseEntity.ok(booking); 
    } 
 
    @GetMapping("/{bookingId}") 
    @Operation(summary = "Get booking by ID") 
    public ResponseEntity<BookingResponse> getBooking(@PathVariable String bookingId) { 
        return bookingService.getBookingByBookingId(bookingId) 
                .map(ResponseEntity::ok) 
                .orElse(ResponseEntity.notFound().build()); 
    } 
 
    @GetMapping 
    @Operation(summary = "List all bookings") 
    public ResponseEntity<List<BookingResponse>> getAllBookings() { 
        List<BookingResponse> bookings = bookingService.getAllBookings(); 
        return ResponseEntity.ok(bookings); 
    } 
 
    @GetMapping("/{bookingId}/payments") 
    @Operation(summary = "View payment events for a booking") 
    public ResponseEntity<List<PaymentEventResponse>> getPaymentEvents(@PathVariable String bookingId) { 
        List<PaymentEventResponse> events = bookingService.getPaymentEventsByBookingId(bookingId); 
        return ResponseEntity.ok(events); 
    } 
}