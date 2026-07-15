# System Architecture

The Adaptive AI Serving Infrastructure handles model inference requests end to end, from API entry to monitoring and adaptive operations.

![Adaptive AI Serving Architecture](./images/adaptive-ai-serving-architecture.png)

*Figure 1: End-to-End Architecture of the Adaptive AI Serving Infrastructure.*

## End-to-End Flow

1. **Client → Inference Gateway**  
   Client applications send inference requests to the gateway, which performs request validation and routing.

2. **Gateway → Redis Rate Limiter**  
   Incoming requests are checked against the current rate-limiting policy to protect the serving infrastructure from overload.

3. **Gateway → Model Service (gRPC)**  
   Accepted requests are forwarded to the model service through gRPC for low-latency inference.

4. **Event Streaming & Storage**  
   Inference events are published to Kafka, consumed by the Storage Service, and persisted in PostgreSQL for analysis and auditing.

5. **Observability & Adaptive Control**  
   Prometheus and Grafana collect runtime metrics. The Anomaly Service analyzes telemetry and automatically updates Redis rate-limiting thresholds to maintain system stability.