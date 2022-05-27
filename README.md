# RegistroUsuario 
Creacion y validacion de usuarios

Diagrama de proyecto

createUser/
│
├── src/main/java/
│   └── com.nisum.createUser
│       ├── controller
│       │   └── userController.java
│       │
│       ├── exception
│       │   └── AccessInvalid.java
│       │   └── EmailDupedInvalid.java
│       │   └── Error.java
│       │   └── ErrorHandler.java
│       │   └── PasswordFormatException.java
│       │
│       ├── jwtAdapter
│       │   ├── JwtAdapter.java
│       │   └── JwtConfig.java
│       │
│       ├── models
│       │   └── Phone.java
│       │   └── User.java
│       │
│       ├── repository
│       │   └── userRepository.java
│       │
│       ├── response
│       │   └── userResponse.java
│       │   
│       ├── service
│       │   └── UserService.java
│       │   └── impl
│       │       └──UserServiceImpl.java
│       ├── swagger
│       │   └── SwaggerConfig.java
│       │
├── src/main/resources/
│   └── application.properties
│
├── .gitignore
├── build.gradle
├── gradlew.bat
├── HELP.md
├── README.md
└── settings.gradle
Comenzando 🚀
Para ejecutar el proyecto debemos seguir los siguientes pasos

Pre-requisitos 📋
GRADLE GIT JAVA 8

Instalación 🔧
clonamos el proyecto en nuestro computador

git clone https://github.com/baemestrada-source/prueba-nisum.git
Ya despues debemos colocarnos en la carpeta del proyecto

mvn install
Ejecutando el proyecto ⚙️
Ya con las librerias descargadas de maven podemos ejecutar con la siguiente instruccion

mvn spring-boot:run
Como probar el proyecto 🔩
Debemos primeramente con un herramienta de test como postman

POST - http://localhost:8080/api/usuarios
importante enviar el json segun las especificaciones ejemplo:

{
"name": "Estrada",
"email": "bestrada3@gmail.com",
"password": "123",
"phones": [
{
"number": "12345",
"citycode": "Guate",
"contrycode": "57"
}
]
}
Lo que devolvera ejemplo:

{
"id": "9c148eb1-e960-42af-9237-e3904e296c84",
"created": "2022-01-14T19:04:56.5026313",
"modified": null,
"last_login": "2022-01-14T19:04:56.5026313",
"token": "$2a$10$KOftWrk8pv5yYBX3SctpT.Ql3Eg5YLwRxHLEKloPGlyPeWbcoo5aa",
"isactive": true
}
Listado de usuarios ⌨️
Ya despues de registrado el usuario pordemos ingresar n usuarios

GET - http://localhost:8080/api/usuarios
Con este endpoint podemos tener todos los usuarios creados ejemplo de resultado:

Respuesta:

[
{
"id": "9c148eb1-e960-42af-9237-e3904e296c84",
"name": "Estrada",
"email": "bestrada3@gmail.com",
"password": "$2a$10$SrwiksFqy7zOy3bgMYYWKukh10KE75khgDKjJnrPxdxJ95KhMnSHO",
"created": "2022-01-14T19:04:56.502631",
"modified": null,
"last_login": "2022-01-14T19:04:56.502631",
"token": "$2a$10$KOftWrk8pv5yYBX3SctpT.Ql3Eg5YLwRxHLEKloPGlyPeWbcoo5aa",
"isactive": true,
"phones": [
{
"number": "12345",
"cityCode": "Guate",
"contryCode": "57"
}
]
},
{
"id": "6c3747ec-626c-4ff2-badd-4463e6ce9f3a",
"name": "Moreira",
"email": "bestrada123@gmail.com",
"password": "$2a$10$sD3n8urK0k6bN61do2NEHOkO5NnzU7d/xKZOn67CH9KcdjON1EkW2",
"created": "2022-01-14T19:21:14.849913",
"modified": null,
"last_login": "2022-01-14T19:21:14.849913",
"token": "$2a$10$Ozpv51gAfddg5GgYcqwC/emIGJ.Yn3s.GClm8tpBpQG1Lz87IGvFC",
"isactive": true,
"phones": [
{
"number": "12345",
"cityCode": "Guate",
"contryCode": "57"
}
]
}
]
Validacion de usuario 📦
Podemos validar el usuario en el siguiente endpoint

POST - http://localhost:8080/api/access
Debemos ingresar el body del usuario que deseamos validar ejemplo

{
"name": "Estrada",
"email": "bestrada2@gmail.com",
"password": "123",
"phones": [
{
"number": "12345",
"citycode": "Guate",
"contrycode": "57"
}
]
}
Y nos devolvera como ejemplo:

si no fuera valido

{
"error": "Usuario y/o clave invalidos"
}
o si fuera valido algo como esto

{
"id": "9c148eb1-e960-42af-9237-e3904e296c84",
"created": "2022-01-14T19:04:56.502631",
"modified": null,
"last_login": "2022-01-14T19:04:56.502631",
"token": "$2a$10$KOftWrk8pv5yYBX3SctpT.Ql3Eg5YLwRxHLEKloPGlyPeWbcoo5aa",
"isactive": true
}
Validacion de ID y TOKEN 📦
Podemos validar el usuario y el token su relacion en el siguiente endpoint en donde debemos incluir el id de usuario que deseamos validar

POST - http://localhost:8080/api/token?id=def697bb-f751-4b82-bd44-0f541156d47c
Como variable header o de encabezado debemos colocar una con nombre token

key: token value: $2a$10$9HsZxpSWZICa5KP4ns5afON71Vpyj/mqc8BF3cY8WIQqchBB30k9W
Y nos devolvera como ejemplo:

si no fuera valido

{
"error": "No esta autorizado"
}
o si fuera valido algo como esto

{
"id": "9c148eb1-e960-42af-9237-e3904e296c84",
"created": "2022-01-14T19:04:56.502631",
"modified": null,
"last_login": "2022-01-14T19:04:56.502631",
"token": "$2a$10$KOftWrk8pv5yYBX3SctpT.Ql3Eg5YLwRxHLEKloPGlyPeWbcoo5aa",
"isactive": true
}
Construido con 🛠️
Se utilizo lo siguiente

[Proyecto - Spring boot]
[Librerias - Maven]
[Base de datos - H2]
[JPA - Hibernate]
Autor ✒️
Byron Arturo Estrada Moreira - Prueba tecnica NISUM"# RegistroUsuario" 
