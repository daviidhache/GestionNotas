package com.example.gestionnotas;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class AD {
    private Context contexto;
    BD mybd;
    public AD(Context context){
        this.contexto = context;
        mybd = new BD(contexto);
    }


    public ArrayList<Nota> obtenerNotas() {
        ArrayList<Nota> lista = new ArrayList<Nota>();
        // Consulta
            String[] campos = {"id","descripcion"};
            // Acceso para lectura
                SQLiteDatabase consulta = mybd.getReadableDatabase();
                Cursor c = consulta.query("notas",campos,null,null,null,null,null);
                while(c.moveToNext()){
                    Nota n = new Nota(c.getInt(0),c.getString(1));
                    lista.add(n);

                }



        return lista;
    }

    public boolean insertarNota(Nota n) {
        boolean resultado = false;
        // Realizamos insert
        SQLiteDatabase insertar = mybd.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("descripcion",n.getDescripcion());
        long salida = insertar.insert("notas",null,registro);
        if(salida != -1){
            resultado = true;
        }

        return resultado;
    }

    public ArrayList<Nota> obtenerNotas(String condicion) {
        ArrayList<Nota> lista = new ArrayList<Nota>();
        // Consulta
        String[] campos = {"id","descripcion"};
        String[] args = {condicion};
        // Acceso para lectura
        SQLiteDatabase consulta = mybd.getReadableDatabase();
        Cursor c = consulta.query("notas",campos,"id=?",args,null,null,null);
        while(c.moveToNext()){
            Nota n = new Nota(c.getInt(0),c.getString(1));
            lista.add(n);

        }



        return lista;
    }

    public boolean actualizarNota(String id, String mensaje) {
        boolean resultado = false;
        SQLiteDatabase actualizar = mybd.getWritableDatabase();
        String[] args  = {id};
        ContentValues registro = new ContentValues();
        registro.put("descripcion",mensaje);
       int salida = actualizar.update("notas",registro,"id=?", args);
       if(salida > 0){
           resultado = true;
       }
        return resultado;
    }

    public void eleminarNota(Nota n) {
        SQLiteDatabase eliminar = mybd.getWritableDatabase();
        eliminar.delete("notas","id=" + String.valueOf(n.getId()),null);


    }
}
