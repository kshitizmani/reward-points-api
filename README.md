# Reward Points Calculation API

This is a **Spring Boot-based Web API** that calculates reward points for customers based on their monthly spending. The API computes reward points using the following rules:

- **2 points** for every dollar spent **over $100** in a single transaction.
- **1 point** for every dollar spent **between $50 and $100** in a single transaction.

---

## **Tech Stack**
- **Java** 
- **Spring Boot**
- **Spring Data JPA**
- **H2 Database** (in-memory)
- **Maven** (for build and dependency management)
- **JUnit & Mockito** (Testing)
- **GitHub** (Version Control)
---

## **Access H2 Console**:
- URL → [http://localhost:8090/h2-console](http://localhost:8090/h2-console)  
- JDBC URL → `jdbc:h2:mem:testdb`  
- Username → `sa`  
- Password → `password`  

---

## **Reward Points Calculation Example**
### Formula:
- Spend `$120` → `2 x $20` (for amount over $100) + `1 x $50` (for amount between $50 and $100)  
**= 90 points**

---

## **API Endpoints**
### 1. **Get Reward Points by Customer**
`GET /customers/{customerId}`  

**Example Request**:
```http
GET http://localhost:8090/customers/CUST1
```

**Example Response**:
```json
{
  "customerId": "CUST1",
  "pointsPerMonth": {
    "2025-01": 90,
    "2025-02": 30,
    "2025-03": 0
  },
  "totalPoints": 120
}
```

---

## **Project Structure**
```
src
├── main
│   ├── java
│   │   └── com.charter.assignment
│   │       ├── controller
│   │       ├── entity
│   │       ├── repository
│   │       ├── service
│   │       └── dto
│   └── resources
│       ├── application.properties
├── test
│   └── java
│       └── com.charter.assignment
            ├── service
            ├── controller
└── README.md

```

---

## **Testing**


### **Sample Output (Success)**
```json
{
  "customerId": "CUST1",
  "pointsPerMonth": {
    "2025-01": 90,
    "2025-02": 30,
    "2025-03": 0
  },
  "totalPoints": 120
}
```
