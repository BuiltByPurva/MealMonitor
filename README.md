# 🍽️ MealMonitor - Food Review Platform

A community-driven food review platform for educational institutions that empowers students, faculty, and staff to share real-time feedback on canteen food quality, helping others make informed choices and encouraging accountability from canteen administration.

[![Java](https://img.shields.io/badge/Java-21-orange)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.5-brightgreen)](https://spring.io/projects/spring-boot)
[![React](https://img.shields.io/badge/React-18.2.0-blue)](https://reactjs.org/)
[![TypeScript](https://img.shields.io/badge/TypeScript-4.9.5-blue)](https://www.typescriptlang.org/)
[![Docker](https://img.shields.io/badge/Docker-Compose-2496ED)](https://www.docker.com/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)](https://www.mysql.com/)

---

## 📋 Table of Contents

- [Architecture](#-architecture)
- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Prerequisites](#-prerequisites)
- [Quick Start](#-quick-start)
- [Services Overview](#-services-overview)
- [API Documentation](#-api-documentation)
- [Monitoring](#-monitoring)
- [Development](#-development)
- [Troubleshooting](#-troubleshooting)
- [Contributing](#-contributing)

---

## 🏗️ Architecture

MealMonitor follows a **microservices architecture** with the following components:

```
┌─────────────────────────────────────────────────────────────────┐
│                          Frontend (React)                        │
│                      http://localhost:3000                       │
└────────────────────────┬────────────────────────────────────────┘
                         │
                         ▼
┌─────────────────────────────────────────────────────────────────┐
│                    API Gateway (Port 8080)                       │
│              Routes all requests to microservices                │
└────────────┬────────────────────────────────────────────────────┘
             │
             ├──────────────────┬──────────────────┬──────────────┐
             ▼                  ▼                  ▼              ▼
    ┌──────────────┐   ┌──────────────┐   ┌──────────────┐   ┌──────────────┐
    │     User     │   │   Canteen    │   │    Review    │   │     Poll     │
    │   Service    │   │   Service    │   │   Service    │   │   Service    │
    │  (Port 8084) │   │  (Port 8086) │   │  (Port 8082) │   │  (Port 8087) │
    └──────┬───────┘   └──────┬───────┘   └──────┬───────┘   └──────┬───────┘
           │                  │                  │                  │
    ┌──────────────┐   ┌──────────────┐   ┌──────────────┐
    │Notification  │   │ Moderation   │   │   WebApp     │
    │  Service     │   │   Service    │   │   Service    │
    │(Port 8089)   │   │  (Port 8088) │   │  (Port 8085) │
    └──────┬───────┘   └──────┬───────┘   └──────┬───────┘
           │                  │                  │
           └──────────────────┴──────────────────┘
                              │
                              ▼
              ┌───────────────────────────────┐
              │    Eureka Server (Port 8761)  │
              │    Service Discovery          │
              └───────────────┬───────────────┘
                              │
              ┌───────────────┴───────────────┐
              │                               │
              ▼                               ▼
    ┌──────────────────┐          ┌──────────────────┐
    │  MySQL (Port 3307)│          │  Zipkin (Port 9411)│
    │  7 Databases      │          │  Distributed Tracing│
    └───────────────────┘          └───────────────────┘

              Monitoring Stack
    ┌──────────────────┐          ┌──────────────────┐
    │Prometheus (9090) │◄─────────│ Grafana (3001)   │
    │Metrics Collection│          │  Dashboards      │
    └──────────────────┘          └──────────────────┘
```

---

## ✨ Features

### 👤 **User Management**
- User registration and authentication
- JWT-based secure authentication
- Role-based access control (Student, Faculty, Staff, Admin)
- Profile management with image upload

### 🍔 **Canteen Management**
- Menu item creation and management
- Real-time availability status
- Nutritional information
- Pricing and categorization

### ⭐ **Review System**
- Rate and review meals (1-5 stars)
- Upload photos of food
- Comment on specific dishes
- View aggregate ratings
- Filter reviews by date, rating, dish

### 📊 **Polling System**
- Create polls for food preferences
- Vote on menu items
- View real-time poll results
- Admin-driven feedback collection

### 🔔 **Notifications**
- Real-time notifications for:
  - New menu items
  - Poll updates
  - Review responses
  - System announcements

### 🛡️ **Moderation**
- Content flagging system
- Issue reporting
- Admin action tracking
- Resolution updates

### 📈 **Monitoring & Analytics**
- Service health monitoring (Prometheus)
- Visual dashboards (Grafana)
- Distributed tracing (Zipkin)
- Performance metrics

---

## 🛠️ Tech Stack

### **Backend**
- **Framework:** Spring Boot 3.5.5
- **Language:** Java 21
- **Service Discovery:** Netflix Eureka
- **API Gateway:** Spring Cloud Gateway
- **Database:** MySQL 8.0
- **Security:** Spring Security with JWT
- **Build Tool:** Maven 3.9.6

### **Frontend**
- **Framework:** React 18.2.0
- **Language:** TypeScript 4.9.5
- **UI Library:** Material-UI (MUI) 7.3.2
- **Routing:** React Router DOM 7.8.2
- **HTTP Client:** Axios 1.11.0
- **Web Server:** Nginx (in Docker)

### **DevOps & Infrastructure**
- **Containerization:** Docker & Docker Compose
- **Monitoring:** Prometheus + Grafana
- **Tracing:** Zipkin
- **Reverse Proxy:** Nginx

### **Key Dependencies**
- Spring Cloud Netflix (Service Discovery)
- Spring Cloud Gateway (API Gateway)
- Spring Data JPA (ORM)
- Hibernate (JPA Implementation)
- JWT (Authentication)
- Resilience4j (Circuit Breaker, Retry)
- Micrometer (Metrics)

---

## 📦 Prerequisites

Before you begin, ensure you have the following installed:

| Tool | Version | Purpose |
|------|---------|---------|
| **Docker Desktop** | Latest | Container runtime |
| **Docker Compose** | v2.0+ | Multi-container orchestration |
| **Git** | Latest | Version control |
| **Java JDK** | 21+ | (Optional) For local development |
| **Node.js** | 18+ | (Optional) For frontend development |
| **Maven** | 3.9+ | (Optional) For building locally |

### **System Requirements**
- **RAM:** Minimum 8GB (16GB recommended)
- **Disk Space:** 10GB free space
- **CPU:** 4 cores recommended
- **OS:** Windows 10/11, macOS, or Linux

---

## 🚀 Quick Start

### **Option 1: Automated Setup (Recommended)**

```powershell
# Clone the repository
git clone https://github.com/Umed23/MealMonitor.git
cd MealMonitor

# Run the automated setup script
.\rebuild-all.ps1
```

This will:
1. Build all microservices
2. Start all containers
3. Initialize databases
4. Set up monitoring

**Wait Time:** ~5-7 minutes for first-time setup

### **Option 2: Manual Setup**

```powershell
# 1. Start infrastructure services
docker-compose up -d mysql zipkin eureka-server

# Wait 60 seconds for MySQL and Eureka to initialize
Start-Sleep -Seconds 60

# 2. Start API Gateway
docker-compose up -d gateway

# Wait 30 seconds
Start-Sleep -Seconds 30

# 3. Start all microservices
docker-compose up -d user-service canteen-service review-service notification-service poll-service moderation-service web-app

# Wait 30 seconds
Start-Sleep -Seconds 30

# 4. Start frontend and monitoring
docker-compose up -d frontend prometheus grafana
```

### **Verify Installation**

```powershell
# Check all services are running
docker-compose ps

# All services should show "Up" or "Up (healthy)"
```

---

## 🌐 Services Overview

### **Core Services**

| Service | Port | Purpose | Health Check |
|---------|------|---------|--------------|
| **Frontend** | 3000 | React web application | http://localhost:3000 |
| **API Gateway** | 8080 | Routes all API requests | http://localhost:8080/actuator/health |
| **Eureka Server** | 8761 | Service discovery | http://localhost:8761 |
| **User Service** | 8084 | User authentication & management | http://localhost:8084/actuator/health |
| **Canteen Service** | 8086 | Menu & item management | http://localhost:8086/actuator/health |
| **Review Service** | 8082 | Food reviews & ratings | http://localhost:8082/actuator/health |
| **Notification Service** | 8089 | Push notifications | http://localhost:8089/actuator/health |
| **Poll Service** | 8087 | Polls & voting | http://localhost:8087/actuator/health |
| **Moderation Service** | 8088 | Content moderation | http://localhost:8088/actuator/health |
| **WebApp Service** | 8085 | Static content serving | http://localhost:8085/actuator/health |

### **Infrastructure Services**

| Service | Port | Purpose | Access URL |
|---------|------|---------|------------|
| **MySQL** | 3307 | Database server | localhost:3307 |
| **Zipkin** | 9411 | Distributed tracing | http://localhost:9411 |
| **Prometheus** | 9090 | Metrics collection | http://localhost:9090 |
| **Grafana** | 3001 | Monitoring dashboards | http://localhost:3001 |

### **Database Schema**

Seven separate databases (one per service):
- `mealmontor_main` - Shared configuration
- `mealmontor_users` - User accounts
- `mealmontor_canteen` - Menu items
- `mealmontor_reviews` - Reviews & ratings
- `mealmontor_notifications` - Notification logs
- `mealmontor_polls` - Polls & votes
- `mealmontor_moderation` - Moderation logs

---

## 📚 API Documentation

### **Base URL**
```
http://localhost:8080/api
```

### **Authentication**
All protected endpoints require JWT token in header:
```
Authorization: Bearer <your_jwt_token>
```

### **API Endpoints**

#### **User Service** (`/api/users`)
```http
POST   /api/auth/register         # Register new user
POST   /api/auth/login            # Login and get JWT
GET    /api/users/profile         # Get user profile
PUT    /api/users/profile         # Update profile
GET    /api/users/{id}            # Get user by ID
```

#### **Canteen Service** (`/api/canteen`)
```http
GET    /api/canteen/items         # Get all menu items
GET    /api/canteen/items/{id}    # Get item by ID
POST   /api/canteen/items         # Create new item (Admin)
PUT    /api/canteen/items/{id}    # Update item (Admin)
DELETE /api/canteen/items/{id}    # Delete item (Admin)
GET    /api/canteen/menu/today    # Get today's menu
```

#### **Review Service** (`/api/reviews`)
```http
GET    /api/reviews               # Get all reviews
GET    /api/reviews/{id}          # Get review by ID
POST   /api/reviews               # Create new review
PUT    /api/reviews/{id}          # Update review
DELETE /api/reviews/{id}          # Delete review
GET    /api/reviews/item/{itemId} # Get reviews for item
GET    /api/reviews/user/{userId} # Get user's reviews
```

#### **Poll Service** (`/api/polls`)
```http
GET    /api/polls                 # Get all active polls
GET    /api/polls/{id}            # Get poll by ID
POST   /api/polls                 # Create poll (Admin)
POST   /api/polls/{id}/vote       # Vote on poll
GET    /api/polls/{id}/results    # Get poll results
```

#### **Notification Service** (`/api/notifications`)
```http
GET    /api/notifications         # Get user notifications
POST   /api/notifications/read/{id} # Mark as read
DELETE /api/notifications/{id}    # Delete notification
GET    /api/notifications/unread  # Get unread count
```

#### **Moderation Service** (`/api/moderation`)
```http
POST   /api/moderation/flag       # Flag content
POST   /api/moderation/report     # Report issue
GET    /api/moderation/issues     # Get all issues (Admin)
PUT    /api/moderation/resolve/{id} # Resolve issue (Admin)
```

### **Response Format**

**Success Response:**
```json
{
  "status": "success",
  "data": {
    // Response data
  },
  "timestamp": "2025-01-01T12:00:00Z"
}
```

**Error Response:**
```json
{
  "status": "error",
  "message": "Error description",
  "code": 400,
  "timestamp": "2025-01-01T12:00:00Z"
}
```

---

## 📊 Monitoring

### **Eureka Dashboard**
Monitor service registration and health:
```
http://localhost:8761
```

Shows all registered microservices and their instances.

### **Zipkin Tracing**
View distributed traces across services:
```
http://localhost:9411
```

Features:
- Request tracing across microservices
- Latency analysis
- Dependency visualization
- Error tracking

### **Prometheus Metrics**
Access raw metrics:
```
http://localhost:9090
```

Available metrics for each service:
- HTTP request rates
- Response times
- Error rates
- JVM metrics (memory, threads, GC)
- Custom business metrics

### **Grafana Dashboards**
Beautiful visualizations:
```
http://localhost:3001
Username: admin
Password: admin
```

Pre-configured dashboards:
- **MealMonitor Overview** - System-wide metrics
- **Service Health** - Individual service monitoring
- **Database Performance** - MySQL metrics
- **API Gateway** - Request routing metrics

---

## 💻 Development

### **Project Structure**

```
MealMonitor/
├── MealMonitor-EurekaServer/       # Service Discovery
├── MealMonitor-Gateway/            # API Gateway
├── MealMonitor-UserService/        # User management
├── MealMonitor-CanteenService/     # Menu management
├── MealMonitor-ReviewService/      # Review system
├── MealMonitor-NotificationService/# Notifications
├── MealMonitor-PollService/        # Polling system
├── MealMonitor-ModerationService/  # Content moderation
├── MealMonitor-WebApp/             # Static content
├── mealmontor-frontend/            # React frontend
├── monitoring/                     # Prometheus & Grafana configs
├── init-scripts/                   # Database initialization
├── docker-compose.yml              # Container orchestration
└── README.md                       # This file
```

### **Local Development**

#### **Backend (Java Microservices)**

```bash
# Navigate to a service directory
cd MealMonitor-UserService

# Build the service
mvn clean install

# Run locally (requires local MySQL and Eureka)
mvn spring-boot:run
```

**Configuration:**
- Update `application.yml` for local development
- Point to `localhost:3306` for MySQL
- Point to `localhost:8761` for Eureka

#### **Frontend (React)**

```bash
cd mealmontor-frontend

# Install dependencies
npm install

# Start development server
npm start

# Build for production
npm run build
```

The app will run on `http://localhost:3000` and proxy API requests to `http://localhost:8080`.

### **Building Docker Images**

```powershell
# Build specific service
docker-compose build user-service

# Build all services
docker-compose build

# Build without cache
docker-compose build --no-cache
```

### **Viewing Logs**

```powershell
# All services
docker-compose logs -f

# Specific service
docker logs mealmontor-user-service -f

# Last 50 lines
docker logs mealmontor-gateway --tail 50
```

---

## 🐛 Troubleshooting

### **Services Not Starting**

**Problem:** Containers exit immediately

**Solution:**
```powershell
# Check logs
docker-compose logs <service-name>

# Restart Docker Desktop
# Then restart services
docker-compose down
docker-compose up -d
```

### **Service Not Registered in Eureka**

**Problem:** Service doesn't appear in Eureka dashboard

**Solution:**
```powershell
# Check service logs for Eureka registration
docker logs mealmontor-user-service | Select-String "Eureka"

# Restart the service
docker-compose restart user-service

# Wait 30 seconds for registration
Start-Sleep -Seconds 30
```

### **Gateway Returns 503 Service Unavailable**

**Problem:** API Gateway can't route to service

**Cause:** Service not registered in Eureka

**Solution:**
1. Check Eureka dashboard: http://localhost:8761
2. Ensure service is listed and status is "UP"
3. Restart Gateway: `docker-compose restart gateway`

### **MySQL Connection Failed**

**Problem:** Services can't connect to MySQL

**Solution:**
```powershell
# Check MySQL is healthy
docker inspect --format='{{.State.Health.Status}}' mealmontor-mysql

# If not healthy, restart MySQL
docker-compose restart mysql

# Wait for health check
Start-Sleep -Seconds 60

# Restart services
docker-compose restart user-service canteen-service review-service
```

### **Frontend Can't Reach Backend**

**Problem:** API calls fail with CORS or network errors

**Solution:**
1. Verify Gateway is running: `docker ps | Select-String "gateway"`
2. Test Gateway: `curl http://localhost:8080/actuator/health`
3. Check nginx configuration in `mealmontor-frontend/nginx.conf`
4. Restart frontend: `docker-compose restart frontend`

### **Port Already in Use**

**Problem:** `Error: Port 8080 is already allocated`

**Solution:**
```powershell
# Find what's using the port
netstat -ano | findstr :8080

# Stop the process or change port in docker-compose.yml
# Then restart
docker-compose up -d
```

### **Out of Memory**

**Problem:** Services crash with `OutOfMemoryError`

**Solution:**
- Increase Docker Desktop memory allocation (Settings > Resources)
- Recommended: 8GB minimum, 16GB optimal
- Stop unnecessary containers

### **Clean Rebuild**

If all else fails, perform a clean rebuild:

```powershell
# Stop and remove everything
docker-compose down -v

# Remove all MealMonitor images
docker images | Select-String "mealmonitor" | ForEach-Object { docker rmi $($_ -split '\s+')[2] -f }

# Rebuild everything
.\rebuild-all.ps1
```

---

## 📖 Additional Documentation

- **Quick Start Guide:** `QUICK_START.md`
- **Service Startup Guide:** `SERVICE_STARTUP_GUIDE.md`
- **Monitoring Guide:** `MONITORING_GUIDE.md`
- **Port Configuration:** `PORT_CONFIGURATION.md`
- **Eureka Registration:** `EUREKA_REGISTRATION_FIX_COMPLETE.md`

---

## 🤝 Contributing

We welcome contributions! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

### **Coding Standards**

- **Java:** Follow Spring Boot best practices
- **React:** Use functional components with hooks
- **TypeScript:** Strict mode enabled
- **Commits:** Use conventional commits format

---

## 📝 License

This project is developed for educational purposes.

---

## 👥 Team

Developed by students for the Microservices Architecture course.

---

## 📧 Support

For issues and questions:
- **Issues:** [GitHub Issues](https://github.com/Umed23/MealMonitor/issues)
- **Discussions:** [GitHub Discussions](https://github.com/Umed23/MealMonitor/discussions)

---

## 🎯 Roadmap

### **Phase 1** (Current)
- ✅ Basic microservices architecture
- ✅ Service discovery with Eureka
- ✅ API Gateway routing
- ✅ User authentication
- ✅ Review system
- ✅ Docker containerization
- ✅ Monitoring with Prometheus & Grafana

### **Phase 2** (Upcoming)
- [ ] Real-time notifications with WebSocket
- [ ] Advanced search and filtering
- [ ] Mobile responsive design improvements
- [ ] Performance optimization
- [ ] Enhanced security features
- [ ] Automated testing (Unit & Integration)

### **Phase 3** (Future)
- [ ] Mobile app (React Native)
- [ ] AI-powered food recommendations
- [ ] Nutritional analysis
- [ ] Integration with payment systems
- [ ] Multi-language support
- [ ] Advanced analytics dashboard

---

## ⭐ Star History

If you find this project helpful, please consider giving it a star!

[![Star History Chart](https://api.star-history.com/svg?repos=Umed23/MealMonitor&type=Date)](https://star-history.com/#Umed23/MealMonitor&Date)

---

## 🙏 Acknowledgments

- Spring Boot Team for excellent microservices framework
- Netflix OSS for Eureka
- Docker for containerization platform
- Material-UI for React components
- All contributors and supporters

---

**Made with ❤️ for better campus dining experiences**
