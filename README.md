# Primer Proyecto con Spring Security

## Descripción del proyecto

Este proyecto corresponde a una primera aproximación práctica al uso de **Spring Security** dentro de una aplicación Spring Boot.

El objetivo principal es comprender cómo una aplicación backend puede proteger ciertos endpoints, permitir el acceso público a otros y exigir autenticación cuando el usuario intenta ingresar a zonas restringidas.

En este proyecto no buscamos construir una API REST compleja. El protagonista es **Spring Security** y su capacidad para controlar el acceso a diferentes rutas de la aplicación.

---

## Objetivo de aprendizaje

Al finalizar este proyecto, el estudiante será capaz de:

- Comprender el rol de Spring Security dentro de una aplicación Spring Boot.
- Diferenciar entre rutas públicas y rutas protegidas.
- Configurar una clase de seguridad usando `SecurityFilterChain`.
- Permitir acceso libre a ciertos endpoints mediante `permitAll()`.
- Exigir autenticación para el resto de las rutas usando `authenticated()`.
- Probar autenticación mediante formulario de login.
- Probar autenticación básica HTTP.
- Comprender el uso inicial de credenciales configuradas en `application.properties`.

---

## Tecnologías utilizadas

Este proyecto utiliza las siguientes tecnologías:

- Java 17 o superior
- Spring Boot
- Spring Web
- Spring Security
- Maven
- Postman, navegador web o Thunder Client
- IDE recomendado: IntelliJ IDEA, Spring Tool Suite o Visual Studio Code

---

## ¿Qué es Spring Security?

Spring Security es un framework que permite agregar seguridad a aplicaciones Java desarrolladas con Spring.

Entre sus principales responsabilidades se encuentran:

- Autenticación de usuarios.
- Autorización de acceso a recursos.
- Protección de rutas.
- Configuración de login.
- Manejo de sesiones.
- Integración con distintos mecanismos de seguridad.

En palabras simples, Spring Security permite responder preguntas como:

- ¿Quién está intentando ingresar?
- ¿Tiene permiso para acceder?
- ¿Qué rutas pueden ser públicas?
- ¿Qué rutas deben estar protegidas?

---

## Idea principal del proyecto

La aplicación tendrá diferentes endpoints. Algunos serán públicos y podrán ser visitados por cualquier usuario. Otros estarán protegidos y requerirán iniciar sesión.

El flujo general será el siguiente:

```text
Usuario solicita una ruta
        ↓
Spring Security intercepta la solicitud
        ↓
Revisa si la ruta es pública o protegida
        ↓
Permite el acceso o solicita autenticación
        ↓
El controlador responde al usuario
```

---

## Estructura sugerida del proyecto

```text
src
└── main
    ├── java
    │   └── com.duoc.seguridad
    │       ├── config
    │       │   └── SecurityConfig.java
    │       ├── controller
    │       │   └── CustomControllerSecurity.java
    │       └── SeguridadApplication.java
    │
    └── resources
        └── application.properties
```

---

## Clase de configuración de seguridad

La clase `SecurityConfig` es una de las partes más importantes del proyecto.

```java
package com.duoc.seguridad.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/app/index_normal", "/app/info").permitAll()
                        .anyRequest().authenticated()
        ).formLogin(withDefaults()).httpBasic(withDefaults());

        return http.build();
    }
}
```

---

## Explicación de la configuración

### `@Configuration`

Esta anotación indica que la clase contiene configuración para Spring.

```java
@Configuration
```

Spring detecta esta clase al iniciar la aplicación y la utiliza para configurar el comportamiento de seguridad.

---

### `@Bean`

```java
@Bean
```

Indica que el método devuelve un objeto que será administrado por Spring.

En este caso, estamos registrando un objeto de tipo:

```java
SecurityFilterChain
```

---

### `SecurityFilterChain`

```java
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
```

La `SecurityFilterChain` define las reglas de seguridad que se aplicarán a las solicitudes HTTP.

Cada vez que un usuario intenta acceder a una ruta, Spring Security revisa esta cadena de filtros antes de permitir o bloquear el acceso.

---

### `authorizeHttpRequests`

```java
http.authorizeHttpRequests(auth -> auth
```

Esta sección permite definir qué rutas estarán disponibles públicamente y cuáles necesitarán autenticación.

---

### Rutas públicas

```java
.requestMatchers("/app/index_normal", "/app/info").permitAll()
```

