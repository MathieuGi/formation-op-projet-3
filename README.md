# Chatop API project

## 1. Prerequisites & Technology versions

Make sure the following tools are installed before running the project:

    Java (JDK): 21

    Spring Boot: 4.1.1

    Maven: 3.9+

Main dependencies

The project uses the following libraries:

    Spring Security: Version managed by Spring Boot

    Spring Data JPA / Hibernate: Version managed by Spring Boot

    Springdoc OpenAPI (Swagger): 3.1.0

    JJWT (JWT authentication): 0.12.5

    MapStruct: 1.6.3

    Lombok: Version managed by Spring Boot (1.18.36 annotation processor)

## 2. Clone the project on your computer

``git clone https://github.com/MathieuGi/formation-op-projet-3.git``

## 3. Instantiate the database

- Create your database with the command : ``CREATE DATABASE {database_name}``
- Connect to the created database : ``USE {database_name}``
- Then import the SQL file you can find in resource/sql/script.sql : ``source 
/path/to/script.sql``
- Create a user for this database with restricted privileges to the
  database :
    - ``CREATE USER '{user}'@'localhost' IDENTIFIED BY '{password}'``
    - ``GRANT INSERT, UPDATE, DELETE, SELECT ON {database_name}.* To '{user}
    '@'localhost'``

## 4. Initialize environment variable

- In your project configuration environment variables or in a file named
  `env.properties` in the root folder of your project add the following
  configurations :
  ```
  APP_DB_HOST={database_host} #127.0.0.1 if you are in local
  APP_DB_NAME={database_name}
  APP_DB_PORT={database_port}
  APP_DB_PW={database_password}
  APP_DB_USER={database_username}
  JWT_SECRET={jwt_secret} #Must be generated
  JWT_EXPIRATION={jwt_expiration_time} #In milliseconds
  ```

- Your can generate the secret key with openssl for example or with this code :
  ```
  KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
  keyGen.init(256);
  SecretKey secretKey = keyGen.generateKey();
  
  String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
  System.out.println("Symmetric Key: " + encodedKey);
  ```

## 5. API Documentation

- Swagger UI : http://localhost:3001/swagger-ui/index.html
- API doc JSON : http://localhost:3001/v3/api-docs
