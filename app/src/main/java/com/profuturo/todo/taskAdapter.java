package com.profuturo.todo;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by LA501857 on 14/09/2017.
 */

public class taskAdapter extends RecyclerView.Adapter<taskAdapter.JugadoresViewHolder> {
    private List<Tasks> items;
    public static class JugadoresViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView imagen;
        public TextView Tarea;
        public TextView Fecha;
        public TextView Descripcion;

        public JugadoresViewHolder(View view)
        {
            super(view);
            imagen = (ImageView) view.findViewById(R.id.imageZelda);
            Tarea = (TextView) view.findViewById(R.id.txtViewTask);
            Fecha = (TextView) view.findViewById(R.id.txtViewFecha);
            Descripcion = (TextView) view.findViewById(R.id.txtViewDescripcion);

        }
    }

    public taskAdapter(List<Tasks> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public JugadoresViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item_view, viewGroup, false);
        return new JugadoresViewHolder(v);
    }

    @Override
    public void onBindViewHolder(JugadoresViewHolder viewHolder, int i) {
        viewHolder.imagen.setImageResource(R.drawable.zelda);
        viewHolder.Tarea.setText("Tarea:"+items.get(i).getTarea());
        viewHolder.Fecha.setText(items.get(i).getFecha());
        viewHolder.Descripcion.setText("Descripcion:\n"+items.get(i).getDescripcion());

    }

}
