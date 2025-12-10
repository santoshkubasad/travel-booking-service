package com.travel.booking.dto; 
 
import jakarta.validation.constraints.NotBlank; 
import jakarta.validation.constraints.NotNull; 
import jakarta.validation.constraints.Positive; 
import java.math.BigDecimal; 
 
public class CreateBookingRequest { 
    @NotBlank(message = "Booking ID cannot be null or empty") 
    private String bookingId; 
 
    @NotBlank(message = "Customer name cannot be null or empty") 
    private String customerName; 
 
    @NotBlank(message = "Destination cannot be null or empty") 
    private String destination; 
 
    @NotNull(message = "Amount due cannot be null") 
    @Positive(message = "Amount due must be greater than zero") 
    private BigDecimal amountDue; 
 
    // Getters and setters 
    public String getBookingId() { return bookingId; } 
    public void setBookingId(String bookingId) { this.bookingId = bookingId; } 
    public String getCustomerName() { return customerName; } 
    public void setCustomerName(String customerName) { this.customerName = customerName; } 
    public String getDestination() { return destination; } 
    public void setDestination(String destination) { this.destination = destination; } 
    public BigDecimal getAmountDue() { return amountDue; } 
    public void setAmountDue(BigDecimal amountDue) { this.amountDue = amountDue; } 
}
