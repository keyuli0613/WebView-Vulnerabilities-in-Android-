package com.example.a4;

import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.net.URISyntaxException;

public class URLWebView extends AppCompatActivity {
    class JsObject {
        @JavascriptInterface
        public String getToken() {
            Log.e("rebeyond","i am in getToken");
            return "{\"token\":\"1234567890abcdefg\"}";
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = (WebView) findViewById(R.id.Wind_webview4);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
        webView.addJavascriptInterface(new JsObject(),"myObj");
        String inputUrl="https://www.site1.com/redirect.php?url=http://localhost:8080/poc.htm"; //ip地址自己写自己的
        try {
            if (checkDomain(inputUrl))
            {
                Log.e("rebeyond","i am a white domain");
                //webView.loadUrl(inputUrl);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    private static boolean checkDomain(String inputUrl) throws  URISyntaxException {
        if (!inputUrl.startsWith("http://")&&!inputUrl.startsWith("https://"))
        {
            return false;
        }
        String[] whiteList=new String[]{"site1.com","site2.com"};
        java.net.URI url=new java.net.URI(inputUrl);
        String inputDomain=url.getHost(); //提取host
        for (String whiteDomain:whiteList)
        {
            if (inputDomain.endsWith("."+whiteDomain)) //www.site1.com      app.site2.com
                return true;
        }
        return  false;
    }

}
