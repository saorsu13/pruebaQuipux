# Playlist App - README

## Descripción del Proyecto
**Playlist App** es una aplicación web que permite a los usuarios gestionar listas de reproducción musicales. Los usuarios pueden registrarse, iniciar sesión, crear, modificar y eliminar playlists, además de consultar géneros musicales desde la API de Spotify. La aplicación utiliza autenticación basada en **JWT (JSON Web Tokens)** para proteger los endpoints.

## Tecnologías Utilizadas

### Backend:
- **Spring Boot (v3.3.3)**: Framework de Java utilizado para el desarrollo del backend, gestionando la lógica de negocio, seguridad y acceso a la base de datos.
- **Spring Security**: Módulo de seguridad para proteger los endpoints y manejar la autenticación con JWT.
- **JWT (Json Web Token)**: Utilizado para la autenticación de usuarios.
- **JPA/Hibernate**: Para la gestión de la persistencia de datos en la base de datos PostgreSQL.
- **PostgreSQL**: Sistema de gestión de bases de datos relacional utilizado para almacenar información de usuarios, playlists y canciones.
- **Maven**: Herramienta de gestión de dependencias y construcción del proyecto.

### Frontend:
- **Angular**: Framework de desarrollo frontend basado en TypeScript, utilizado para la creación de la interfaz de usuario.

### Base de Datos:
- **PostgreSQL**: Base de datos relacional utilizada para almacenar la información de los usuarios y sus listas de reproducción.

## Instalación y Configuración del Proyecto

### Prerrequisitos
Para ejecutar el proyecto localmente, asegúrate de tener instalados los siguientes programas:

- **Java 17**
- **Maven 3.8+**
- **Node.js v14+ y npm** (para el frontend en Angular)
- **PostgreSQL** (Base de datos)

### Configuración del Backend

1. **Clona el repositorio**:

   ```bash
   git clone https://github.com/tu-repositorio/playlist-app.git
   cd playlist-app

2. **Configura la base de datos PostgreSQL**
   Crea una base de datos llamada postgres o ajusta el archivo de configuración application.properties según tu configuración local.

spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=123456789
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.show-sql=true

## Endpoints de la API

**Autenticación**

**Registro de usuarios:**
POST /register
Registra un nuevo usuario con username, password, y email.

**Login:**
POST /login
Inicia sesión con username y password, devolviendo un token JWT si las credenciales son correctas.

**Playlists**

**Crear Playlist:**
POST /lists/add
Crea una nueva playlist proporcionando un name y una description opcional.

**Obtener todas las Playlists:**
GET /lists/getPlaylist
Devuelve una lista de todas las playlists creadas.

**Obtener Playlist por Nombre:**
GET /lists/getBy/{listName}
Devuelve una playlist específica basada en el nombre.

**Eliminar Playlist:**
DELETE /lists/delete/{listName}
Elimina una playlist según el nombre.

**Géneros de Spotify**

**Obtener géneros:**
GET /lists/genres
Devuelve una lista de géneros musicales obtenida de la API de Spotify. Se requiere un token de autorización en el header.