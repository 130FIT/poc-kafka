# ภาพรวมของโปรเจกต์

โปรเจกต์นี้เป็น **Proof of Concept (POC)** สำหรับระบบส่งข้อความโดยใช้ **Apache Kafka** และ **Spring Boot** โดยมีการทำงานดังนี้:

1. **KafkaProducerService**: ส่งข้อความไปยัง Kafka topic
2. **KafkaConsumerService**: รับข้อความจาก Kafka topic และประมวลผล
3. **MessageDTO**: ใช้เป็น Data Transfer Object (DTO) สำหรับ payload ของข้อความ

---

## คุณสมบัติ
- Producer สำหรับส่งข้อความ JSON พร้อม header แบบกำหนดเอง
- Consumer สำหรับรับข้อความและแสดงผลใน log
- การตั้งค่า Kafka ที่ปรับแต่งได้ผ่านไฟล์ `application.yaml`

---

## ข้อกำหนดเบื้องต้น
1. **Java**: ติดตั้ง Java 17 หรือเวอร์ชันที่สูงกว่า
2. **Maven**: ติดตั้งและตั้งค่า Maven
3. **Kafka**: มี Kafka instance ที่ทำงานอยู่ (เช่น ผ่าน Docker หรือการติดตั้งในเครื่อง)

---

## การตั้งค่า Kafka ใน `application.yaml`

ตัวอย่างการตั้งค่า Kafka ในไฟล์ `application.yaml`:

```yaml
spring:
  kafka:
    bootstrap-servers: localhost:29094
    consumer:
      auto-offset-reset: earliest
      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
      value-deserializer: org.apache.kafka.common.serialization.StringDeserializer
      properties:
        session.timeout.ms: 15000
        max.poll.interval.ms: 300000
    producer:
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      value-serializer: org.apache.kafka.common.serialization.StringSerializer
      retries: 3
      acks: all
    properties:
      linger.ms: 1
      request.timeout.ms: 30000
      retry.backoff.ms: 1000
```
- **bootstrap-servers**: ระบุที่อยู่ของ Kafka server
- **auto-offset-reset**: ตั้งค่าการเริ่มต้น offset (เช่น earliest หรือ latest)
- **session.timeout.ms**: ระยะเวลาที่ consumer จะรอ heartbeat ก่อน timeout
- **retries**: จำนวนครั้งที่ producer จะพยายามส่งข้อความใหม่ในกรณีที่ล้มเหลว
- **acks**: ระบุระดับการยืนยันจาก broker