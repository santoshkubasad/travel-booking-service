package com.travel.booking.mapper;

import com.travel.booking.dto.BookingResponse;
import com.travel.booking.dto.CreateBookingRequest;
import com.travel.booking.model.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookingMapper {

    @Mapping(source = "bookingId", target = "bookingReference")
    @Mapping(source = "customerName", target = "clientName")
    @Mapping(source = "destination", target = "travelDestination")
    @Mapping(source = "amountDue", target = "totalAmount")
    @Mapping(target = "currentPaymentStatus", ignore = true)
    @Mapping(target = "recordCreatedAt", ignore = true)
    Booking toEntity(CreateBookingRequest request);

    @Mapping(source = "bookingReference", target = "bookingId")
    @Mapping(source = "clientName", target = "customerName")
    @Mapping(source = "travelDestination", target = "destination")
    @Mapping(source = "totalAmount", target = "amountDue")
    @Mapping(source = "currentPaymentStatus", target = "paymentStatus")
    @Mapping(source = "recordCreatedAt", target = "createdAt")
    BookingResponse toResponse(Booking booking);
}