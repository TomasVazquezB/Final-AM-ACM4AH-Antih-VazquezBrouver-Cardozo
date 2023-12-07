// ReiniciarContra.java
package com.example.aplicacionbasica;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                // Obtener la nueva contraseña ingresada por el usuario
                String nuevaContraseña = editTextNuevaContraseña.getText().toString().trim();

                // Validar que la contraseña no esté vacía
                if (!nuevaContraseña.isEmpty()) {
                    // Guardar la nueva contraseña en SharedPreferences
                    sharedPreferences = getSharedPreferences("MisPreferencias", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("contraseña", nuevaContraseña);
                    editor.apply();

                    // Mostrar mensaje de éxito
                    Toast.makeText(ReiniciarContra.this, "Contraseña restablecida con éxito", Toast.LENGTH_SHORT).show();

                    // Redirigir a la pantalla de inicio de sesión (Login)
                    startActivity(new Intent(ReiniciarContra.this, LoginActivity.class));
                    finish();  // Cierra la actividad actual para evitar volver atrás con el botón de retroceso
                } else {
                    // Mostrar mensaje de error si la contraseña está vacía
                    Toast.makeText(ReiniciarContra.this, "Ingrese una nueva contraseña", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Método para crear una nueva intención
    public static Intent newIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }
}