Con esta línea indicamos que las siguientes rutas serán públicas:

```text
/app/index_normal
/app/info
```

Esto significa que cualquier usuario podrá acceder a ellas sin iniciar sesión.

---

### Rutas protegidas

```java
.anyRequest().authenticated()
```

Esta línea indica que cualquier otra solicitud deberá estar autenticada.

Por lo tanto, rutas como las siguientes estarán protegidas:

```text
/app/index_protegido
/app/admin
```

Cuando un usuario intente ingresar a estas rutas sin autenticarse, Spring Security lo redirigirá al formulario de login.

---

### Login por formulario

```java
.formLogin(withDefaults())
```

Esta configuración habilita el formulario de inicio de sesión que Spring Security entrega por defecto.

Gracias a esta línea, no necesitamos crear manualmente una pantalla de login para esta primera experiencia.

---

### Autenticación básica HTTP

```java
.httpBasic(withDefaults())
```

Esta configuración permite probar la autenticación básica HTTP, por ejemplo desde herramientas como Postman o Thunder Client.

---

## Controlador del proyecto

El controlador contiene las rutas que serán probadas durante la clase.

```java
package com.duoc.seguridad.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomControllerSecurity {

    @GetMapping("/app/index_normal")
    public String indexNormal() {
        return "Bienvenido al sitio publico !!!!";
    }

    @GetMapping("/app/info")
    public String infoPublica() {
        return "Bienvenido a la info publica";
    }

    @GetMapping("/app/index_protegido")
    public String indexProtegido() {
        return "Bienvenido al area protegida !!!";
    }

    @GetMapping("/app/admin")
    public String zonaAdmin() {
        return "Zona de administradores";
    }
}
```

---

## Endpoints disponibles

| Método | Endpoint | Tipo de acceso | Descripción |
|---|---|---|---|
| GET | `/app/index_normal` | Público | Muestra un mensaje de bienvenida público |
| GET | `/app/info` | Público | Muestra información pública |
| GET | `/app/index_protegido` | Protegido | Requiere autenticación |
| GET | `/app/admin` | Protegido | Requiere autenticación |

---

## Configuración de usuario

En el archivo `application.properties` se configuran las credenciales iniciales de acceso.

```properties
spring.application.name=seguridad

spring.security.user.name=admin
spring.security.user.password=admin123
```

Esto significa que, para ingresar a las rutas protegidas, se deben utilizar las siguientes credenciales:

```text
Usuario: admin
Contraseña: admin123
```

Importante: estas credenciales son solo para fines académicos. En un proyecto real no se recomienda dejar usuarios y contraseñas directamente configurados de esta forma.

---

## Cómo ejecutar el proyecto

### 1. Clonar el repositorio

```bash
git clone https://github.com/usuario/primer-proyecto-spring-security.git
```

### 2. Ingresar al proyecto

```bash
cd primer-proyecto-spring-security
```

### 3. Ejecutar la aplicación

Con Maven:

```bash
mvn spring-boot:run
```

O desde el IDE, ejecutando la clase principal:

```java
SeguridadApplication.java
```

---

## Cómo probar el proyecto desde el navegador

Una vez iniciada la aplicación, puedes probar las siguientes rutas.

### Ruta pública

```text
http://localhost:8080/app/index_normal
```

Respuesta esperada:

```text
Bienvenido al sitio publico !!!!
```

---

### Otra ruta pública

```text
http://localhost:8080/app/info
```

Respuesta esperada:

```text
Bienvenido a la info publica
```

---

### Ruta protegida

```text
http://localhost:8080/app/index_protegido
```

Al intentar acceder, Spring Security solicitará iniciar sesión.

Credenciales:

```text
Usuario: admin
Contraseña: admin123
```

Después de iniciar sesión, se mostrará:

```text
Bienvenido al area protegida !!!
```

---

### Ruta de administrador

```text
http://localhost:8080/app/admin
```

Respuesta esperada después de autenticarse:

```text
Zona de administradores
```

---

## Cómo probar con Postman o Thunder Client

También puedes probar los endpoints protegidos usando autenticación básica.

Ejemplo:

```http
GET http://localhost:8080/app/index_protegido
```

En la pestaña de autenticación selecciona:

```text
Type: Basic Auth
Username: admin
Password: admin123
```

Si las credenciales son correctas, la aplicación responderá con el contenido protegido.

---

## Conceptos clave que debes estudiar

