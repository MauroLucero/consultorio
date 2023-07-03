[![Codacy Badge](https://api.codacy.com/project/badge/Grade/1dd143aaa28a4e62a62f489aa1046987)](https://app.codacy.com/gh/MauroLucero/consultorio?utm_source=github.com&utm_medium=referral&utm_content=MauroLucero/consultorio&utm_campaign=Badge_Grade)


# CTD - Proyecto Integrador: Consultorio Odontológico
Backend I

Proyecto de funciones CRUD y API REST para el manejo de pacientes, odontologos y turnos de una pagina web de una clínica odontológica. El Front-end permite observar y utilizar los distintos Endpoints, hecho con Javascript y CSS unicamente.

## Framework
- `Spring Boot`

## Dependencias
- `Spring Web:` Alta de servidor
- `Spring Data JPA:`: ORM
- `Spring Security:` Seguridad y Login
- `Log4j:` Manejo de logs
- `JUNIT:` Test unitarios


## Requisitos

- [Maven](https://maven.apache.org/download.cgi)
- [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)


## Endpoints


### Pacientes

- Guardar: `POST` -> `localhost:8080/pacientes/`

  - `BODY`
  
  ```json
    {
      "nombre": "Mauro",
      "apellido": "Lucero",
      "fechaIngreso": "2023-04-17",
      "domicilio": {
        "calle": "Avenide Indepenencia",
        "numero": 4110,
        "localidad": "Mar del Plata",
        "provincia": "Buenos Aires"
      }
    }
    ```

- Buscar: `GET` -> `localhost:8080/pacientes/{id}`

  - `RESPUESTA` ID =1
  ```json
  {  
      "id":1,
      "nombre": "Mauro",
      "apellido": "Lucero",
      "fechaIngreso": "2023-04-17",
      "domicilio": {
        "id":1,
        "calle": "Avenide Indepenencia",
        "numero": 4110,
        "localidad": "Mar del Plata",
        "provincia": "Buenos Aires"
      }
    }
  ```  
- Actualizar existente: `PUT` -> `localhost:8080/pacientes/`
  - `BODY` 
  ```json
   {   
      "id":1,
      "nombre": "Mauro",
      "apellido": "Lucero",
      "fechaIngreso": "2023-04-17",
      "domicilio": {
        "id":1,
        "calle": "Avenide Indepenencia",
        "numero": 4110,
        "localidad": "Mar del Plata",
        "provincia": "Buenos Aires"
      }
    }
   ```
- Eliminar por id: `DELETE` -> `localhost:8080/pacientes/{id}`

  - `200 OK`- Paciente eliminado

- Buscar a todos los pacientes: `GET` -> `PATH/pacientes(`

### Odontólogos

- Guardaro: `POST` -> `localhost:8080/odontologos/`
  - `BODY`
   
    ```json
    {
      "nombre": "Mauro",
      "apellido": "Lucero",
      "matricula": "4665"
    }
    ```
- Buscar: `GET` -> `localhost:8080/odontologos/{id}`

  - `RESPUESTA` ID =1

```json
  {   
      "id": 1,
      "nombre": "Mauro",
      "apellido": "Lucero",
      "matricula": "4665"
    }
```
- Actualizar existente: `PUT` -> `localhost:8080/odontologos/`
  - `BODY`
  
 ```json
  {   
      "id": 1,
      "nombre": "Mauro",
      "apellido": "Lucero",
      "matricula": "4665"
    }
```
- Eliminar por id: `DELETE` -> `localhost:8080/odontologos/{id}`

   - `200 OK`- Odontologo eliminado

- Buscar a todos los odontologos: `GET` a `PATH/odontologos`



### Turnos

- Guardar: `POST` -> `localhost:8080/turnos/`

  - `BODY`
    ```json
    {
      "paciente": { "id": "1" },
      "odontologo": { "id": "1" },
      "fecha": "2023-04-17T19:30:00"
    }
    ```

- Buscar por id: `GET` -> `localhost:8080/turnos/{id}`

  - `RESPUESTA`
  
   ```json
    {
      "id": 1,
      "paciente": { "id": "1" },
      "odontologo": { "id": "1" },
      "fecha": "2023-04-17T19:30:00"
    }
    ```

- Actualizar existente: `PUT` -> `PATH/turnos`
  - `BODY`
    ```json
    {
      "id": 1,
      "paciente": { "id": "1" },
      "odontologo": { "id": "1" },
      "fecha": "2023-04-17T19:30:00"
    }
    ```
- Eliminar por id: `DELETE` -> `localhost:8080/turnos/{id}`

  - `200 OK`- Turno eliminado

- Buscar a todos los turnos: `GET` a `PATH/turnos`
