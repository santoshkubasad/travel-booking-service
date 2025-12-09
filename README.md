# travel-booking-service

 
## Applications 
 
### 1. Travel Booking Service (Port 8080) 
- Manages travel bookings 
- Receives payment confirmations via webhook 
- Updates booking payment status 
- Exposes authenticated REST APIs 
- Swagger UI: http://localhost:8080/swagger-ui/index.html
  
## Prerequisites 
 
- Java 17 or higher 
- Maven 3.6+ 
 
## Running the Applications 
 
### Step 1: Start Travel Booking Service 
``` 
cd travel-booking-service 
mvn spring-boot:run 
``` 
 

 
## API Endpoints 
 
### Travel Booking Service (http://localhost:8080) 
 
#### Booking Management 
- **POST /api/bookings** - Create a new booking 
- **GET /api/bookings/{bookingId}** - Retrieve booking by ID 
- **GET /api/bookings** - List all bookings 
- **GET /api/bookings/{bookingId}/payments** - View payment events 
 
#### Webhook Endpoint 
- **POST /webhooks/payments** - Receive payment confirmations 
 
  
## Authentication 
 
Both applications use Basic Authentication: 
- Username: santosh 
- Password: password123
 
## Testing the Webhook Communication 
 
### Create a Booking 
```
curl -X POST http://localhost:8080/api/bookings \ 
  -H "Content-Type: application/json" \ 
  -H "Authorization: Basic c2FudG9zaDpwYXNzd29yZDEyMw==" \ 
  -d "{" 
  "\"bookingId\": \"BK001\"," 
  "\"customerName\": \"Santosh K\"," 
  "\"destination\": \"Prague\"," 
  "\"amountDue\": 500.00" 
  "}" 
``` 
 
 
### Check Booking Status 
``` 
curl -X GET http://localhost:8080/api/bookings/BK001 \ 
  -H "Authorization: Basic c2FudG9zaDpwYXNzd29yZDEyMw==" 
``` 
 
### View Payment Events 
``` 
curl -X GET http://localhost:8080/api/bookings/BK001/payments \ 
  -H "Authorization: Basic c2FudG9zaDpwYXNzd29yZDEyMw==" 
``` 

### All Bookings
``` 
curl -X GET http://localhost:8080/api/bookings \ 
  -H "Authorization: Basic c2FudG9zaDpwYXNzd29yZDEyMw==" 
``` 
 
## Database Access 
 
The Travel Booking Service uses H2 in-memory database. 
Access H2 Console: http://localhost:8080/h2-console 
- JDBC URL: jdbc:h2:mem:bookingdb 
- Username: sa 
- Password: (empty) 
 
 

 

