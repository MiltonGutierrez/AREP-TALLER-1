### Escuela Colombiana de Ingeniería

### Arquitectura Empresarial - AREP

#  TALLER DISEÑO Y ESTRUCTURACIÓN DE APLICACIONES DISTRIBUIDAS EN INTERNET

Este taller consiste en la creación de un servidor web básico en Java, sin el uso de frameworks como Spark o Spring. El servidor debe manejar múltiples solicitudes de forma secuencial (no concurrente), leer archivos del sistema local y responder con contenido estático como páginas HTML, archivos JavaScript, CSS e imágenes. Además, incorpora servicios REST en el backend para comunicación asíncrona con una aplicación web.

## Empezando

Estas instrucciones te permitirán obtener una copia del proyecto y ejecutarlo en tu máquina local para propósitos de desarrollo y pruebas.

### Prerequisitos

- Java 8 o superior
- Maven 3.x
- Acceso a una terminal.

### Instalando

Pasos para configurar el entorno de desarrollo:

1. Clona el repositorio del proyecto:

   ```bash
   git clone https://github.com/MiltonGutierrez/taller1-arep.git
   cd taller1-arep
   ```

2. Compila el proyecto usando Maven:

   ```bash
   mvn clean compile
   ```

3. Ejecuta el servidor:

   ```bash
   java -cp target/classes edu.escuelaing.arep.taller1.HttpServer
   ```

4. Accede al servidor desde tu navegador en [http://localhost:8080](http://localhost:8080).

## Correr las pruebas 

Para ejecutar las pruebas automatizadas del sistema:

```bash
mvn test
```

### Break down into end-to-end tests

Estas pruebas validan el correcto funcionamiento de las funcionalidades principales del servidor. Especificamente las peticiones GET y POST hacia el recurso /app/note (para la realización de estas se utilizo JUnit Jupiter. Una muestra de estas pruebas es: 
```java
    @ParameterizedTest
    @CsvSource({
        "'title=&group=personal&content=hola', 'Some parameters are empty'",
        "'title=hola&group=hi&content=hola', 'Invalid group'",
        "'title=&group=personal&content=', 'Some parameters are empty'",
        "'title=&group=&content=', 'Some parameters are empty'"
    })
    void testPostNoteResponseShouldHandleErrors(String input, String expectedError) {
        String responseByController = noteController.addNote(input);
        StringBuilder responseThatShouldReturn = new StringBuilder();
        responseThatShouldReturn.append("HTTP/1.1 400 Bad Request\r\n");
        responseThatShouldReturn.append("Content-Type: application/json\r\n");
        responseThatShouldReturn.append("{ \"error\": " + "\"" + expectedError + "\"}");

        assertEquals(responseByController, responseThatShouldReturn.toString());
    }
```
En este caso se prueban diferentes peticiones POST con diferentes parametros para la creación de una nota, estos parametros son inválidos, por lo que el controllador debe devolver una respuesta 400 y un JSON con la especificacion del error. 

- **Resultado de las pruebas**
![image](https://github.com/user-attachments/assets/70e8578f-a02b-43d9-b05b-1eb20416ee9f)

### Estilos de código.

Se utilizo el patron de diseño MVC, para poder dividir el sistema en diferentes componentes para el back -> (Controller, Service y Model) y para el front, se tuvo en cuenta la creacion de un apiclient en js para poder almacenar las peticiones a realizar. Además se tuvo en cuenta la creación de las interfaces necesarias.

### Muestra de la ejecución

1. Acceso al aplicativo:
![image](https://github.com/user-attachments/assets/7942c3df-bb6f-479a-9a4c-a3da2f44ade8)
2. Ejemplo creación de la nota
![image](https://github.com/user-attachments/assets/a2da0022-34cf-48a0-a57e-5965e6db0b0b)
3. Ejemplo petición de notas creadas
![image](https://github.com/user-attachments/assets/63429968-5db6-44f2-9b22-b9d873899ce3)
4. Ejemplo añadir nota con datos incompletos (se realiza la validación desde el cliente por lo que no se ejecuta la petición)
![image](https://github.com/user-attachments/assets/35d2ef47-8986-4233-807f-ed11c2dd91f0)

## Construido con.

- [Maven](https://maven.apache.org/) - Dependency Management

## Autores

- **Milton Andres Gutierrez Lopez** - *Initial work* - [MiltonGutierrez](https://github.com/MiltonGutierrez)

## Licencia

Este proyecto está licenciado bajo la Licencia  GPL-3.0 - mira el archivo [LICENSE.md](LICENSE.md) para más detalles.


