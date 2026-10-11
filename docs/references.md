# Referanslar ve İlham Kaynakları

## Ana Bilgi Kaynakları

### 1. Shattered Pixel Dungeon
Temel oyun mantığı ve tile sistemi için incelenmelidir.

Özellikle:
- `Level.java`
- `Tile.java`
- `Hero.java`

### 2. KryoNet / Netty
Java tabanlı hızlı ağ iletişimi için önerilen teknoloji.

Kullanım senaryoları:
- oyuncu hareketleri
- blok yerleştirme
- sohbet mesajları
- canlı oyun durumu

### 3. libGDX
Harita ve blok sistemleri için mekanik rehber.

### 4. Mindustry
Sunucu-istemci mimarisi ve oyun tasarımı için çok değerli bir örnek.

## Uygulama Notları

- Bu repo, gerçek oyun kodu yerine geliştirme başlangıcıdır.
- Sunucu tarafı, oyun mantığının merkezi olacak şekilde tasarlanmıştır.
- Client tarafı, oyuncu girişini ve sunucu ile iletişimini sağlar.
- Common katmanı, ağ paketleri ve paylaşılabilir veri modellerini barındırır.
