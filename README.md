# Examen_2P-ArquitecturaSoftware

## BanQuito – Branch & Holidays API

### Descripción general
Este proyecto corresponde al desarrollo de una API RESTful orientada a la gestión de sucursales bancarias y sus feriados para el Banco BanQuito. El sistema permite administrar información básica de las sucursales, así como registrar, consultar y validar los días no laborables asociados a cada una de ellas.

La solución ha sido diseñada siguiendo principios de arquitectura limpia, separación de responsabilidades y buenas prácticas en el diseño de servicios REST, garantizando un código mantenible, escalable y fácil de extender.

---

### Objetivo del proyecto
Desarrollar un servicio backend que permita:

- Gestionar la información de sucursales bancarias.
- Registrar y administrar feriados asociados a cada sucursal.
- Verificar si una fecha específica corresponde a un feriado en una sucursal determinada.
- Exponer la funcionalidad mediante endpoints REST versionados.

---

### Arquitectura y tecnologías utilizadas

El proyecto ha sido implementado utilizando las siguientes tecnologías:

- Java 21  
- Spring Boot 3.5.8  
- Spring Web (REST)  
- Spring Data MongoDB  
- MongoDB  
- Lombok  
- Springdoc OpenAPI (Swagger UI)  
- Maven  
- Git y GitHub  

La arquitectura del sistema se encuentra organizada en capas:

- controller: responsable de la exposición de los endpoints REST.
- service: contiene la lógica de negocio de la aplicación.
- repository: gestiona el acceso a los datos almacenados en MongoDB.
- model: define las entidades del dominio.
- dto: encapsula los objetos de transferencia de datos.
- exception: maneja de forma centralizada los errores de la aplicación.

---

### Persistencia de datos (MongoDB)

La aplicación utiliza MongoDB como sistema de almacenamiento de datos.

- Base de datos: banquito
- Colecciones:
  - branch
  - branchHoliday

La relación entre las entidades se implementa siguiendo el principio de relación hijo a padre. En este contexto, la colección branchHoliday almacena el identificador de la sucursal (branchId), permitiendo asociar múltiples feriados a una sola sucursal sin generar dependencias circulares.

---

### Endpoints de la API

#### 1. Obtener todas las sucursales (paginado)
GET `/api/v1/branches`

Parámetros:
- page (valor por defecto: 0)
- limit (valor por defecto: 10)

Respuesta:
- 200 OK

---

#### 2. Crear una sucursal (sin feriados)
POST `/api/v1/branches`

```json
{
  "id": "BR-001",
  "emailAddress": "branch001@banquito.ec",
  "name": "Branch Central",
  "phoneNumber": "0999999999",
  "state": "ACTIVE"
}
