## English Description

# Spring 3 & Spring Security 6 JWT Token Application

This project includes a sample JWT token application developed using Spring 3 and Spring Security 6. Additionally, it contains configurations for bringing up a MySQL database using Docker-compose and connecting the application to this database.

## Project Description

The project has the following key features:

- **Usage of Spring and Spring Security:** A secure application has been developed using Spring 3 and Spring Security 6.
- **MySQL Database Connection:** A MySQL database has been brought up using Docker-compose, and the Spring application is connected to it.
- **JWT Token Application:** An example JWT token application is implemented, including token creation, validation, and user authorization processes.
- **Bringing Up MySQL with Docker-compose:** The project easily brings up a MySQL database using Docker-compose.

## Project Structure

The basic structure of project files is as follows:

- **src/main/java:** Java source code is located in this folder.
- **src/main/resources:** Configuration files and resources used by the project are in this folder.
- **docker-compose.yml:** Docker-compose file used to bring up the MySQL database.
- **pom.xml:** Maven project configuration file.

## Installation Steps

### Bringing Up MySQL Database:

```sh
docker-compose up -d
```

# MySQL Connection Details:
Adjust your MySQL connection details using the `src/main/resources/application.properties` file in the project root.

# JWT Token Settings:
Modify JWT token settings through the Spring Security configurations within the project.

# Running the Application:
To start the application, you can use the following command:

```sh
./mvnw spring-boot:run
```

# Usage:
After the project is up and running, you can use it by navigating to localhost:8080. To learn about the usage of JWT tokens for API calls, examine the comments and Spring Security configurations within the project.


## Türkçe Açıklaması

# Spring 3 & Spring Security 6 JWT Token Uygulaması

Bu proje, Spring 3 ve Spring Security 6 kullanılarak geliştirilmiş bir JWT token örnek uygulamasını içermektedir. Ayrıca, Docker-compose ile MySQL veritabanını ayağa kaldırmak ve bu veritabanına bağlanmak için gerekli yapılandırmalar da bulunmaktadır.

## Proje Açıklaması

Proje, aşağıdaki temel özelliklere sahiptir:

- **Spring ve Spring Security Kullanımı:** Spring 3 ve Spring Security 6 kullanılarak güvenli bir uygulama geliştirilmiştir.
- **MySQL Veritabanı Bağlantısı:** Docker-compose ile MySQL veritabanı ayağa kaldırılmış ve Spring uygulaması ile bağlantı sağlanmıştır.
- **JWT Token Uygulaması:** Örnek bir JWT token uygulaması yapılmıştır. Token oluşturma, doğrulama ve kullanıcı yetkilendirme işlemleri gerçekleştirilmiştir.
- **Docker-compose ile MySQL Ayağa Kaldırma:** Proje, Docker-compose ile MySQL veritabanını kolayca ayağa kaldırmaktadır.

## Proje Yapısı

Proje dosyalarının temel yapısı şu şekildedir:

- **src/main/java:** Java kaynak kodları bu klasör altında bulunur.
- **src/main/resources:** Projenin kullanacağı konfigürasyon dosyaları ve kaynaklar bu klasörde bulunur.
- **docker-compose.yml:** MySQL veritabanını ayağa kaldırmak için kullanılan Docker-compose dosyasıdır.
- **pom.xml:** Maven proje yapılandırma dosyasıdır.

## Kurulum Adımları

### MySQL Veritabanını Ayağa Kaldırma:

```sh
docker-compose up -d
```
# MySQL Bağlantı Bilgileri:
Proje ana dizininde bulunan src/main/resources/application.properties dosyasını kullanarak MySQL bağlantı bilgilerinizi ayarlayın.

# JWT Token Ayarları:
Proje içerisindeki Spring Security konfigürasyonları üzerinden JWT token ayarlarını düzenleyin.

# Uygulamayı Çalıştırma:
Uygulamayı ayağa kaldırmak için aşağıdaki komutu kullanabilirsiniz:

```sh
./mvnw spring-boot:run
```
# Kullanım:
Proje ayağa kalktıktan sonra, localhost:8080 adresine giderek uygulamayı kullanabilirsiniz. API çağrıları için JWT token kullanımını öğrenmek için proje içindeki yorum satırlarını ve Spring Security konfigürasyonlarını inceleyebilirsiniz

