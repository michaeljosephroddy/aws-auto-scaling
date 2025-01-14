# Auto-Scaling and Load Balancing

This project demonstrates auto-scaling and load balancing in Amazon EC2 using a Java microservice.

---

## How It Works

1. **Request Handling:**
   - Incoming requests are routed through an Application Load Balancer (ALB).
   - The ALB distributes requests to healthy instances within the target group.

2. **Auto-Scaling:**
   - An Auto Scaling Group (ASG) manages the lifecycle of EC2 instances, starting or terminating them as needed.
   - Each instance sends numerical metrics (e.g., CPU utilization) to Amazon CloudWatch.
   - A target tracking policy in the ASG monitors the average CPU utilization:
     - If the average CPU utilization exceeds the target threshold, the ASG launches additional instances.
     - If the utilization falls below the threshold, the ASG terminates excess instances.

---

## AWS Setup

For detailed instructions on setting up AWS resources, refer to the `auto-scaling-and-load-balancing/report.pdf` file.

---

## MySQL Configuration

1. Use the `couponservice/sql/tables.sql` file to set up the database schema.
2. Update the database URL, username, and password in the `application.yml` file to match your MySQL configuration.

---

## Coupon Service

`couponservice` is a Spring Boot microservice for creating and retrieving coupon data. It interacts with a MySQL database to persist and query coupons.

A sample coupon JSON object:
```json
{
  "id": 12345,
  "code": "DUMMYDATA",
  "discount": 10.99,
  "expDate": "01/05/1997"
}
```

---

## Generating Load

Use the `auto-scaling-and-load-balancing/send_requests.py` script to simulate load on the microservice. Update the script with your Application Load Balancer’s DNS name before running it.

---

## Architecture

Below is an architecture diagram illustrating the entities and interactions involved in the auto-scaling and load-balancing setup:

![Auto-Scaling and Load Balancing Architecture](./assignment1.jpg)

