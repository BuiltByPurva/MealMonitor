# Docker Compose Commands for Starting Specific Services

## Infrastructure Services (Required First)

### 1. Start MySQL Database Only
```powershell
docker-compose up -d mysql
```

### 2. Start Eureka Server Only
```powershell
docker-compose up -d eureka-server
```

### 3. Start Zipkin (Distributed Tracing) Only
```powershell
docker-compose up -d zipkin
```

### 4. Start Infrastructure (MySQL + Eureka + Zipkin)
```powershell
docker-compose up -d mysql zipkin eureka-server
```

## Core Services

### 5. Start API Gateway Only
```powershell
docker-compose up -d gateway
```

### 6. Start User Service Only
```powershell
docker-compose up -d user-service
```

### 7. Start Canteen Service Only
```powershell
docker-compose up -d canteen-service
```

### 8. Start Review Service Only
```powershell
docker-compose up -d review-service
```

### 9. Start Notification Service Only
```powershell
docker-compose up -d notification-service
```

### 10. Start Poll Service Only
```powershell
docker-compose up -d poll-service
```

### 11. Start Moderation Service Only
```powershell
docker-compose up -d moderation-service
```

## Frontend & Monitoring

### 12. Start Frontend Only
```powershell
docker-compose up -d frontend
```

### 13. Start Prometheus Only
```powershell
docker-compose up -d prometheus
```

### 14. Start Grafana Only
```powershell
docker-compose up -d grafana
```

### 15. Start Monitoring Stack (Prometheus + Grafana)
```powershell
docker-compose up -d prometheus grafana
```

## Common Service Combinations

### 16. Start All Infrastructure + Gateway
```powershell
docker-compose up -d mysql zipkin eureka-server gateway
```

### 17. Start All Microservices (without frontend/monitoring)
```powershell
docker-compose up -d user-service canteen-service review-service notification-service poll-service moderation-service
```

### 18. Start Specific Microservices (e.g., User + Review + Poll)
```powershell
docker-compose up -d user-service review-service poll-service
```

### 19. Start Complete Stack (Everything)
```powershell
docker-compose up -d
```

### 20. Start Services in Order (Recommended for First Time)
```powershell
# Step 1: Infrastructure
docker-compose up -d mysql zipkin eureka-server

# Wait 60 seconds
Start-Sleep -Seconds 60

# Step 2: Gateway
docker-compose up -d gateway

# Wait 30 seconds
Start-Sleep -Seconds 30

# Step 3: All Microservices
docker-compose up -d user-service canteen-service review-service notification-service poll-service moderation-service

# Wait 30 seconds
Start-Sleep -Seconds 30

# Step 4: Frontend and Monitoring
docker-compose up -d frontend prometheus grafana
```

## Utility Commands

### View Running Services
```powershell
docker-compose ps
```

### View Logs of Specific Service
```powershell
docker-compose logs -f <service-name>
# Example: docker-compose logs -f poll-service
```

### Stop Specific Service
```powershell
docker-compose stop <service-name>
# Example: docker-compose stop poll-service
```

### Stop All Services
```powershell
docker-compose down
```

### Stop and Remove Volumes (Clean Slate)
```powershell
docker-compose down -v
```

### Rebuild Specific Service
```powershell
docker-compose build <service-name>
docker-compose up -d <service-name>
# Example: docker-compose build poll-service && docker-compose up -d poll-service
```

### Restart Specific Service
```powershell
docker-compose restart <service-name>
# Example: docker-compose restart moderation-service
```

## Service Dependencies

**Important**: Services have dependencies. Start them in this order:

1. **Infrastructure**: `mysql` → `zipkin` → `eureka-server`
2. **Gateway**: Requires `eureka-server` and `zipkin`
3. **Microservices**: Require `mysql`, `eureka-server`, and `zipkin`
4. **Frontend**: Requires `gateway`
5. **Grafana**: Requires `prometheus`

## Ports Reference

| Service | Port | URL |
|---------|------|-----|
| MySQL | 3307 | localhost:3307 |
| Eureka Server | 8761 | http://localhost:8761 |
| Gateway | 8080 | http://localhost:8080 |
| User Service | 8084 | http://localhost:8084 |
| Canteen Service | 8086 | http://localhost:8086 |
| Review Service | 8082 | http://localhost:8082 |
| Notification Service | 8089 | http://localhost:8089 |
| Poll Service | 8087 | http://localhost:8087 |
| Moderation Service | 8088 | http://localhost:8088 |
| Frontend | 3000 | http://localhost:3000 |
| Zipkin | 9411 | http://localhost:9411 |
| Prometheus | 9090 | http://localhost:9090 |
| Grafana | 3001 | http://localhost:3001 |

