package com.profuturo.todo;

import android.graphics.drawable.Drawable;

/**
 * Created by LA501857 on 14/09/2017.
 */

public class Tasks {

    private int Id;
    private String Tarea;
    private String Fecha;
    private String Descripcion;
    private Drawable Foto;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTarea() {
        return Tarea;
    }

    public void setTarea(String tarea) {
        Tarea = tarea;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public Drawable getFoto() {
        return Foto;
    }

    public void setFoto(Drawable foto) {
        Foto = foto;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }


}
