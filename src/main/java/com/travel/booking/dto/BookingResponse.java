package com.travel.booking.dto;

import com.travel.booking.model.PaymentStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BookingResponse {
    private String bookingId;
    private String customerName;
    private String destination;
    private BigDecimal amountDue;
    private PaymentStatus paymentStatus;
    private LocalDateTime createdAt;

    // Constructors
    public BookingResponse() {}

    // Getters and setters
    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public BigDecimal getAmountDue() { return amountDue; }
    public void setAmountDue(BigDecimal amountDue) { this.amountDue = amountDue; }

    public PaymentStatus getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(PaymentStatus paymentStatus) { this.paymentStatus = paymentStatus; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}