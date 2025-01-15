# Gestor de Productos y Usuarios

Una aplicación web desarrollada en Java que permite gestionar productos y usuarios a través de operaciones CRUD. Incluye autenticación y un diseño responsivo utilizando Bootstrap 5.


## Características

- **Gestión de Productos:** Crear, editar, eliminar y listar productos con sus categorias.
- **Gestión de Usuarios:** Crear, editar, eliminar y listar usuarios. Acceso restringido con inicio de sesión.
- **Interfaz Adaptable:** Diseñado en Bootstrap 5.
- **Capa de Persistencia:** Gestión eficiente de datos utilizando repositorios para conexión con la base de datos.

## Estructura del Proyecto
- El proyecto sigue una arquitectura basada en MVC (Model-View-Controller), organizada en los siguientes paquetes:

### 1. Controllers
- Los controladores gestionan las solicitudes HTTP entrantes y determinan la vista o respuesta adecuada para el cliente.

- **Detalles:**
   - Manejan las rutas de la aplicación.
   - Interactúan con los servicios y modelos para procesar datos.
   - En este proyecto, los controladores están implementados como servlets o utilizando anotaciones de Spring MVC (@Controller).

### 2. Filters
- Los filtros interceptan solicitudes y respuestas, aplicando funcionalidades adicionales como autenticación y logging.

- **Detalles:**
   - Útil para controlar el acceso a recursos y personalizar respuestas de error.
   - Implementados como clases que extienden Filter.

### 3. Listeners
- Escuchan eventos del ciclo de vida de la aplicación, como la creación o destrucción de sesiones.

- **Detalles:**
   - Implementados con interfaces como HttpSessionListener y ServletContextListener.
   - Utilizados para gestionar recursos, logging, y estadísticas.


### 4. Models
- Representan los datos de la aplicación y su lógica de negocio.

- **Detalles:**
   - Contienen las definiciones de entidades y objetos de transferencia de datos (DTOs).
   - Mantienen la lógica de negocio separada de la capa de presentación.

### 5. Repositories
- Gestionan la interacción con la base de datos o sistemas de almacenamiento de datos.

- **Detalles:**
  - Implementados utilizando frameworks como JPA/Hibernate.
  - Encargados de realizar operaciones CRUD.
  - Proveen abstracción para facilitar pruebas y cambios en la persistencia.

### 6. Services
- Encapsulan la lógica de negocio y coordinan entre los controladores y repositorios.

- **Detalles:**
   - Aplican reglas de negocio.
   - Transforman y validan datos antes de enviarlos a la capa de persistencia.
   - Ayudan a mantener los controladores ligeros y enfocados.

###  7. Util
- Contiene herramientas y utilidades comunes.

- **Detalles:**
   - Métodos estáticos para validaciones, manejo de fechas, etc.
   - En este proyecto, gestiona la conexión a la base de datos.

###  8. WebApp
- Recursos y configuraciones específicas de la aplicación web.

- **Detalles:**
   - Incluye vistas (JSP/HTML), recursos estáticos (CSS/JS), y configuraciones web.
   - Preparado para despliegue en servidores como Tomcat.


## 🛠️ Requisitos de Instalación
**Entorno de Desarrollo:**
- JDK 11 o superior.
- IntelliJ IDEA
- Servidor Tomcat 10
- Base de datos MySQL

## Pasos para Configuración:
Clona este repositorio:
bash
Copiar código
git clone https://github.com/tu-usuario/gestor-productos-usuarios.git

Configura la base de datos en la clase de conexion **ConexionBaseDatos**
Copiar código
```bash
    private static String url = "jdbc:mysql://localhost:Colocar el puerto/el nombre de tu Base de datos?serverTimezone=America/Santiago";
    private static String username = "Coloca tu username";
    private static String password = "Coloca tu password";
bash

