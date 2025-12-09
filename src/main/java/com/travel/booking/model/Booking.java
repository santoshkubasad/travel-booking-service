package com.travel.booking.model; 
 
import jakarta.persistence.*; 
import java.math.BigDecimal; 
import java.time.LocalDateTime; 
 
@Entity 
@Table(name = "bookings") 
public class Booking { 
    @Id
    @Column(nullable = false) 
    private String bookingReference; 
 
    @Column(nullable = false) 
    private String clientName; 
 
    @Column(nullable = false) 
    private String travelDestination; 
 
    @Column(nullable = false) 
    private BigDecimal totalAmount; 
 
    @Enumerated(EnumType.STRING) 
    private PaymentStatus currentPaymentStatus = PaymentStatus.PENDING; 
 
    private LocalDateTime recordCreatedAt = LocalDateTime.now(); 
 
    // Constructors 
    public Booking() {} 
 
    // Complete getters and setters
    public String getBookingReference() { return bookingReference; } 
    public void setBookingReference(String bookingReference) { this.bookingReference = bookingReference; } 
 
    public String getClientName() { return clientName; } 
    public void setClientName(String clientName) { this.clientName = clientName; } 
 
    public String getTravelDestination() { return travelDestination; } 
    public void setTravelDestination(String travelDestination) { this.travelDestination = travelDestination; } 
 
    public BigDecimal getTotalAmount() { return totalAmount; } 
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; } 
 
    public PaymentStatus getCurrentPaymentStatus() { return currentPaymentStatus; } 
    public void setCurrentPaymentStatus(PaymentStatus currentPaymentStatus) { this.currentPaymentStatus = currentPaymentStatus; } 
 
    public LocalDateTime getRecordCreatedAt() { return recordCreatedAt; } 
    public void setRecordCreatedAt(LocalDateTime recordCreatedAt) { this.recordCreatedAt = recordCreatedAt; } 
}