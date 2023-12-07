package com.example.aplicacionbasica;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.aplicacionbasica.ui.login.LoginActivity;

public class Register extends AppCompatActivity {

    private EditText editTextEmail, editTextNombre, editTextApellido, editTextUsuario, editTextContraseña;
    private Button buttonIrALogin, buttonRegistrarse;
    private SharedPreferences sharedPreferences;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        editTextEmail = findViewById(R.id.Email);
        editTextNombre = findViewById(R.id.Nombre);
        editTextApellido = findViewById(R.id.Apellido);
        editTextUsuario = findViewById(R.id.Usuario);
        editTextContraseña = findViewById(R.id.Contraseña);

        Button buttonIrALogin = findViewById(R.id.buttonIrALogin);
        buttonIrALogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View buttonIrLogin) {
                Intent intent = new Intent(Register.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        Button buttonRegistrarse = findViewById(R.id.Button_registro);
        buttonRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View buttonRegistrarse) {
                String email = editTextEmail.getText().toString().trim();
                String nombre = editTextNombre.getText().toString().trim();
                String apellido = editTextApellido.getText().toString().trim();
                String usuario = editTextUsuario.getText().toString().trim();
                String contraseña = editTextContraseña.getText().toString().trim();

                if (email.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || usuario.isEmpty() || contraseña.isEmpty()) {
                    Toast.makeText(Register.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    sharedPreferences = getSharedPreferences("MisPreferencias", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("email", email);
                    editor.putString("contraseña", contraseña);
                    editor.apply();

                    Toast.makeText(Register.this, "Datos guardados localmente", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}