# Application

Bu proje, Spring Boot kullanılarak geliştirilen bir Java uygulamasıdır. 

## Özellikler

- **Spring Boot**: Hızlı ve güçlü uygulama geliştirme.
- **Spring Data JPA**: Veri yönetimi için ORM.
- **H2 Database**: Hafif ve yerleşik veritabanı.
- **Lombok**: Kod sadeliği ve boilerplate kod azaltımı.
- **Spring Validation**: Veri doğrulama mekanizmaları.

Proje, modüler bir yapıya sahiptir ve aşağıdaki katmanları içerir:

- **controllers**: Uygulamanın REST API uç noktalarını içerir.
- **converter**: Veri dönüşümleri için sınıflar.
- **dto**: Veri taşıma nesneleri (Data Transfer Objects).
- **exception**: Hata yönetimi ve özel istisnalar.
- **model**: Veritabanı için kullanılan modeller.
- **repository**: Veri erişim katmanı.
- **service**: İş mantığını barındıran servisler.

---

## API Endpoint'leri

### **CartController (Alışveriş Sepeti Yönetimi)**

| HTTP Metodu | Endpoint                                                       | Açıklama                                 |
|-------------|---------------------------------------------------------------|-----------------------------------------|
| POST        | `/api/v1.0/carts/create/{customerId}`                          | Yeni bir alışveriş sepeti oluşturur.    |
| POST        | `/api/v1.0/carts/{cartId}/add-product/{productId}/{quantity}`  | Sepete ürün ekler.                      |
| DELETE      | `/api/v1.0/carts/{cartId}/remove-product/{productId}`          | Sepetten ürün çıkarır.                  |
| GET         | `/api/v1.0/carts/{cartId}`                                     | Belirtilen sepete ait bilgileri getirir.|
| DELETE      | `/api/v1.0/carts/{cartId}/clear`                               | Sepeti temizler.                        |
| GET         | `/api/v1.0/carts/{cartId}/total`                               | Sepetin toplam tutarını döner.          |


## CartController (Alışveriş Sepeti Yönetimi)

| HTTP Metodu | Endpoint                                                       | Açıklama                                 |
|-------------|----------------------------------------------------------------|-----------------------------------------|
| POST        | `/api/v1.0/carts/create/{customerId}`                          | Yeni bir alışveriş sepeti oluşturur.    |
| POST        | `/api/v1.0/carts/{cartId}/add-product/{productId}/{quantity}`  | Sepete ürün ekler.                      |
| DELETE      | `/api/v1.0/carts/{cartId}/remove-product/{productId}`          | Sepetten ürün çıkarır.                  |
| GET         | `/api/v1.0/carts/{cartId}`                                     | Belirtilen sepete ait bilgileri getirir.|
| DELETE      | `/api/v1.0/carts/{cartId}/clear`                               | Sepeti temizler.                        |
| GET         | `/api/v1.0/carts/{cartId}/total`                               | Sepetin toplam tutarını döner.          |

---

## CustomerController (Müşteri Yönetimi)

| HTTP Metodu | Endpoint                       | Açıklama                               |
|-------------|--------------------------------|----------------------------------------|
| GET         | `/api/v1.0/customers`          | Tüm müşterilerin listesini döner.      |
| GET         | `/api/v1.0/customers/{id}`     | Belirtilen ID'ye sahip müşteriyi getirir.|
| POST        | `/api/v1.0/customers`          | Yeni bir müşteri ekler.                |
| PUT         | `/api/v1.0/customers/{id}`     | Müşteri bilgilerini günceller.         |
| DELETE      | `/api/v1.0/customers/{id}`     | Belirtilen müşteriyi siler.            |

---

## OrderController (Sipariş Yönetimi)

| HTTP Metodu | Endpoint                                | Açıklama                               |
|-------------|----------------------------------------|----------------------------------------|
| GET         | `/api/v1.0/orders/{orderId}`           | Belirtilen ID'ye sahip sipariş bilgilerini döner.|
| GET         | `/api/v1.0/orders`                     | Tüm siparişlerin listesini döner.      |
| GET         | `/api/v1.0/orders/order-code/{orderCode}` | Belirtilen sipariş koduna göre siparişleri döner.|
| GET         | `/api/v1.0/orders/customer/{customerId}`| Belirtilen müşterinin siparişlerini döner.|
| DELETE      | `/api/v1.0/orders/{orderId}`           | Belirtilen siparişi siler.             |

---

## ProductController (Ürün Yönetimi)

| HTTP Metodu | Endpoint                                   | Açıklama                              |
|-------------|-------------------------------------------|---------------------------------------|
| GET         | `/api/v1.0/products`                      | Tüm ürünlerin listesini döner.        |
| GET         | `/api/v1.0/products/{id}`                 | Belirtilen ID'ye sahip ürünü döner.   |
| GET         | `/api/v1.0/products/check-stock/{id}`     | Ürün stok durumunu kontrol eder.      |
| POST        | `/api/v1.0/products/reduce-stock/{id}`    | Belirtilen miktarda stok düşer.       |
| POST        | `/api/v1.0/products/increase-stock/{id}`  | Belirtilen miktarda stok artırır.     |
| POST        | `/api/v1.0/products`                      | Yeni bir ürün ekler.                  |
| PUT         | `/api/v1.0/products/{id}`                 | Ürün bilgilerini günceller.           |
| DELETE      | `/api/v1.0/products/{id}`                 | Belirtilen ürünü siler.               |

---

## Teknolojiler

- **Java 17**
- **Spring Boot 3.4.0**
- **H2 Database**
- **Maven**
- **Bean Validation**



