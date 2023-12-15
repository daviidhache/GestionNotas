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

public class ModificarActivity extends AppCompatActivity {

    EditText txt;
    ImageButton actualizar;
    Nota n;

    AD ad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);
        txt = findViewById(R.id.editModifica);
        actualizar = findViewById(R.id.imgModificar);
        ad = new AD(this);
        n = getIntent().getParcelableExtra("nota");

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    // Update
                        String id = String.valueOf(n.getId());
                        if(ad.actualizarNota( id,txt.getText().toString())){
                            Toast.makeText(ModificarActivity.this, "Nota actualizada", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(i);

                        }
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.back){
            Intent i = new Intent(ModificarActivity.this,MainActivity.class);
            startActivity(i);
            finish();
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