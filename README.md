# Polizas API – Prueba Técnica (Módulo 2)

API REST para la gestión de pólizas y riesgos, implementada en **Spring Boot**.  
Cumple con los requerimientos del Módulo 2 de la prueba técnica.

---

## 🔹 Tecnologías

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- H2 Database (temporal en memoria)
- Maven
- Lombok

---

## 🔹 Estructura del proyecto

```text
polizas-api/
├─ src/main/java/com/prueba/polizas/controller
│   ├─ CoreMockController.java
│   └─ PolizaController.java
├─ src/main/java/com/prueba/polizas/entity
│   ├─ Poliza.java
│   └─ Riesgo.java
├─ src/main/java/com/prueba/polizas/service
│   ├─ PolizaService.java
│   └─ PolizaServiceImpl.java
├─ src/main/java/com/prueba/polizas/repository
│   ├─ PolizaRepository.java
│   └─ RiesgoRepository.java
├─ pom.xml
└─ src/main/resources/application.properties
