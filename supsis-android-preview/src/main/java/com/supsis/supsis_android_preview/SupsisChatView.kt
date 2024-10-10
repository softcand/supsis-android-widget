package com.supsis.supsis_android_preview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import com.google.gson.Gson

class SupsisChatView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val webView: WebView
    private var domainName: String = ""
    private var isInitialized = false

    init {
        LayoutInflater.from(context).inflate(R.layout.view_supsischat, this, true)
        webView = findViewById(R.id.webView)
        setupWebView()
    }

    private fun setupWebView() {
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()
    }

    fun initialize(domainName: String) {
        this.domainName = domainName
        val url = "https://${domainName}.visitor.supsis.live"
        webView.loadUrl(url)
        isInitialized = true
    }

    fun open() {
        if (!isInitialized) {
            throw IllegalStateException("SupsisChatView is not initialized. Call initialize() first.")
        }
        this.visibility = VISIBLE
    }

    fun close() {
        this.visibility = GONE
    }

    fun setUserData(userData: Map<String, String>) {
        val jsonData = Gson().toJson(userData)
        val script = """
            window.postMessage({
                command: 'set-user-data',
                payload: $jsonData
            });
        """.trimIndent()
        injectJavaScript(script)
    }

    fun setDepartment(department: String) {
        val script = """
            window.postMessage({
                command: 'set-department',
                payload: '$department'
            });
        """.trimIndent()
        injectJavaScript(script)
    }

    private fun injectJavaScript(script: String) {
        post {
            webView.evaluateJavascript(script, null)
        }
    }
}
