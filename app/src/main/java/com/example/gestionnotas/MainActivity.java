package com.example.gestionnotas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    AD ad;

    SearchView busqueda;
  ArrayAdapter adaptador;


    ListView listaNotas;

    ArrayList<Nota> notas =  new ArrayList<Nota>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ad = new AD(this);
        busqueda = findViewById(R.id.busqueda3);

        notas = ad.obtenerNotas();
        listaNotas = findViewById(R.id.listaNotas);
       adaptador = new ArrayAdapter<Nota>(getApplicationContext(), android.R.layout.simple_list_item_1,notas);
        listaNotas.setAdapter(adaptador);
        adaptador.notifyDataSetChanged();


        busqueda.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Consulta

                adaptador.clear();
                adaptador.addAll(ad.obtenerNotas(query));
                adaptador.notifyDataSetChanged();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {


                return false;
            }
        });

        busqueda.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                adaptador.clear();
                adaptador.addAll(ad.obtenerNotas());
                adaptador.notifyDataSetChanged();
                return false;
            }
        });



        listaNotas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
                dialogo.setTitle("¿Desea eliminar la nota?");
                dialogo.setCancelable(false);

                dialogo.setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Eliminar registro
                        Nota n = notas.get(position);
                        ad.eleminarNota(n);
                        adaptador.clear();
                        adaptador.addAll(ad.obtenerNotas());
                        adaptador.notifyDataSetChanged();
                    }
                });
                dialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                    }
                });

                dialogo.show();

            }
        });

        listaNotas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(getApplicationContext(),ModificarActivity.class);
                Nota n = notas.get(position);
                i.putExtra("nota",n);
                startActivity(i);
                finish();

                return false;
            }
        });

    }




    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.salir){
            finish();
        }if (id == R.id.agregar){
            Intent i = new Intent(MainActivity.this,CrearNota.class);
            startActivity(i);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflar = getMenuInflater();
        inflar.inflate(R.menu.menu,menu);


        return true;
    }




}