### 1. Autenticación

La autenticación responde a la pregunta:

```text
¿Quién eres?
```

En este proyecto, el usuario se autentica usando:

```text
admin / admin123
```

---

### 2. Autorización

La autorización responde a la pregunta:

```text
¿Qué tienes permitido hacer?
```

En este proyecto, todas las personas pueden ingresar a las rutas públicas, pero solo los usuarios autenticados pueden ingresar a las rutas protegidas.

---

### 3. Ruta pública

Una ruta pública es una dirección a la que se puede acceder sin iniciar sesión.

Ejemplo:

```text
/app/index_normal
```

---

### 4. Ruta protegida

Una ruta protegida requiere que el usuario esté autenticado.

Ejemplo:

```text
/app/index_protegido
```

---

### 5. Filtro de seguridad

Spring Security trabaja mediante una cadena de filtros.

Antes de que la solicitud llegue al controlador, Spring Security la intercepta y revisa si debe permitirla o bloquearla.

---

## Mapa mental del flujo de seguridad

```text
Solicitud HTTP
      ↓
SecurityFilterChain
      ↓
¿La ruta está permitida con permitAll()?
      ↓
Sí → Acceso público
      ↓
No
      ↓
¿El usuario está autenticado?
      ↓
Sí → Acceso permitido
      ↓
No → Redirección al login
```

---

## Buenas prácticas iniciales

Para este primer acercamiento a Spring Security, se recomienda:

- Separar la configuración de seguridad en un paquete `config`.
- Separar los controladores en un paquete `controller`.
- Probar primero las rutas públicas.
- Luego probar las rutas protegidas.
- Observar el comportamiento del navegador cuando intenta acceder a una ruta privada.
- Probar también desde Postman o Thunder Client.
- No memorizar todo al principio, sino entender el flujo general de protección.

---

## Errores comunes

### Error 401 Unauthorized

Significa que el usuario no está autenticado o las credenciales no fueron enviadas correctamente.

Puede ocurrir al probar desde Postman sin configurar Basic Auth.

---

### Error 403 Forbidden

Significa que el usuario está autenticado, pero no tiene permisos suficientes para acceder a un recurso.

En este primer proyecto todavía no estamos trabajando con roles, pero este error será importante más adelante.

---

### Error 404 Not Found

Significa que la ruta no existe o fue escrita incorrectamente.

Verifica que estés usando exactamente las rutas configuradas en el controlador.

---

## Desafíos propuestos

Cuando el proyecto base esté funcionando, intenta realizar los siguientes desafíos:

### Desafío 1

Agregar una nueva ruta pública:

```text
/app/contacto
```

Debe responder con un mensaje simple.

---

### Desafío 2

Agregar una nueva ruta protegida:

```text
/app/perfil
```

Debe requerir autenticación.

---

### Desafío 3

Cambiar el usuario y contraseña desde `application.properties`.

Por ejemplo:

```properties
spring.security.user.name=duoc
spring.security.user.password=seguridad123
```

---

### Desafío 4

Investigar qué ocurre si se elimina esta línea:

```java
.formLogin(withDefaults())
```

---

### Desafío 5

Investigar qué ocurre si se elimina esta línea:

```java
.httpBasic(withDefaults())
```

---

## Próximos pasos

Este proyecto es solo el primer paso.

Más adelante se pueden incorporar conceptos más avanzados como:

- Usuarios en memoria.
- Roles y permisos.
- Protección por tipo de usuario.
- Login personalizado.
- Logout.
- CSRF.
- JWT.
- OAuth2.
- OpenID Connect.
- Seguridad conectada a base de datos.

---

## Reflexión final

Este proyecto permite observar una de las ideas más importantes del desarrollo backend moderno: no todas las rutas de una aplicación deben estar disponibles para todos los usuarios.

Spring Security nos permite definir reglas claras para proteger nuestra aplicación. En este primer ejercicio aprendimos a separar rutas públicas de rutas privadas usando una configuración sencilla, pero muy poderosa.

Lo importante no es aprenderse cada línea de memoria, sino comprender la lógica:

```text
Algunas rutas son públicas.
Otras rutas requieren autenticación.
Spring Security decide si permite o bloquea el acceso.
```

Cuando entiendes eso, ya estás dando tus primeros pasos en la seguridad de aplicaciones backend con Java.

---

## Autor

Proyecto académico desarrollado para fines de aprendizaje.
