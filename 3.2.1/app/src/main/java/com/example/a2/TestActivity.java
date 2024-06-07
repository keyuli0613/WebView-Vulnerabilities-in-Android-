package com.example.a2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class TestActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Uri uri = intent.getData();
        //Uri uri = Uri.parse(intent.getData().toString());

        Log.d("evil", "Scheme: " + uri.getScheme());
        Log.d("evil", "UserInfo: " + uri.getUserInfo());
        Log.d("evil", "Host: " + uri.getHost());
        Log.d("evil", "toString(): " + uri.toString());
    }
}
