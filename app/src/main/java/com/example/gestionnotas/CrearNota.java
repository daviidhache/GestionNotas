package com.example.gestionnotas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class CrearNota extends AppCompatActivity {

            AD ad;
            EditText descripcion;
            ImageButton btn;
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_nota);
        descripcion = findViewById(R.id.editDescripcion);
        btn = findViewById(R.id.botonInsertar);
        ad = new AD(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Nota n = new Nota();
                n.setDescripcion(descripcion.getText().toString());
                if(ad.insertarNota(n)){
                    Toast.makeText(CrearNota.this, "Nota añadida", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(CrearNota.this, "Error al añadir nota", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.back){
            Intent i = new Intent(CrearNota.this,MainActivity.class);
            startActivity(i);
        }

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflar = getMenuInflater();
        inflar.inflate(R.menu.menu2,menu);
        return true;
    }
}