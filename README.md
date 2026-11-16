# Kata 4 — Cliente HTTP

**Ingeniería del Software 2 — ULPGC**

## 1. Objetivo

Desarrollar un cliente Java que consuma una API REST mediante HTTP, transforme las respuestas JSON en objetos y presente los resultados por consola.

Se utiliza la API pública [JSONPlaceholder](https://jsonplaceholder.typicode.com/), sin necesidad de credenciales ni de un servidor propio.

La solución separa la interfaz de usuario, el puerto de servicio, el adaptador HTTP, la deserialización y el modelo de dominio.

## 2. Tecnologías

- Java 27.
- Maven.
- Gson 2.15.0 para deserializar JSON.
- `java.net.http.HttpClient` para las peticiones HTTP.

## 3. Compilación y ejecución

1. Abrir el proyecto en IntelliJ IDEA con JDK 27.
2. Cargar las dependencias de Maven.
3. En el panel Maven, ejecutar **Lifecycle → clean** y después **compile**.
4. Ejecutar `Main.java`.
5. Introducir el identificador solicitado por consola.

También puede compilarse mediante `mvn clean compile` si Maven está instalado y disponible en el sistema.

La URL base se configura en `Main.java`. La implementación final consulta el endpoint `GET /todos/{id}`.

## 4. Arquitectura

La tercera repetición, que constituye la versión final del código, contiene los siguientes componentes:

| Componente | Responsabilidad |
|---|---|
| `Main` | Configuración y conexión de componentes. |
| `Task` | Modelo de dominio. |
| `TaskLoader` | Puerto de servicio. |
| `TaskConsole` | Entrada de datos y presentación. |
| `JsonPlaceHolderTaskLoader` | Comunicación HTTP e implementación del puerto. |
| `TaskDto` | Representación de los datos externos. |
| `TaskDeserializer` | Conversión de JSON a DTO mediante Gson. |
| `TaskValidator` | Validación de los datos recibidos. |
| `TaskMapper` | Transformación de DTO a modelo de dominio. |

El adaptador de consola depende del puerto `TaskLoader`, no de la implementación HTTP concreta.

## 5. Repeticiones

La kata se ha desarrollado tres veces, conservando una estructura y un flujo de funcionamiento equivalentes:

| Repetición | Recurso consultado | Commit |
|---|---|---|
| Primera | Usuarios (`/users/{id}`) | [`ec33bb44`](https://github.com/C-nizo/kata4/commit/ec33bb44) |
| Segunda | Publicaciones (`/posts/{id}`) | [`22bab780`](https://github.com/C-nizo/kata4/commit/22bab780) |
| Tercera | Tareas (`/todos/{id}`) | [`8aa5ed45`](https://github.com/C-nizo/kata4/commit/8aa5ed45) |

Las variaciones consistieron principalmente en cambiar el recurso, su modelo de datos y su presentación. En la tercera repetición se trabaja también con el atributo booleano `completed`.

Las implementaciones anteriores pueden recuperarse desde el historial de Git.

## 6. Verificación

Se realizaron comprobaciones manuales sobre la tercera repetición:

| Prueba | Resultado |
|---|---|
| ID `1` | Muestra correctamente la tarea y su estado. |
| ID `999` | `Tarea no encontrada (HTTP 404)` |
| Entrada `abc` | `El ID debe ser un número entero.` |
| URL de servicio inaccesible | `Error de comunicación` |
| Reconstrucción desde IntelliJ | Correcta |
| Maven `clean` y `compile` | `BUILD SUCCESS` con Java 27 |

El código también contempla respuestas JSON malformadas y datos incompletos mediante el tratamiento de excepciones y la validación del DTO.

La URL utilizada para simular el fallo de comunicación fue una dirección inexistente, restableciéndose posteriormente la URL original.

## 7. Vídeo explicativo

**Pendiente de grabación y enlace.**

Duración máxima: 7 minutos.

El vídeo mostrará el funcionamiento del cliente, la organización de sus componentes, las decisiones de diseño, las repeticiones y los resultados de verificación.