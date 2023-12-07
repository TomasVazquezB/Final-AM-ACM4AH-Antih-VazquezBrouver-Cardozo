package com.example.aplicacionbasica;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.aplicacionbasica.ui.login.LoginActivity;

public class ReiniciarContra extends AppCompatActivity {

    private EditText editTextNuevaContraseña;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olvidocontra);

        editTextNuevaContraseña = findViewById(R.id.Contraseña_recuperada);

        Button buttonRestablecerContraseña = findViewById(R.id.recuperar_contraseña);
        buttonRestablecerContraseña.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nuevaContraseña = editTextNuevaContraseña.getText().toString().trim();

                if (!nuevaContraseña.isEmpty()) {

                    sharedPreferences = getSharedPreferences("MisPreferencias", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("contraseña", nuevaContraseña);
                    editor.apply();

                    Toast.makeText(ReiniciarContra.this, "Contraseña restablecida con éxito", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(ReiniciarContra.this, LoginActivity.class));
                    finish();
                } else {
                    Toast.makeText(ReiniciarContra.this, "Ingrese una nueva contraseña", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }
}
