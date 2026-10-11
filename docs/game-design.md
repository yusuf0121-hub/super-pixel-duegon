# Oyun Tasarımı ve Senaryo

## 1. Oyun İçi Senaryo ve Kaos

### Spawn (Doğma) Noktası
Yeni oyuncu ilk geldiğinde etrafı kaotik ve tehlikeli bir ortam bekler. Yerde boş çantalar, kırılmış bloklar ve düşman oyuncular ortaya çıkar. 10 saniyelik doğma koruması ile oyuncu kısa süreli bir kaçış fırsatı kazanır.

### Merdiven Başları (Haraç Bölgeleri)
Merdivenler, güçlü klanların veya oyuncuların kontrol ettiği giriş noktalarıdır. Bu alanlar, aşağı katlara erişimi kontrol eden haraç bölgeleridir.

"Aşağı inmek istiyorsan 50 Altın ver ya da köprüden aşağı atlarım!"

Bu tür diyaloglar, düşman gruplar arasında pazarlık, haraç ve baskınlara zemin hazırlar.

### Tüccar Odaları
Tarafsız bölgelerde, düşman oyuncular yan yana durup blok ve iksir alabilir. Bu odalar, savaşın dışında kalmak isteyen oyuncular için güvenli ve ticari bir alan sunar.

### Mimar / Mühendis Oyuncuları
Mühendisler, tüccar odalarından aldıkları bloklarla gizli köprüler kurar. Böylece harita içindeki gizli odalara ve boşluklara kısa yollar açarlar. Ayrıca küçük kaleler ve sığınaklar inşa ederler.

## 2. İlham Alınacak Repo ve Teknoloji Kaynakları

### A. Orijinal Kod ve Ağ Temeli
- Shattered Pixel Dungeon
- Netty / KryoNet

### B. Çok Oyunculu Pixel Dungeon Mantığı
- Açık kaynaklı multiplayer deneyler / fork'lar

### C. Blok Koyma ve Harita Yönetimi
- libGDX
- Tiled map yaklaşımı

### D. SandBox / Multiplayer Mimari
- Mindustry

## 3. İlk Proje Hedefi

Bu repo, aşağıdaki adımları destekleyen bir başlangıç yapısıdır:

1. Temel Java + Gradle sunucu yapısı kur
2. Client/server ağ yapısını oluştur
3. Oyuncu login ve sohbet protokolünü ekle
4. Oyun içinde tile / blok değişim mantığını hazırlamaya başla
5. Sonraki aşamada libGDX ve gerçek dungeon haritası eklenir
