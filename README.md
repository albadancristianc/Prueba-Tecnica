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

🔹 Ejecutar la API

Clona el repositorio:

https://github.com/albadancristianc/Prueba-Tecnica.git
cd polizas-api

Ejecuta con Maven:

mvn spring-boot:run

La API estará disponible en:

http://localhost:8080
🔹 Seguridad

Todos los endpoints requieren el header:

x-api-key: 123456

Si el valor es incorrecto, la API retornará un error.

🔹 Endpoints
1️⃣ Listar pólizas
GET /polizas?tipo={tipo}&estado={estado}
Headers: x-api-key

Retorna todas las pólizas filtradas por tipo y estado.

2️⃣ Obtener riesgos de una póliza
GET /polizas/{id}/riesgos
Headers: x-api-key

Retorna los riesgos asociados a la póliza.

3️⃣ Renovar póliza
POST /polizas/{id}/renovar
Headers: x-api-key

Incrementa canon y prima en +IPC (10% mock).

Cambia estado a RENOVADA.

Registra evento en logs hacia /core-mock/evento.

4️⃣ Cancelar póliza
POST /polizas/{id}/cancelar
Headers: x-api-key

Cambia estado a CANCELADA.

Todos los riesgos asociados pasan a CANCELADO.

5️⃣ Agregar riesgo
POST /polizas/{id}/riesgos
Headers: x-api-key
Body: {
  "descripcion": "Descripcion del riesgo"
}

Solo si la póliza es COLECTIVA.

Una póliza individual solo puede tener 1 riesgo.

6️⃣ Cancelar riesgo
POST /riesgos/{id}/cancelar
Headers: x-api-key

Cambia el estado del riesgo a CANCELADO.

7️⃣ Mock externo
POST /core-mock/evento
Body: {
  "evento": "ACTUALIZACION",
  "polizaId": 555
}

Solo registra en logs que se intentó enviar el evento al CORE.

🔹 Reglas de negocio

Una póliza individual solo puede tener 1 riesgo.

No se puede renovar una póliza cancelada.

La cancelación de una póliza cancela todos sus riesgos.

Agregar riesgo exige validación del tipo de póliza (COLECTIVA).
