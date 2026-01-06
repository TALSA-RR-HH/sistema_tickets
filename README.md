# Sistema de Registro de Tickets RRHH - Talsa

Sistema web corporativo para la gestión de solicitudes internas desarrollado para el área de Recursos Humanos. El proyecto implementa una arquitectura desacoplada con un Backend API REST robusto y seguro.

## 🛠 Tecnologías

### Backend (API REST)
* **Lenguaje:** Java 25
* **Framework:** Spring Boot 3.x
* **Base de Datos:** PostgreSQL
* **Seguridad:** Spring Security (con encriptación **BCrypt**)
* **Arquitectura:** API RESTful con patrón DTO (Data Transfer Object)
* **Herramientas:** Maven, Lombok, IntelliJ IDEA

### Frontend (Cliente Web)
* **Framework:** Angular (SPA - Single Page Application)
* **Estilos:** Bootstrap / CSS3
* **Comunicación:** HTTP Client (Consumo de JSON)

## 📋 Funcionalidades Implementadas
1.  **Seguridad Avanzada:**
    * Login con validación de credenciales encriptadas.
    * Protección de contraseñas con hash `BCrypt`.
    * Configuración CORS segura para peticiones desde el navegador.
2.  **Gestión de Usuarios:**
    * Carga masiva automática (`DataInitializer`) si la BD está vacía.
    * Roles diferenciados (ROLE_ADMIN, ROLE_USER).
3.  **Catálogo de Servicios:** Listado dinámico de servicios disponibles (Boletas, Vacaciones, etc.).
4.  **Gestión de Solicitudes:**
    * Creación de tickets (validación de usuarios activos).
    * Historial de solicitudes optimizado (respuestas limpias sin datos sensibles).
5.  **Reportes:** Estadísticas por usuario y globales.

## ⚙️ Configuración Local

1.  **Base de Datos:**
    * Crear base de datos PostgreSQL llamada `rrhh_tickets_db`.
    * El sistema gestiona las tablas automáticamente (`ddl-auto=update`).

2.  **Configuración:**
    * Revisar credenciales en `src/main/resources/application.properties`.

3.  **Ejecución:**
    * Ejecutar `mvn spring-boot:run` o desde IntelliJ.
    * *Nota:* Al primer inicio, el sistema creará automáticamente un usuario administrador:
        * **User:** `admin`
        * **Pass:** `admin`

4.  **Pruebas (API):**
    * Importar la colección de Postman para probar los endpoints:
        * `POST /api/usuarios/login`
        * `GET /api/servicios`
        * `POST /api/solicitudes`

---
Desarrollado por Valentin Fernandez - 2026