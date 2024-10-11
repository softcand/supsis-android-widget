# Supsis Android Widget

**Supsis Android Widget**, Android uygulamalarınız için özelleştirilebilir bir sohbet bileşenidir. Bu bileşen, kullanıcılarınızın destek ekibinizle gerçek zamanlı olarak iletişim kurmasını sağlar.

## Özellikler

- **Kolay Entegrasyon**: Uygulamanıza minimum çabayla sohbet bileşeni ekleyin.
- **Özelleştirilebilir**: Kullanıcı verilerini ve departmanı ayarlayarak kişiselleştirilmiş destek sunun.
- **Duyarlı Arayüz**: Bileşen, farklı ekran boyutlarına ve yönlendirmelerine sorunsuz bir şekilde uyum sağlar.
- **Gerçek Zamanlı İletişim**: Kullanıcılarınız ve destek ekibiniz arasında anlık mesajlaşma imkanı sağlar.

## Gereklilikler

- **Android SDK 24** veya üzeri
- **İnternet İzni**: Uygulamanızın internet erişimine izin vermesi gerekir. `AndroidManifest.xml` dosyanıza aşağıdaki izni ekleyin:

  ```xml
  <uses-permission android:name="android.permission.INTERNET" />

## Kurulum

Bu kütüphane JitPack üzerinden dağıtılmaktadır. Projenize eklemek için aşağıdaki adımları izleyin:

- JitPack deposunu root build.gradle dosyanıza ekleyin:
  
```gradle
allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```
- Bağımlılığı modülünüzün build.gradle dosyasına ekleyin:
```gradle
dependencies {
    implementation 'com.github.softcand:supsis-android-widget:1.0.3'
}
```

## Kullanım

SupsisChatView'i kullanmak için, XML düzeninize ekleyin ve MainActivity içinde başlatın.

```activity_main.xml``` Dosyası:

```xml
<?xml version="1.0.3" encoding="utf-8"?>
<com.supsis.supsis_android_preview.SupsisChatView xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/supsisChatView"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```

## Fonksiyon ve Widget Kullanımı
Sohbet deneyimini kişiselleştirmek için kullanıcı bilgilerini ayarlayabilirsiniz.

```MainActivity.kt``` Dosyası:

```kotlin
package com.supsis.testapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.supsis.supsis_android_preview.SupsisChatView

class MainActivity : AppCompatActivity() {

    private var supsisChatView: SupsisChatView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)˜
        setContentView(R.layout.activity_main) // XML düzenimizi yüklüyoruz

        // SupsisChatView'i başlatıyoruz
        supsisChatView = findViewById<SupsisChatView>(R.id.supsisChatView).apply {
            initialize("sizin_domain_adiniz") // Domain adınızı girin
            open() // Uygulama açıldığında sohbeti otomatik açar
            setUserData(
                mapOf(
                    "email" to "kullanici@ornek.com",
                    "name" to "Ahmet Yılmaz"
                )
            )
            setDepartment("Destek")
        }
    }
}
```

## Fonksiyonların Açıklaması

| Fonksiyon | Açıklama    | Kullanım Örneği    |
| :-----: | :---: | :---: |
| ```initialize(domainName: String)``` | Bileşeni domain adınızla başlatır.   | ```supsisChatView?.initialize("sizin_domain_adiniz")```   |
| ```setUserData(userData: Map<String, String>)``` | Sohbet oturumu için kullanıcı bilgilerini ayarlar.   | ```supsisChatView?.setUserData(mapOf("email" to "kullanici@ornek.com", "name" to "Ahmet Yılmaz"))```   |
| ```setDepartment(department: String)``` | Sohbet oturumu için departmanı ayarlar.   | ```supsisChatView?.setDepartment("Destek")```   |
| ```open()``` | Sohbet bileşenini açar ve kullanıcıya gösterir.   | ```supsisChatView?.open()```   |
| ```close()``` | Sohbet bileşenini kapatır ve kullanıcıdan gizler.   | ```supsisChatView?.close()```   |

## Örnek Uygulama

```activity_main.xml``` Dosyası:

```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/layoutRoot"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <!-- SupsisChatView'i yerleştiriyoruz -->
    <com.supsis.supsis_android_preview.SupsisChatView
        android:id="@+id/supsisChatView"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

    <!-- Butonları alt kısma yerleştiriyoruz -->
    <LinearLayout
        android:id="@+id/buttonContainer"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        android:layout_alignParentBottom="true"
        android:gravity="center_horizontal">

        <Button
            android:id="@+id/buttonOpenChat"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Sohbeti Aç" />

        <Button
            android:id="@+id/buttonCloseChat"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Sohbeti Kapat" />
    </LinearLayout>

</RelativeLayout>
```

```MainActivity.kt``` Dosyası:

```kotlin
package com.supsis.testapp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.supsis.supsis_android_preview.SupsisChatView

class MainActivity : AppCompatActivity() {

    private var supsisChatView: SupsisChatView? = null
    private lateinit var buttonOpenChat: Button
    private lateinit var buttonCloseChat: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // SupsisChatView'i tanımlıyoruz
        supsisChatView = findViewById<SupsisChatView>(R.id.supsisChatView).apply {
            initialize("sizin_domain_adiniz") // Domain adınızı buraya girin
            open() // Uygulama açıldığında sohbeti otomatik açar
            setUserData(
                mapOf(
                    "email" to "kullanici@ornek.com",
                    "name" to "Ahmet Yılmaz"
                )
            )
            setDepartment("Destek")
        }

        // Butonları tanımlıyoruz
        buttonOpenChat = findViewById(R.id.buttonOpenChat)
        buttonCloseChat = findViewById(R.id.buttonCloseChat)

        // "Sohbeti Aç" butonuna tıklanınca sohbeti açıyoruz
        buttonOpenChat.setOnClickListener {
            supsisChatView?.open()
        }

        // "Sohbeti Kapat" butonuna tıklanınca sohbeti kapatıyoruz
        buttonCloseChat.setOnClickListener {
            supsisChatView?.close()
        }
    }
}

```

## Lisans

Bu proje MIT Lisansı altında lisanslanmıştır.

## Destek
Herhangi bir sorunla karşılaşırsanız veya sorunuz varsa, lütfen GitHub deposu üzerinden bir sorun (issue) açın.
