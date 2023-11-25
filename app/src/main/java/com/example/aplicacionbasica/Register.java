// Register.java
package com.example.aplicacionbasica;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    private EditText editTextEmail, editTextNombre, editTextApellido, editTextUsuario, editTextContraseña;
    private Button buttonIrALogin, buttonRegistrarse;
    private SharedPreferences sharedPreferences;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        // Inicializar vistas
        editTextEmail = findViewById(R.id.Email);
        editTextNombre = findViewById(R.id.Nombre);
        editTextApellido = findViewById(R.id.Apellido);
        editTextUsuario = findViewById(R.id.Usuario);
        editTextContraseña = findViewById(R.id.Contraseña);

        // Botón para ir directamente a la pantalla de inicio de sesión
        Button buttonIrALogin = findViewById(R.id.buttonIrALogin);
        buttonIrALogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View buttonIrLogin) {
                // Iniciar la actividad Login al hacer clic en el botón
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });

        // Botón para registrar y luego ir a la pantalla de inicio de sesión
        Button buttonRegistrarse = findViewById(R.id.Button_registro);
        buttonRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View buttonRegistrarse) {
                // Obtener los datos ingresados por el usuario
                String email = editTextEmail.getText().toString().trim();
                String nombre = editTextNombre.getText().toString().trim();
                String apellido = editTextApellido.getText().toString().trim();
                String usuario = editTextUsuario.getText().toString().trim();
                String contraseña = editTextContraseña.getText().toString().trim();

                // Validar que los campos no estén vacíos
                if (email.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || usuario.isEmpty() || contraseña.isEmpty()) {
                    Toast.makeText(Register.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    // Guardar los datos en SharedPreferences
                    sharedPreferences = getSharedPreferences("MisPreferencias", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("email", email);
                    editor.putString("contraseña", contraseña);
                    editor.apply();

                    // Mostrar mensaje de éxito
                    Toast.makeText(Register.this, "Datos guardados localmente", Toast.LENGTH_SHORT).show();

                    // Iniciar la actividad de inicio de sesión al hacer clic en el botón de registro
                    Intent intent = new Intent(Register.this, Login.class);
                    startActivity(intent);
                }
            }
        });
    }
}
