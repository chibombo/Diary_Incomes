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

public class taskAdapter extends RecyclerView.Adapter<taskAdapter.TaskViewHolder> {

    private List<Tasks> items;
    private OnItemClickListener listener;

    public interface OnItemClickListener
    {
        void onItemClick(Tasks item);
    }



    public static class TaskViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView imagen;
        public TextView Tarea;
        public TextView Fecha;
        public TextView Descripcion;

        public TaskViewHolder(View view)
        {
            super(view);
            imagen = (ImageView) view.findViewById(R.id.imageZelda);
            Tarea = (TextView) view.findViewById(R.id.txtViewTask);
            Fecha = (TextView) view.findViewById(R.id.txtViewFecha);
            Descripcion = (TextView) view.findViewById(R.id.txtViewDescripcion);

        }
        public void bind(final Tasks item, final OnItemClickListener listener)
        {
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v)
                {
                    listener.onItemClick(item);
                }
            });
        }
    }

    public taskAdapter(List<Tasks> items, OnItemClickListener listener)
    {
        this.listener = listener;
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item_view, viewGroup, false);
        return new TaskViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder viewHolder, int i) {
        viewHolder.imagen.setImageResource(R.drawable.zelda);
        viewHolder.Tarea.setText("Tarea:"+items.get(i).getTarea());
        viewHolder.Fecha.setText(items.get(i).getFecha());
        viewHolder.Descripcion.setText("Descripcion:\n"+items.get(i).getDescripcion());
        viewHolder.bind(items.get(i),listener);
    }

}
