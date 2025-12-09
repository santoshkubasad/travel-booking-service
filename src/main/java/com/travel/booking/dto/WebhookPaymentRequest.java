package com.travel.booking.dto; 
 
import java.math.BigDecimal; 
import java.time.LocalDateTime; 
 
public class WebhookPaymentRequest { 
    private String transactionId; 
    private String bookingId; 
    private BigDecimal amountPaid; 
    private LocalDateTime paymentTime; 
 
    // Getters and setters 
    public String getTransactionId() { return transactionId; } 
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; } 
    public String getBookingId() { return bookingId; } 
    public void setBookingId(String bookingId) { this.bookingId = bookingId; } 
    public BigDecimal getAmountPaid() { return amountPaid; } 
    public void setAmountPaid(BigDecimal amountPaid) { this.amountPaid = amountPaid; } 
    public LocalDateTime getPaymentTime() { return paymentTime; } 
    public void setPaymentTime(LocalDateTime paymentTime) { this.paymentTime = paymentTime; } 
}
