# Microservicio de Gestión de Rutas y Paradas

## Descripción
Este repositorio contiene el código fuente del microservicio de gestión de rutas y paradas, desarrollado en Spring Boot.

## Estructura de Ramas y Flujo de Trabajo
Para mantener un flujo de desarrollo organizado, se siguen las siguientes reglas:

### Ramas Principales
- **main** → Solo debe contener versiones estables y en producción.
- **release** → Rama utilizada para QA y pruebas antes de pasar a `main`.
- **develop** → Rama principal de desarrollo, donde se integran las funcionalidades en curso.

## ¿Qué se hizo en este proyecto?

### 1. **Configuración del Proyecto**
- Se utilizó **Spring Boot 3.4.4** como base del proyecto.
- Se configuró **Maven** como herramienta de construcción, incluyendo dependencias clave como:
  - `spring-boot-starter-web`: Para construir la API REST.
  - `spring-boot-starter-data-jpa`: Para la integración con la base de datos.
  - `spring-cloud-starter-netflix-eureka-client`: Para habilitar el descubrimiento de servicios.
  - `springdoc-openapi-starter-webmvc-ui`: Para generar documentación de la API con OpenAPI/Swagger.
  - `lombok`: Para reducir el código repetitivo (getters, setters, etc.).

### 2. **Gestión de la Base de Datos**
- Se configuró **PostgreSQL** como base de datos principal.
- Se definieron las propiedades de conexión en el archivo `application.properties`, permitiendo la personalización mediante variables de entorno:
  - `DB_HOST`, `DB_PORT`, `DB_NAME`, `DB_USER`, `DB_PASSWORD`.

### 3. **API REST**
- Se implementaron controladores para manejar las operaciones CRUD (Crear, Leer, Actualizar, Eliminar) de rutas.
- Se validaron los datos de entrada utilizando **Spring Boot Validation**.
- Se generó documentación automática de la API con **SpringDoc** y OpenAPI, accesible en `/v3/api-docs`.

### 4. **Descubrimiento de Servicios**
- Se configuró el cliente **Eureka** para registrar este servicio en un servidor Eureka.
- Propiedades clave configuradas:
  - `eureka.client.service-url.defaultZone`: URL del servidor Eureka.
  - `eureka.client.register-with-eureka=true`: Permite registrar el servicio.
  - `eureka.client.fetch-registry=true`: Habilita la obtención de información de otros servicios.

### 5. **Swagger y OpenAPI**
- Se integró **SpringDoc** para generar documentación de la API.

### 6. **Estructura del Proyecto**
El proyecto sigue la estructura estándar de Spring Boot:
- **`src/main/java`**: Código fuente principal.
  - **Controladores**: Manejan las solicitudes HTTP.
  - **Servicios**: Contienen la lógica de negocio.
  - **Repositorios**: Interactúan con la base de datos.
  - **Modelos**: Representan las entidades de la base de datos.
- **`src/main/resources`**: Archivos de configuración.
  - `application.properties`: Configuración de la aplicación.
