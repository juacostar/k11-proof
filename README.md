# k11-proof

Prueba elaborada para aspirar al cargo de desarrollador backend en K11 Technologies. API creada con el lenguaje de programación JAVA en su 11va versión junto el framework SpringBoot en su versión 2.7.5, además usando la herramienta de automatización de independencias del proyecto denominada Gradle. Como tecnología estándar de almacenamiento se utilizó PostgreSQL como motor de base de datos relacional para el almacenamiento de los usuarios.

## Arquitectura
Este proyecto se procuró implementar buenas prácticas de diseño de software basado en los principios SOLID para el desarrollo de software. Se utilizó el patrón de diseño basado en capas para poder construir la API. Además de ello se utilizaron diferentes librerías complementarias como RestTemplate para hacer el llenado de los datos a partir de la API escrita en el documento y Swagger para generar de la documentación de los modelos y servicios incluidos en la API.

## Instalación
Para poder ejecutar la api es necesario:
1. Tener PostgreSQL para alamacenar los datos.
2. Tener un usario postgres con contraseña root que es el usuario por defecto que tiene la API para acceder a la base de datos.
3. Tener una base de datos creada con el nombre de k11
4. Tener Java para poder ejecutar el archivo .jar adjuntado en el repositorio
5. Tener una herramienta para poder consultar los servicios de la API

## Consideraciones
1. Para poder llenar la base de datos con los datos que se eencentran en la API del documento, se creó un servicio k11/users/fill, el cual llena con una de las dos páginas de la API que contienen datos (solo se encontraron las dos primeras páginas con datos de usuarios).
