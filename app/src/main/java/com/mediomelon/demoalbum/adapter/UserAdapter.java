package com.mediomelon.demoalbum.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mediomelon.demoalbum.R;
import com.mediomelon.demoalbum.model.entity.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private Context mContext;
    private List<User> listUser;

    // viewholder accede a todas las vistas
    static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        @BindView(R.id.txtxUserNombre)
        TextView txtnombre;
        @BindView(R.id.txtxUserCorreo)
        TextView txtcorreo;
        @BindView(R.id.imageViewUsers)
        ImageView imgPhotoUser;

        ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    // constructor
    public UserAdapter(Context context, ArrayList<User> listUser) {
        this.listUser = listUser;
        mContext = context;
    }

    // Crea nuevas vistas (invocadas pot el layout manager)
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // crear una nueva vista
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_user, parent, false);

        return new ViewHolder(v);
    }

    // Reemplazar el contenido del layout manager
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // - obtiene los elementos del dataset en una posicion definida
        holder.txtnombre.setText(listUser.get(position).getName());
        holder.txtcorreo.setText(listUser.get(position).getEmail());
        holder.imgPhotoUser.setImageResource(listUser.get(position).getPhoto());

        //metodoOnclick() para cada elemento del recyclerview
        holder.itemView.setOnClickListener(v -> {
            //envio de datos

        });

    }

    // Devuelve el tamaño de tu dataset
    @Override
    public int getItemCount() {
        return listUser.size();
    }
}
