Actividad-Final-Integrada-UT6-y-UT7_Saul_Samuele

Este proyecto es una API RESTful desarrollada con Spring Boot para gestionar notas almacenadas en una base de datos MySQL. Permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre las notas, las cuales contienen un título, contenido y fecha de creación. Utiliza Hibernate como ORM para la interacción con la base de datos y HikariCP como pool de conexiones para optimizar el rendimiento.

Está diseñado para ser una aplicación sencilla que pueda servir como base para sistemas más complejos de gestión de información.

Estructura del proyecto:

src/
├── main/
│ ├── java/
│ │ └── com/tu_usuario/notas/
│ │ ├── controller/  -Controladores REST
│ │ ├── model/ Modelos
│ │ ├── repository/ -Repositorios JPA
│ │ ├── service/ -Servicios
│ │ └── NotasApiApplication.java
│ └── resources/
│ ├── application.properties -Configuración de la app

Para poder usarlo debe editar: application.properties con el puerto y datos correctos para poder conectarlo a el MySQL Workbench. Una vez configurado, puede administrar la base de datos con MySQL Workbench utilizando los mismos datos de conexión para visualizar y modificar las tablas y registros.

Para poder usar el Postman, debe ejecutar y usar los diferentes checkpoints:

-UsuarioController /api/v1/usuarios
 | GET /usuarios
 | GET /usuarios/{id}
 | POST /usuarios
 | PUT /usuarios/{id}
 | DELETE /usuarios/{id}
  
-UsuarioController /api/v2
  POST /sign-in
  
-NotaController /api/v1/notas
  | GET /notas
  | GET /notas?usuarioId={id}&order={asc|desc}
  |GET /notas/{id}
  | POST /notas?usuarioId={id}
  | PUT /notas/{id}
  | DELETE /notas/{id}

Y en raw escribir codigo JSON y ejecutarlo según el checkpoint. 

https://github.com/Saulinho55/Actividad-Final-Integrada-UT6-y-UT7_Saul_Samuele
