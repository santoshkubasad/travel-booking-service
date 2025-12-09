package com.travel.booking.controller; 
 
import com.travel.booking.dto.WebhookPaymentRequest; 
import com.travel.booking.service.BookingService; 
import io.swagger.v3.oas.annotations.Operation; 
import io.swagger.v3.oas.annotations.tags.Tag; 
import jakarta.validation.Valid; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.*; 
 
@RestController 
@RequestMapping("/webhooks") 
@Tag(name = "Webhook Endpoints", description = "Webhook endpoints for payment notifications") 
public class WebhookController { 
 
    @Autowired 
    private BookingService bookingService; 
 
    @PostMapping("/payments") 
    @Operation(summary = "Receive payment confirmation webhook") 
    public ResponseEntity<String> receivePaymentWebhook(@Valid @RequestBody WebhookPaymentRequest request) { 
        try { 
          String status =  bookingService.processPaymentWebhook(request); 
            return ResponseEntity.ok(status); 
        } catch (Exception e) { 
            return ResponseEntity.badRequest().body("Error processing payment: " + e.getMessage()); 
        } 
    } 
}
