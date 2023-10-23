package com.example.aplicacionbasica;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View button) {
                LinearLayout content = findViewById(R.id.linearlayout);
                TextView newText = new TextView(button.getContext());
                newText.setText(R.string.textItem);
                content.addView(newText);

            }
        });
    }

}