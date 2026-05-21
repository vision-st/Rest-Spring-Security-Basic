# Primer Proyecto REST con Spring Boot

## Descripción del proyecto

Este proyecto corresponde a una primera aproximación práctica al desarrollo de una **API REST utilizando Spring Boot**.

El objetivo principal es comprender cómo se estructura una aplicación backend moderna, cómo se exponen endpoints HTTP y cómo se organizan las responsabilidades dentro de un proyecto Java usando buenas prácticas iniciales.

A través de este proyecto, los estudiantes podrán construir una API sencilla, probar sus endpoints y comenzar a familiarizarse con conceptos fundamentales del desarrollo backend profesional.

---

## Objetivo de aprendizaje

Al finalizar este proyecto, el estudiante será capaz de:

- Comprender qué es una API REST.
- Crear un proyecto base con Spring Boot.
- Construir controladores REST usando `@RestController`.
- Exponer endpoints mediante métodos HTTP como `GET`, `POST`, `PUT` y `DELETE`.
- Entender la separación básica entre controlador, modelo y servicio.
- Probar endpoints utilizando herramientas como Postman o Thunder Client.
- Reconocer la importancia de una estructura de carpetas clara y mantenible.

---

## Tecnologías utilizadas

Este proyecto utiliza las siguientes tecnologías:

- Java 17 o superior
- Spring Boot
- Spring Web
- Maven
- Postman o Thunder Client
- IDE recomendado: IntelliJ IDEA, Eclipse STS o Visual Studio Code

---

## ¿Qué es una API REST?

Una API REST es una forma de permitir que diferentes aplicaciones se comuniquen entre sí mediante el protocolo HTTP.

Por ejemplo, una aplicación frontend, una app móvil o incluso otro sistema externo puede consumir los datos entregados por nuestra API.

En una API REST normalmente trabajamos con recursos. Un recurso puede ser, por ejemplo:

- Usuarios
- Productos
- Robots
- Cursos
- Estudiantes
- Tareas

Cada recurso se manipula utilizando métodos HTTP.

---

## Métodos HTTP principales

| Método | Uso principal | Ejemplo |
|---|---|---|
| GET | Obtener información | Listar todos los robots |
| POST | Crear un nuevo recurso | Crear un nuevo robot |
| PUT | Actualizar un recurso existente | Modificar los datos de un robot |
| DELETE | Eliminar un recurso | Eliminar un robot por su ID |

---

## Estructura sugerida del proyecto

```text
src
└── main
    └── java
        └── com.ejemplo.primerapi
            ├── controller
            │   └── RobotController.java
            ├── model
            │   └── Robot.java
            ├── service
            │   └── RobotService.java
            └── PrimerApiApplication.java