package com.example.aplicacionbasica;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.aplicacionbasica.ui.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    private EditText editTextEmail, editTextNombre, editTextApellido, editTextUsuario, editTextContraseña;
    private SharedPreferences sharedPreferences;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

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
        buttonIrALogin.setOnClickListener(buttonIrLogin -> {
            Intent intent = new Intent(Register.this, LoginActivity.class);
            startActivity(intent);
        });

        Button buttonRegistrarse = findViewById(R.id.Button_registro);
        buttonRegistrarse.setOnClickListener(buttonRegistrarse1 -> {
            String email = editTextEmail.getText().toString().trim();
            String nombre = editTextNombre.getText().toString().trim();
            String apellido = editTextApellido.getText().toString().trim();
            String usuario = editTextUsuario.getText().toString().trim();
            String contraseña = editTextContraseña.getText().toString().trim();

            if (email.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || usuario.isEmpty() || contraseña.isEmpty()) {
                Toast.makeText(Register.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            } else {
                mAuth.createUserWithEmailAndPassword(email, contraseña)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    saveUserDataToFirestore(email, nombre, apellido, usuario, contraseña);
                                    Toast.makeText(Register.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Register.this, LoginActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(Register.this, "Error al registrarse: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    private void saveUserDataToFirestore(String email, String nombre, String apellido, String usuario, String contraseña) {
        Map<String, Object> user = new HashMap<>();
        user.put("email", email);
        user.put("nombre", nombre);
        user.put("apellido", apellido);
        user.put("usuario", usuario);
        user.put("contraseña", contraseña);

        db.collection("users")
                .document(mAuth.getCurrentUser().getUid())
                .set(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                        } else {
                            Toast.makeText(Register.this, "Error al guardar datos en Firestore", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
