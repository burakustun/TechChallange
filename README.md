# TechChallange

# Kullanılan Kütüphaneler

Retrofit -> networking işlemleri için kullanıldı

RXJava -> reactive programming

Koin -> dependency injection için tercih edildi.

Lottie -> loading animasyonu için kullanıldı.

Material Components -> ui componentleri için kullanıldı 

ConstraintLayout -> layout için kullanıldı

# Mimari yapı

### Core modülü

Extensionlar, utility classlar, base classlar ve proje genelinde kullanılan kütüphanelerin tutulduğu modül.

### Data modülü

Dto ve domain classların tutulduğu modül.

### Network modülü

Network ile alakalı classların tutulduğu modül. Retrofit, okhttp ve web serviceler burada oluşturuluyor. 

### App modülü

Uygulamanın bulunduğu modül. Feature bazlı klasörleme yapılıyor. Her feature'ın içinde kendi classları(activity,fragment,view model vb) bulunuyor
