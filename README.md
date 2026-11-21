# Kata 4 — Servicio web de divisores con Javalin

Práctica de Ingeniería del Software 2 (ULPGC). El proyecto expone una operación Java mediante un servicio HTTP con Javalin y devuelve los resultados en JSON utilizando Gson.

Repositorio: [C-nizo/kata4](https://github.com/C-nizo/kata4).

## Requisitos y dependencias

- Java 27.
- Maven e IntelliJ IDEA (o cualquier IDE compatible con Maven).
- Javalin 7.2.4, para el servidor y las rutas HTTP.
- Gson 2.15.0, para serializar las respuestas JSON.

Las dependencias están declaradas en `pom.xml`.

## Compilación y ejecución

1. Abre el proyecto como proyecto Maven en IntelliJ IDEA y selecciona el JDK 27.
2. Deja que Maven cargue las dependencias.
3. Ejecuta `src/main/java/software/ulpgc/katas/Main.java`.
4. Con el servidor en marcha, accede a `http://127.0.0.1:8080/divisors/10`.

También se puede compilar desde una terminal que tenga Maven instalado:

```bash
mvn clean compile
```

El servidor escucha en el puerto **8080**.

## Contrato HTTP

**`GET /divisors/{n}`**: calcula los divisores positivos de `n`.

- `n` es un número entero positivo incluido en la ruta.
- La operación devuelve **HTTP 200** y un cuerpo **`application/json`** cuando la entrada es válida.
- Si `n` no es un entero o no es positivo, devuelve **HTTP 400** con un mensaje de error.
- Las rutas inexistentes reciben **HTTP 404** mediante Javalin.

Ejemplo de petición válida:

```http
GET http://127.0.0.1:8080/divisors/10
```

Respuesta: **200 OK**

```json
{"number":10,"divisors":[1,2,5,10]}
```

Ejemplos de entradas inválidas:

| Petición | Estado | Mensaje |
| --- | --- | --- |
| `GET /divisors/abc` | 400 | `Introduce un valor entero para n.` |
| `GET /divisors/0` | 400 | `El valor debe ser mayor que cero.` |
| `GET /divisors/-5` | 400 | `El valor debe ser mayor que cero.` |

## Estructura y responsabilidades

| Componente | Responsabilidad |
| --- | --- |
| `Main` | Construye los componentes, registra la ruta y arranca Javalin. |
| `DivisorsEndPoint` | Lee y valida la entrada HTTP, ejecuta la operación y prepara la respuesta. |
| `DivisorsCalculationOperation` | Coordina el cálculo y la serialización. |
| `DivisorsResult` | Modelo del resultado (`number` y `divisors`) y cálculo de los divisores. |
| `DivisorsSerializer` | Puerto que define la serialización del modelo. |
| `JsonDivisorsSerializer` | Adaptador que implementa el puerto mediante Gson. |
| `DivisorsOperationCheck` | Comprobación sencilla de la operación sin arrancar el servidor. |

La lógica del dominio y la operación no dependen de Javalin ni de Gson.

## Verificación

Con `Main` ejecutándose, pueden comprobarse los estados y las respuestas desde PowerShell:

```powershell
curl.exe -i "http://127.0.0.1:8080/divisors/10"
curl.exe -i "http://127.0.0.1:8080/divisors/abc"
curl.exe -i "http://127.0.0.1:8080/divisors/0"
curl.exe -i "http://127.0.0.1:8080/ruta-inexistente"
```

Además, `src/test/java/software/ulpgc/katas/DivisorsOperationCheck.java` puede ejecutarse directamente desde IntelliJ sin arrancar Javalin. Comprueba que para `18` se obtiene:

```json
{"number":18,"divisors":[1,2,3,6,9,18]}
```

Cuando pasa la comprobación, imprime `Prueba superada`. Es una prueba manual con `main`, no una prueba JUnit.

## Repeticiones

Se rehízo tres veces el mismo servicio de divisores, manteniendo la operación y la separación de responsabilidades. Cada repetición y su preparación están identificadas mediante commits en Git.

1. **Primera repetición:** `GET /divisors?n=10`, con el número como parámetro de consulta y respuesta JSON como array. El cálculo se implementa en `DivisorsCalculator`.
2. **Segunda repetición:** `GET /divisors/10`, con el número como parámetro de ruta y la misma respuesta JSON. Se conserva `DivisorsCalculator`.
3. **Tercera repetición (versión final):** se conserva la ruta de la segunda, pero el modelo pasa a ser el `record` `DivisorsResult` y la respuesta JSON incluye tanto `number` como `divisors`.

El historial permite consultar las tres implementaciones; la rama `master` contiene la última versión evaluable.

## Vídeo explicativo

**Pendiente de grabación.** Se añadirá aquí el enlace al vídeo (duración máxima de 7 minutos) en un commit posterior.