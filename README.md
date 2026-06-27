# Moon-Cart

A microservices-based full-stack e-commerce backed platform build using java, Spring Boot, and Spring cloud. The project implements a functional API Gateway routing pattern to seamlessly direct incoming traffic to varous downstream microservices.

##  Architecture Overview

`Moon-Cart` leverages a microservices architectural style to separate domine boundaries and ensure independent scalability.

* **API Gateway (`api-gateway-server`)**: Acts as the single entry point for all client requests. Configured using Spring Cloud Gateway's Functional MVC routing API to handle routing, request interception, dynamic logging, and load balancing.
* **Discovery Service (`discovery-server`)**: It acts as a central service registry where all microservices (`product-service`, etc.) register themselves dynamically at startup, allowing for seamless service-to-service communication without hardcoded URLs.
* **Product Service (`product-server`)**: Handles product catalogs, inventory item listings, and specific metadata adjustments.
* **Inventory Service (`inventory-server`)**: Manages real-time stock levels, tracking availability across incoming purchase patterns.

---

## Tech Stack

* **Backend Core**: Java 17+, Spring Boot
* **Microservices Infrastructure**: Spring Cloud Gateway, Netflix Eureka (Discovery server)
* **Database & Storage**: Mongo DB
* **Devops & Containerization**: Docker

---

## Getting Started

### Prerequisites
* Java Development Kit (JDK) 17 or higher
* Maven 3.8+
* Docker

### Local Installation

1. **Clone the repository:**
   ```bash 
   git clone [https://github.com/praveenKavali/Moon-cart.git](https://github.com/praveenKavali/Moon-cart.git)
   ```
   cd Moon-cart
