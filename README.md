# Super Pixel Duegon

Super Pixel Duegon, Pixel Dungeon benzeri bir döngüsel dungeon/macera oyununa çok oyunculu (multiplayer) ve kaotik PvP öğeleri ekleyen bir prototip ve fikir geliştirme projesidir. Bu repo, oyun fikrini hızlı şekilde hayata geçirmek için başlangıç mimarisini, sunucu-istemci ağ yapısını ve oyun tasarım dokümantasyonunu içeren bir iskelet sunar.

## Oyun Akışı

- Spawn alanında yeni oyuncular için kısa bir güvenlik süresi vardır.
- Merdiven başları, haraç bölgeleri ve çatışma noktalarıdır.
- Tüccar odaları, PvP'nin yasak olduğu tarafsız bir yer olarak tasarlanır.
- Mimar ve mühendis oyuncular, gizli yollar ve küçük kaleler inşa eder.
- Oyun, 2b2t benzeri kaos hissini korurken, dungeon ilerleme ve taktiksel rekabeti birleştirir.

## Proje Yapısı

```text
super-pixel-duegon/
├── README.md
├── settings.gradle
├── build.gradle
├── .gitignore
├── common/
│   └── src/main/java/com/superpixel/common/
├── server/
│   └── src/main/java/com/superpixel/server/
├── client/
│   └── src/main/java/com/superpixel/client/
├── docs/
│   ├── game-design.md
│   └── references.md
└── LICENSE
```

## Teknoloji Yığını

- Java 17
- Gradle
- KryoNet (server/client iletişimi)
- Yerel ağ protokolü ve dinamik oyun örnekleri

## Hızlı Başlangıç

```bash
gradle :server:run
```

Ayrı bir istemci başlatmak için:

```bash
gradle :client:run
```

Not: Bu aşama, temel prototip katmanıdır. Oyun içi görseller, harita sistemi ve gerçek dungeon mantığı daha sonra eklenir.

## Tasarım Teması

### 1. Spawn ve Kaos
- Oyuncular doğar doğmaz savunmasızdır.
- 10 saniyelik koruma süresi, kaçış ve strateji kurma imkanı sunar.
- Spawn alanı, düşman oyuncular için bir av sahasıdır.

### 2. Merdiven Başları ve Haraç
- Alt katlara geçiş noktaları, kontrol ve baskı alanıdır.
- "50 Altın ver ya da köprüden aşağı atlarım!" tarzı etkileşimler için zemin hazırlanır.

### 3. Tüccar Odaları
- Tarafsız ticaret alanlarında PvP yasaktır.
- Blok ve iksir alışverişi yapılabilir.

### 4. Mimar / Mühendis Oynanışı
- Harita üzerinde gizli köprüler ve küçük sığınaklar inşa edilir.
- Derin boşluklar, keşif ve taktiksel avantaj sağlar.

## Referanslar

1. Shattered Pixel Dungeon
2. Netty / KryoNet
3. Mindustry
4. libGDX

Ayrıntılı tasarım ve referans notları için `docs/` klasörüne bakın.
