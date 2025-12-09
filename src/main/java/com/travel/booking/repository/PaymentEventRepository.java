package com.travel.booking.repository; 
 
import com.travel.booking.model.PaymentEvent; 
import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository; 
import java.math.BigDecimal;
import java.util.List; 
 
@Repository 
public interface PaymentEventRepository extends JpaRepository<PaymentEvent, Long> { 
    List<PaymentEvent> findByAssociatedBookingIdOrderByEventCreatedAtDesc(String associatedBookingId); 
    boolean existsByPaymentTransactionId(String paymentTransactionId); 
    
    @Query("SELECT COALESCE(SUM(pe.paidAmount), 0) FROM PaymentEvent pe WHERE pe.associatedBookingId = :bookingId")
    BigDecimal sumTotalPaidAmountByBookingId(@Param("bookingId") String bookingId);
}