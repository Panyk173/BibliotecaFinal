Sistema de Gestión de Biblioteca
================================

Descripción
-----------
Este proyecto es un sistema de gestión de biblioteca desarrollado en Java utilizando JPA, Swing y un enfoque basado en MVC (Modelo-Vista-Controlador). El sistema permite gestionar libros, ejemplares, usuarios y préstamos con reglas de negocio específicas, como penalizaciones y límites en los préstamos.

Características principales
---------------------------
1. Gestión de Libros:
   - Registrar libros con los campos: ISBN13, título, autor.
   - Buscar libros por autor.

2. Gestión de Ejemplares:
   - Registrar ejemplares asociados a un libro.
   - Controlar el stock disponible (estado: Disponible, Prestado, Dañado).

3. Gestión de Usuarios:
   - Registrar usuarios (DNI, nombre, email, password, tipo).
   - Penalizar usuarios por devoluciones fuera de plazo.
   - Diferenciar entre usuarios normales y administradores.

4. Gestión de Préstamos:
   - Registrar préstamos con fecha de inicio y calcular dinámicamente la fecha límite (15 días).
   - Registrar devoluciones y actualizar el estado del ejemplar.
   - Reglas de validación:
     - Un usuario no puede tener más de 3 préstamos activos.
     - No se pueden prestar ejemplares no disponibles.
     - Usuarios penalizados no pueden realizar nuevos préstamos.

5. Interfaces gráficas:
   - **Administrador**: Gestión completa de libros, ejemplares, usuarios y préstamos.
   - **Usuario normal**: Visualización de sus préstamos activos.

Tecnologías utilizadas
----------------------
- Java 17+
- Swing para la interfaz gráfica
- JPA (Jakarta Persistence API) con Hibernate como implementación
- Base de datos MariaDB

Requisitos del sistema
----------------------
- Java Development Kit (JDK) 17 o superior
- MariaDB instalado y configurado
- IDE compatible con Java (Eclipse, IntelliJ IDEA, NetBeans)

Configuración de la base de datos
---------------------------------
1. Crear la base de datos:
   - Ejecutar el script SQL proporcionado para crear las tablas necesarias.
   - Nombre de la base de datos: biblioteca

2. Configuración en persistence.xml:
   - URL: jdbc:mariadb://localhost:3306/biblioteca
   - Usuario: root
   - Contraseña: (vacío, o especificar si tienes una contraseña configurada)

Estructura del proyecto
-----------------------
src/
├── controlador/
│   ├── UsuarioControlador.java
│   ├── LibroControlador.java
│   ├── EjemplarControlador.java
│   ├── PrestamoControlador.java
├── modelo/
│   ├── dto/
│   │   ├── Usuario.java
│   │   ├── Libro.java
│   │   ├── Ejemplar.java
│   │   ├── Prestamo.java
│   ├── dao/
│   │   ├── GenericoDAO.java
│   │   ├── UsuarioDAO.java
│   │   ├── LibroDAO.java
│   │   ├── EjemplarDAO.java
│   │   ├── PrestamoDAO.java
│   ├── validacion/
│       ├── UsuarioValidacion.java
│       ├── LibroValidacion.java
│       ├── EjemplarValidacion.java
│       ├── PrestamoValidacion.java
├── vista/
│   ├── LoginDialogo.java
│   ├── InterfazUsuarioAdmin.java
│   ├── InterfazUsuarioNormal.java
│   ├── paneles/
│       ├── PanelUsuario.java
│       ├── PanelLibro.java
│       ├── PanelEjemplar.java
│       ├── PanelPrestamo.java
├── AplicacionPrincipal.java

Cómo ejecutar el proyecto
-------------------------
1. Clonar el repositorio o descargar el código fuente.
2. Configurar la base de datos según las instrucciones.
3. Abrir el proyecto en tu IDE favorito.
4. Ejecutar la clase `AplicacionPrincipal` para iniciar la aplicación.

Créditos
--------
- Desarrollo: [Alberto (Panyk)]
- Inspiración: Requisitos del sistema de gestión de biblioteca.

Licencia
--------
Este proyecto es de uso educativo y no tiene fines comerciales.
