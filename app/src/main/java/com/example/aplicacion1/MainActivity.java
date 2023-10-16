package com.example.aplicacion1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import animals.*;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Cat michi = new Cat("Bola de nieve", 2, 0, 12);
        Dog firulais = new Dog("Firulais", 6, 1, 4);
        Log.d(TAG, michi.greet());
        TextView myText = new TextView(context this);
        myText.setText("Hello World");
        Log.d(tag:"test", msg "testing app");


    }
}