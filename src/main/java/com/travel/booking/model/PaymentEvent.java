package com.travel.booking.model; 
 
import jakarta.persistence.*; 
import java.math.BigDecimal; 
import java.time.LocalDateTime; 
 
@Entity 
@Table(name = "payment_events") 
public class PaymentEvent { 
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long eventId; 
 
    @Column(unique = true, nullable = false) 
    private String paymentTransactionId; 
 
    @Column(nullable = false) 
    private String associatedBookingId; 
 
    @Column(nullable = false) 
    private BigDecimal paidAmount; 
 
    @Column(nullable = false) 
    private LocalDateTime transactionTimestamp; 
 
    private LocalDateTime eventCreatedAt = LocalDateTime.now(); 
 
    // Constructors 
    public PaymentEvent() {} 
 
    // Complete getters and setters 
    public Long getEventId() { return eventId; } 
    public void setEventId(Long eventId) { this.eventId = eventId; } 
 
    public String getPaymentTransactionId() { return paymentTransactionId; } 
    public void setPaymentTransactionId(String paymentTransactionId) { this.paymentTransactionId = paymentTransactionId; } 
 
    public String getAssociatedBookingId() { return associatedBookingId; } 
    public void setAssociatedBookingId(String associatedBookingId) { this.associatedBookingId = associatedBookingId; } 
 
    public BigDecimal getPaidAmount() { return paidAmount; } 
    public void setPaidAmount(BigDecimal paidAmount) { this.paidAmount = paidAmount; } 
 
    public LocalDateTime getTransactionTimestamp() { return transactionTimestamp; } 
    public void setTransactionTimestamp(LocalDateTime transactionTimestamp) { this.transactionTimestamp = transactionTimestamp; } 
 
    public LocalDateTime getEventCreatedAt() { return eventCreatedAt; } 
    public void setEventCreatedAt(LocalDateTime eventCreatedAt) { this.eventCreatedAt = eventCreatedAt; } 
}