Playlist App - README
Descripción del Proyecto
Playlist App es una aplicación web que permite a los usuarios gestionar listas de reproducción musicales. Los usuarios pueden registrarse, iniciar sesión, crear, modificar y eliminar playlists, además de consultar géneros musicales desde la API de Spotify. La aplicación utiliza autenticación basada en JWT (JSON Web Tokens) para proteger los endpoints.

Tecnologías Utilizadas
Backend:
Spring Boot (v3.3.3): Framework de Java utilizado para el desarrollo del backend, gestionando la lógica de negocio, seguridad y acceso a la base de datos.
Spring Security: Módulo de seguridad para proteger los endpoints y manejar la autenticación con JWT.
JWT (Json Web Token): Utilizado para la autenticación de usuarios.
JPA/Hibernate: Para la gestión de la persistencia de datos en la base de datos PostgreSQL.
PostgreSQL: Sistema de gestión de bases de datos relacional utilizado para almacenar información de usuarios, playlists y canciones.
Maven: Herramienta de gestión de dependencias y construcción del proyecto.
Frontend:
Angular: Framework de desarrollo frontend basado en TypeScript, utilizado para la creación de la interfaz de usuario.
Base de Datos:
PostgreSQL: Base de datos relacional utilizada para almacenar la información de los usuarios y sus listas de reproducción.
Instalación y Configuración del Proyecto
Prerrequisitos
Para ejecutar el proyecto localmente, asegúrate de tener instalados los siguientes programas:

Java 17
Maven 3.8+
Node.js v14+ y npm (para el frontend en Angular)
PostgreSQL (Base de datos)
Configuración del Backend
Clona el repositorio:

bash
Copiar código
git clone https://github.com/tu-repositorio/playlist-app.git
cd playlist-app
Configura la base de datos PostgreSQL:
Asegúrate de tener PostgreSQL instalado y ejecutando en tu sistema. Crea una base de datos llamada postgres o ajusta el archivo de configuración application.properties según tu configuración local.

properties
Copiar código
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=123456789
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.show-sql=true
Ejecuta el backend:
Ejecuta el siguiente comando para compilar y ejecutar el proyecto:

bash
Copiar código
mvn spring-boot:run
Configuración del Frontend (Angular)
Navega al directorio del frontend:

bash
Copiar código
cd playlist-app-frontend
Instala las dependencias de Angular:

bash
Copiar código
npm install
Configura el archivo environment.ts para apuntar al backend:

typescript
Copiar código
export const environment = {
  production: false,
  apiUrl: 'http://localhost:8080'
};
Ejecuta la aplicación de Angular:

bash
Copiar código
ng serve
La aplicación estará disponible en http://localhost:4200.

Endpoints de la API
Autenticación
Registro de usuarios:
POST /register
Registra un nuevo usuario con username, password, y email.

Login:
POST /login
Inicia sesión con username y password, devolviendo un token JWT si las credenciales son correctas.

Playlists
Crear Playlist:
POST /lists/add
Crea una nueva playlist proporcionando un name y una description opcional.

Obtener todas las Playlists:
GET /lists/getPlaylist
Devuelve una lista de todas las playlists creadas.

Obtener Playlist por Nombre:
GET /lists/getBy/{listName}
Devuelve una playlist específica basada en el nombre.

Eliminar Playlist:
DELETE /lists/delete/{listName}
Elimina una playlist según el nombre.

Géneros de Spotify
Obtener géneros:
GET /lists/genres
Devuelve una lista de géneros musicales obtenida de la API de Spotify. Se requiere un token de autorización en el header.
Estructura de la Base de Datos
La base de datos contiene las siguientes tablas:

sql
Copiar código
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE playlists (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    user_id INTEGER REFERENCES users(id)
);

CREATE TABLE songs (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    artist VARCHAR(255) NOT NULL,
    album VARCHAR(255),
    genre VARCHAR(50),
    release_year INTEGER,
    playlist_id INTEGER REFERENCES playlists(id)
);
JWT y Seguridad
El proyecto utiliza JWT para la autenticación. Los tokens generados expiran después de 24 horas y deben ser enviados en cada solicitud protegida usando el header Authorization: Bearer <token>. El sistema valida el token antes de procesar cualquier operación en los endpoints asegurados.

Ejecución de Pruebas
El proyecto incluye pruebas unitarias que pueden ser ejecutadas con el siguiente comando:

bash
Copiar código
mvn test
