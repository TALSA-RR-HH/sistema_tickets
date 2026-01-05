# Sistema de Registro de Tickets RRHH - Talsa

Sistema web corporativo para la gestión de solicitudes internas desarrollado para el área de Recursos Humanos.

## 🛠 Tecnologías
* **Backend:** Java 25, Spring Boot 3.x
* **Frontend:** Thymeleaf (Server-side rendering), HTML5, CSS3
* **Base de Datos:** PostgreSQL
* **Seguridad:** Spring Security
* **Herramientas:** Maven, Lombok, IntelliJ IDEA

## 📋 Funcionalidades Principales
1.  **Login Corporativo:** Autenticación segura para empleados.
2.  **Panel de Solicitudes:** 6 opciones rápidas para servicios frecuentes.
3.  **Historial de Registros:** Almacenamiento detallado de quién solicitó qué y cuándo.
4.  **Reportes (Próximamente):** Exportación de data para análisis de RRHH.

## ⚙️ Configuración Local

1.  Clonar el repositorio.
2.  Crear base de datos PostgreSQL llamada `rrhh_tickets_db`.
3.  Configurar credenciales en `src/main/resources/application.properties`.
4.  Ejecutar `mvn spring-boot:run`.

---
Desarrollado por Valentin Fernandez - 2026