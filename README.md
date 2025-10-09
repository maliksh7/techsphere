# 🖥️ TechSphere

TechSphere is an e-commerce platform for **electronics, PCs, and accessories**, built with a modern full-stack architecture.

---

## 🧩 Tech Stack

- **Backend:** Spring Boot (REST API, JPA/Hibernate)
- **Frontend:** React.js
- **Cloud:** AWS (EC2, S3, RDS)
- **Payments:** Stripe
- **AI:** Spring AI for intelligent recommendations

---


## 🚀 Getting Started

### 1️⃣ Prerequisites
- Java 17+
- Node.js 18+
- Maven
- AWS credentials configured
- Stripe API key
---
### 2️⃣ Run Backend
```bash
cd backend
./mvnw spring-boot:run
```
---
### Run Frontend
```bash

cd frontend
npm install
npm start

```

---

#### Testing
```bash


cd backend
./mvnw test

# Frontend
cd frontend
npm test

```

### 📦 Deployment

- Build backend: mvn clean package
- Build frontend: npm run build 
- Deploy on AWS (Elastic Beanstalk / ECS / EC2)
- Configure Stripe webhooks and environment variables


### 💡 Contribution Guide

- Create a new branch: git checkout -b feature/backend-api 
- Commit changes: git commit -m "feat(api): add product endpoints"
- Push: git push origin feature/backend-api 
- Submit a pull request