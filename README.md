# Aiva Studio API

API REST de Aiva Studio construida con Spring Boot, PostgreSQL y autenticación por token Bearer.

## Ejecutar con Docker

1. Copia `.env.template` como `.env` y define `DB_PASSWORD` con una contraseña local segura.
2. Inicia los servicios desde esta carpeta:

   ```powershell
   docker compose up --build
   ```

La API queda disponible en `http://localhost:8080`; OpenAPI se publica en
`http://localhost:8080/swagger-ui.html` y está protegido por la autenticación configurada. PostgreSQL
conserva sus datos en el volumen `postgres_data`.

Para permitir el origen donde se ejecuta el frontend, configura `CORS_ORIGINS` en `.env` como una lista
separada por comas, por ejemplo `http://localhost:5173,http://localhost:4173`.

## Variables para el despliegue

Al configurar el servicio en Render, define estas variables de entorno en el panel del servicio:

- `DB_URL`: URL JDBC de Neon, incluyendo `sslmode=require`.
- `DB_USERNAME`: usuario de la base de datos.
- `DB_PASSWORD`: contraseña de la base de datos. Guárdala como variable secreta y nunca en Git.
- `CORS_ORIGINS`: origen exacto del frontend publicado, por ejemplo `https://tu-frontend.onrender.com`.
- `PORT`: Render lo proporciona automáticamente; la aplicación escucha en ese puerto y usa `8080` localmente.

No uses las credenciales de Neon en el frontend. El frontend debe comunicarse únicamente con la URL pública
de esta API.

## Ejecutar localmente

Configura PostgreSQL, actualiza `DB_URL`, `DB_USERNAME` y `DB_PASSWORD` en `.env`, y ejecuta:

```powershell
.\mvnw.cmd spring-boot:run
```

## Consola y pruebas en Postman

El servidor imprime en consola las solicitudes REST con método, ruta, estado HTTP y duración, además
de las sentencias SQL de Hibernate. Por seguridad, no registra cuerpos de solicitud, contraseñas,
tokens ni valores enlazados a las consultas SQL.

Importa `postman/Aiva-Studio-API.postman_collection.json` en Postman. También puedes importar
`postman/Local.postman_environment.json` y seleccionarlo como entorno. Ejecuta la colección completa
con el backend activo en `http://localhost:8080`.

La colección prueba validación del registro, registro/login, modelos, perfil, creación/listado de
proyectos, movimientos, recarga de prueba y revocación del token. Genera un correo único `@example.test`
por ejecución. Los datos se guardan en la base configurada: crea una cuenta, un proyecto y movimientos
de créditos; la recarga de prueba añade 500 créditos más un bono de 100. No la ejecutes contra datos
de producción si no quieres conservar esos registros.

## Endpoints principales

Los endpoints de autenticación son públicos. Los demás requieren
`Authorization: Bearer <token>`.

| Método | Ruta | Uso |
| --- | --- | --- |
| `POST` | `/api/auth/registro` | Crear cuenta (`nombre`, `email`, `password`) |
| `POST` | `/api/auth/login` | Iniciar sesión (`email`, `password`) |
| `POST` | `/api/auth/logout` | Revocar el token actual |
| `GET` | `/api/aiva/modelos` | Listar modelos y costo por segundo |
| `GET` | `/api/aiva/usuarios/{userId}` | Consultar perfil y créditos |
| `GET` | `/api/aiva/usuarios/{userId}/movimientos` | Consultar movimientos de créditos |
| `POST` | `/api/aiva/usuarios/recargar` | Recargar créditos (`usuarioId`, `cantidad`) |
| `POST` | `/api/aiva/proyectos/crear` | Crear proyecto |
| `GET` | `/api/aiva/proyectos/usuario/{userId}` | Listar proyectos del usuario |

Las reglas de validación, costos, movimientos de créditos y autorización se mantienen en los servicios
del backend. El frontend enlazado aún presenta datos de demostración en su `src/main.jsx`; para mostrar
datos reales debe llamar estos endpoints y enviar el token recibido al iniciar sesión.

## Pruebas automatizadas

Ejecuta la suite local con:

```powershell
.\mvnw.cmd test
```
