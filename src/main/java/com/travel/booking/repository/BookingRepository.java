package com.travel.booking.repository; 
 
import com.travel.booking.model.Booking; 
import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository; 
import java.util.Optional; 
 
@Repository 
public interface BookingRepository extends JpaRepository<Booking, String> { 
    Optional<Booking> findByBookingReference(String bookingReference); 
}