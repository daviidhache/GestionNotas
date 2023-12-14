package com.example.gestionnotas;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Date;

public class Nota  implements Parcelable {
    private int id;
    private String descripcion;

    public Nota(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public Nota(){

    }

    protected Nota(Parcel in) {
        id = in.readInt();
        descripcion = in.readString();
    }

    public static final Creator<Nota> CREATOR = new Creator<Nota>() {
        @Override
        public Nota createFromParcel(Parcel in) {
            return new Nota(in);
        }

        @Override
        public Nota[] newArray(int size) {
            return new Nota[size];
        }
    };

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return   "Nota: " + id +   "\n" + "---------------" + "\n" +
                 descripcion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(descripcion);
    }
}
