## Overview of this:
This service implements the core ingestion and analytics layer for a high-scale EV
fleet platform. The system is designed to ingest real-time telemetry from thousands
of smart meters and electric vehicles, persist the data efficiently, and provide
fast analytical insights into energy efficiency and vehicle performance.

The design focuses on handling write-heavy workloads (millions of telemetry records
per day) while supporting low-latency analytical queries without performing full
table scans on historical data.

## Problem Context
The platform consumes two independent telemetry streams that arrive every 60 seconds
from each device:

### 1. Smart Meter (Grid Side)
- Measures AC power drawn from the utility grid
- Represents total billable energy

Sample Fields
- meterId
- kwhConsumedAc
- voltage
- timestamp

### 2. Vehicle / Charger (Vehicle Side)
- Measures DC power delivered to the battery
- Represents actual stored energy

Sample Fields
- vehicleId
- soc (State of Charge)
- kwhDeliveredDc
- batteryTemp
- timestamp

In real-world systems, AC energy consumed is always higher than DC energy delivered
due to conversion losses. A sustained drop in efficiency can indicate hardware
faults or energy leakage.

## Architecture Overview

The system follows a layered backend architecture:

- API Layer: Telemetry ingestion and analytics endpoints
- Service Layer: Validation, routing, and aggregation logic (for this given implemetation in impl)
- Persistence Layer: Optimized data storage for hot and cold paths
- Database: MySQL (relational, indexed, write-optimized)

The architecture is framework-agnostic and focuses on scalability, correctness,
and operational efficiency.

## Data Storage Strategy

### Hot Store of this application
Purpose:
- Fast access to current vehicle and meter state
- Dashboard and real-time monitoring use cases

Characteristics:
- One row per device
- Frequently updated
- Accessed via primary key

Operations:
- UPSERT (insert or update on conflict)

Example tables:
- vehicle_live_status
- meter_live_status

### Cold Store
Purpose:
- Long-term analytics and auditing
- Time-based reporting

Characteristics:
- Append-only
- Designed to handle billions of records over time
- Optimized for time-bounded queries

Operations:
- INSERT only

Example tables:
- vehicle_telemetry_history
- meter_telemetry_history

---

## Persistence Strategy

The persistence logic is intentionally split based on data temperature:

Data Type: Live State & Telemetry History
Storage: Hot Store & Cold Store
Operation: UPSERT & INSERT


This approach prevents expensive scans on large historical tables when querying
current device state.

## Analytics Design
For simplicity, vehicleId is assumed to have a 1:1 mapping with meterId for analytics correlation.

### Endpoint
GET /v1/analytics/performance/{vehicleId}

### Metrics (Last 24 Hours)
- Total AC energy consumed
- Total DC energy delivered
- Energy efficiency ratio (DC / AC)
- Average battery temperature

### Performance Considerations
- Time-bounded queries using indexed timestamps
- Vehicle-specific filtering
- Aggregations executed at the database layer
- No full-table scans on historical telemetry tables

## Scalability Considerations
- Designed to handle ~14.4 million telemetry records per day
- Append-only writes reduce lock contention
- Hot and cold data separation ensures predictable query latency
- Indexed time-series queries for analytics
- Stateless service design allows horizontal scaling

### Metrics (Last 24 Hours)
- Total **AC energy consumed**
- Total **DC energy delivered**
- **Efficiency ratio** (DC / AC)
- **Average battery temperature**

---

## Technology Stack
- Backend: Spring Boot (Java)
- Database: PostgreSQL / MySQL
- ORM: JPA / Hibernate
- Containerization: Docker & Docker Compose

## Running the Application (Docker)

To run the service using Docker:

```bash
mvn clean package -DskipTests
docker-compose down
docker-compose up --build
```

## Endpoint

---

## Author

**Mallikarjun Jamadar**  
Backend Developer | Java | Spring Boot  

- GitHub: https://github.com/Mallikarjun5015  
- Tech Stack: Java, Spring Boot, JPA/Hibernate, MySQL, Docker  
- Interests: Scalable backend systems, data ingestion pipelines, analytics systems

