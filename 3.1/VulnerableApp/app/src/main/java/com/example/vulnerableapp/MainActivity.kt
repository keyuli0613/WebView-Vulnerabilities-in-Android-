package com.example.vulnerableapp

import android.net.Uri
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val webView: WebView = findViewById(R.id.webView)
        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true




        webView.addJavascriptInterface(WebAppInterface(), "Android")


        val uri = intent.data
        if (uri != null) {
            webView.loadUrl(uri.toString())
        }
    }

    inner class WebAppInterface {
        @JavascriptInterface
        fun getFlag(): String {
            return "This is a sensitive flag!"
        }
    }
}
