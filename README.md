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
├── src/test/java/
│     └── com.nisum.service.impl
│       ├── user
│            └── userFaker.java
│       └── UserServiceImplTest.java
│
├── .gitignore
├── build.gradle
├── gradlew.bat
├── HELP.md
├── README.md
└── settings.gradle

# Comenzando 🚀
  Para ejecutar el proyecto debemos seguir los siguientes pasos

# Pre-requisitos 📋
  GRADLE GIT JAVA 8

# Instalación 🔧
  clonamos el proyecto en nuestro computador

# git clone https://github.com/oscarmayor0211/createUser.git
  Ya despues debemos colocarnos en la carpeta del proyecto

  gradle clean
  gradle build
  gradle bootRun

# Como probar el proyecto 🔩
  Debemos primeramente con un herramienta de test como postman

# POST - http://localhost:8080/user
  importante enviar el json segun las especificaciones ejemplo:

{
    "name": "Oscar Mayor", 
    "email": "oscarmayor0211@gmail.com", 
    "password": "@Gildardo29",
    "phones": [ 
        {
            "number": "312435567",
            "cityCode": "602",
            "countryCode": "+57"
        } 
    ] 
}
  Lo que devolvera ejemplo:

{
    "id": "c167a63c-bd4d-4d8c-bb4b-14b5cf7c095b",
    "created":"2022-05-26 22:10:55",
    "modified": null,
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJjMTY3YTYzYy1iZDRkLTRkOGMtYmI0Yi0xNGI1Y2Y3YzA5NWIifQ.4acftOWt73jR07NnGl4nyJVNu9GELqC_fvLGLUVW73Q",
    "last_login": "2022-05-26 22:10:55",
    "active": true
}

# Listado de usuarios ⌨️
  Ya despues de registrado el usuario pordemos ingresar n usuarios

# GET - http://localhost:8080/
  Con este endpoint podemos tener todos los usuarios creados ejemplo de resultado:

# Respuesta:
{
        "id": "c167a63c-bd4d-4d8c-bb4b-14b5cf7c095b",
        "name": "Oscar Mayor",
        "email": "oscarmayor0211@gmail.com",
        "created":"2022-05-26 22:10:55",
        "last_login": "2022-05-26 22:10:55",
        "password": "@Gildardo29",
       "phones": [ 
        {
            "number": "312435567",
            "cityCode": "602",
            "countryCode": "+57"
        } 
        ]
        "modified": null, 
        "accessToken":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJjMTY3YTYzYy1iZDRkLTRkOGMtYmI0Yi0xNGI1Y2Y3YzA5NWIifQ.4acftOWt73jR07NnGl4nyJVN
        u9GELqC_fvLGLUVW73Q",
        "active": true
    }

Validacion de usuario 📦
Podemos validar el usuario en el siguiente endpoint

# POST - http://localhost:8080/user/access
Debemos ingresar el body del usuario que deseamos validar ejemplo

{
    "email" :"oscarmayor0211@gmail.com",
    "password" :"@Gildardo29"
}

Y nos devolvera como ejemplo:

si no fuera valido

{
    "mensaje": "Access Invalid"
}
o si fuera valido algo como esto

{
    "id": "575c6bd6-6a19-4b3e-b959-751ba6571ca2",
    "created": "2022-05-26 20:29:54",
    "modified": null,
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiI1NzVjNmJkNi02YTE5LTRiM2UtYjk1OS03NTFiYTY1NzFjYTIifQ.LY3PxCNnf6EvONGfjjFin_5M1h4DsY7EfslMdlDGn5A",
    "last_login": "2022-05-26 22:19:24",
    "active": true
}

# Para modificar 
PUT - http://localhost:8080/user/{id}

Respuesta
{
    "id": "575c6bd6-6a19-4b3e-b959-751ba6571ca2",
    "name": "Oscar Eduardo Mayor",
    "email": "oscarmayor0211@gmail.com",
    "last_login": "2022-05-26 22:19:24",
    "password": "@MayorJaramillo02",
    "phones": [
        {
            "id": 7,
            "number": "3182747662",
            "cityCode": "602",
            "countryCode": "+57",
            "user": null
        }
    ],
    "created": "created": "2022-05-26 20:29:54",
    "modified": 2022-05-26 22:23:58",
    "accessToken": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiI1NzVjNmJkNi02YTE5LTRiM2UtYjk1OS03NTFiYTY1NzFjYTIifQ.LY3PxCNnf6EvONGfjjFin_5M1h4DsY7EfslMdlDGn5A",
    "active": true
}

# Se implemento SWAGGER  http://localhost:8080/swagger-ui/index.html#/

# Construido con 🛠️

## Se utilizo lo siguiente

## [Proyecto - Spring boot]
## [Librerias - Gradle]
## [Base de datos - mysql]
## [JPA - Hibernate]
# Autor ✒️
## Oscar Eduardo Mayor Jaramillo
