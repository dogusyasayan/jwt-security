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

