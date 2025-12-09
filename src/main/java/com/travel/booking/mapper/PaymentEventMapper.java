package com.travel.booking.mapper;

import com.travel.booking.dto.PaymentEventResponse;
import com.travel.booking.dto.WebhookPaymentRequest;
import com.travel.booking.model.PaymentEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaymentEventMapper {

    @Mapping(source = "transactionId", target = "paymentTransactionId")
    @Mapping(source = "bookingId", target = "associatedBookingId")
    @Mapping(source = "amountPaid", target = "paidAmount")
    @Mapping(source = "paymentTime", target = "transactionTimestamp")
    @Mapping(target = "eventId", ignore = true)
    @Mapping(target = "eventCreatedAt", ignore = true)
    PaymentEvent toEntity(WebhookPaymentRequest request);

    @Mapping(source = "eventId", target = "id")
    @Mapping(source = "paymentTransactionId", target = "transactionId")
    @Mapping(source = "associatedBookingId", target = "bookingId")
    @Mapping(source = "paidAmount", target = "amountPaid")
    @Mapping(source = "transactionTimestamp", target = "paymentTime")
    @Mapping(source = "eventCreatedAt", target = "createdAt")
    PaymentEventResponse toResponse(PaymentEvent paymentEvent);
}