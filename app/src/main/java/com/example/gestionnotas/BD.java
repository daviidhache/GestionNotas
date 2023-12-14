package com.example.gestionnotas;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class BD  extends SQLiteOpenHelper {
    Context contexto;




    public static String crearTabla = "CREATE TABLE notas ( id integer primary key autoincrement, descripcion TEXT)";

   public BD(@Nullable Context context) {
        super(context, "NotasBD", null, 1);
        contexto = context;
    }




    @Override
    public void onCreate(SQLiteDatabase db) {

        try{
            db.execSQL(crearTabla);
            Toast.makeText(contexto, "Se ha creado la base de datos", Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            e.printStackTrace();
            Toast.makeText(this.contexto, "Error al crear BD", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS notas");
        onCreate(db);
    }


}
