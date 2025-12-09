package com.travel.booking.exception;

public class BookingAlreadyExistsException extends RuntimeException {
    
    public BookingAlreadyExistsException(String bookingId) {
        super("Booking with ID '" + bookingId + "' already exists in the database");
    }
    
    public BookingAlreadyExistsException(String bookingId, Throwable cause) {
        super("Booking with ID '" + bookingId + "' already exists in the database", cause);
    }
}