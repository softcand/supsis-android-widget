package com.supsis.supsiswidget

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.supsis.supsis_android_preview.SupsisChatView
import com.supsis.supsiswidget.ui.theme.SupsisWidgetTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SupsisWidgetTheme {
                SupsisChatScreen()
            }
        }
    }
}

@Composable
fun SupsisChatScreen() {
    // `SupsisChatView` referansını tutmak için `remember` kullanıyoruz
    val supsisChatView = remember { mutableStateOf<SupsisChatView?>(null) }

    Column(modifier = Modifier.fillMaxSize()) {
        // `AndroidView` içinde `SupsisChatView`'i oluşturuyoruz
        AndroidView(
            modifier = Modifier.weight(1f),
            factory = { context ->
                SupsisChatView(context).apply {
                    initialize("supsis") // Domain adınızı buraya girin
                    open() // İlk açılışta açık olarak başlatmak için
                    setUserData(
                        mapOf(
                            "email" to "John@doe.com",
                            "name" to "John Doe"
                        )
                    )
                    setDepartment("Support")
                    supsisChatView.value = this // `SupsisChatView` referansını kaydediyoruz
                }
            }
        )

        // Butonları yan yana yerleştirmek için Row kullanıyoruz
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Open Chat butonu
            Button(onClick = {
                supsisChatView.value?.open() // `open()` fonksiyonunu çağırır
            }) {
                Text("Open Chat")
            }

            // Close Chat butonu
            Button(onClick = {
                supsisChatView.value?.close() // `close()` fonksiyonunu çağırır
            }) {
                Text("Close Chat")
            }
        }
    }
}
