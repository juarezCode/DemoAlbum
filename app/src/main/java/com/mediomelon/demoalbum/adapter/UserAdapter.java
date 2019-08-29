package com.mediomelon.demoalbum.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mediomelon.demoalbum.R;
import com.mediomelon.demoalbum.model.entity.User;
import com.mediomelon.demoalbum.view.activity.DetailUserActivity;
import com.mediomelon.demoalbum.view.activity.MainActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.Holder> {

    private Context mContext;
    private List<User> listUser;

    // viewholder accede a todas las vistas
    static class Holder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        @BindView(R.id.txtxUserNombre)
        TextView txtnombre;
        @BindView(R.id.txtxUserCorreo)
        TextView txtcorreo;
        @BindView(R.id.imageViewUsers)
        ImageView imgPhotoUser;
        @BindView(R.id.btnUserEdit)
        Button btnEditUser;
        @BindView(R.id.btnUserDelete)
        Button btnDeleteUser;

        Holder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    // constructor
    public UserAdapter(Context context, List<User> listUser) {
        this.listUser = listUser;
        mContext = context;
    }

    // Crea nuevas vistas (invocadas pot el layout manager)
    @Override
    public UserAdapter.Holder onCreateViewHolder(ViewGroup parent,
                                                 int viewType) {
        // crear una nueva vista
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_user, parent, false);

        return new Holder(v);
    }

    // Reemplazar el contenido del layout manager
    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        // - obtiene los elementos del dataset en una posicion definida
        holder.txtnombre.setText(listUser.get(position).getName());
        holder.txtcorreo.setText(listUser.get(position).getEmail());
        holder.imgPhotoUser.setImageResource(listUser.get(position).getPhoto());

        //update User
        holder.btnEditUser.setOnClickListener(v -> {
            User user = MainActivity.dataBase.userDao().getUserById(Integer.parseInt(listUser.get(position).getId()));

            AlertDialog.Builder mBuilder = new AlertDialog.Builder(mContext);
            View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_edit_user, null);
            EditText nombre = view.findViewById(R.id.edtNombre);
            EditText email = view.findViewById(R.id.edtEmail);
            EditText username = view.findViewById(R.id.edtUsername);
            EditText company = view.findViewById(R.id.edtCompany);
            EditText website = view.findViewById(R.id.edtWebSite);
            EditText phone = view.findViewById(R.id.edtPhone);
            Button btnSave = view.findViewById(R.id.btnSave);
            Button btnCancel = view.findViewById(R.id.btnCancel);

            nombre.setText(user.getName());
            email.setText(user.getEmail());
            username.setText(user.getUsername());
            company.setText(user.getCompany().getName());
            website.setText(user.getWebsite());
            phone.setText(user.getPhone());

            mBuilder.setView(view);
            AlertDialog dialog = mBuilder.create();

            btnSave.setOnClickListener(v1 -> {
                if (!nombre.getText().toString().isEmpty() &&
                        !email.getText().toString().isEmpty() &&
                        !username.getText().toString().isEmpty() &&
                        !company.getText().toString().isEmpty() &&
                        !website.getText().toString().isEmpty() &&
                        !phone.getText().toString().isEmpty()) {

                    user.setName(nombre.getText().toString());
                    user.setEmail(email.getText().toString());
                    user.setUsername(username.getText().toString());
                    user.getCompany().setName(company.getText().toString());
                    user.setWebsite(website.getText().toString());
                    user.setPhone(phone.getText().toString());

                    MainActivity.dataBase.userDao().updateUser(user);
                    Toast.makeText(mContext, "success update", Toast.LENGTH_SHORT).show();
                    dialog.cancel();



                }else
                    Toast.makeText(mContext, "failed update", Toast.LENGTH_SHORT).show();
            });
            btnCancel.setOnClickListener(v12 -> {
                dialog.cancel();
            });

            dialog.show();

        });

        //delete User
        holder.btnDeleteUser.setOnClickListener(v -> {
            User user = MainActivity.dataBase.userDao().getUserById(Integer.parseInt(listUser.get(position).getId()));
            if(user.getStatus() != "Deleted"){
                user.setStatus("Deleted");
                MainActivity.dataBase.userDao().deleteUser(user);
                Toast.makeText(mContext,"user deleted successfull", Toast.LENGTH_SHORT).show();

            }else
                Toast.makeText(mContext,"no existe el usuario", Toast.LENGTH_SHORT).show();

        });


        //metodoOnclick() para cada elemento del recyclerview
        holder.imgPhotoUser.setOnClickListener(v -> {
            //envio de datos
            Intent intent = new Intent(mContext, DetailUserActivity.class);

            String name = listUser.get(position).getName();
            String username = listUser.get(position).getUsername();
            String website = listUser.get(position).getWebsite();

            String address = listUser.get(position).getAddress().getCity()
                    + "," + listUser.get(position).getAddress().getStreet()
                    + "," + listUser.get(position).getAddress().getSuite()
                    + "," + listUser.get(position).getAddress().getZipcode();

            String company = listUser.get(position).getCompany().getName();
            String email = listUser.get(position).getEmail();
            String phone = listUser.get(position).getPhone();
            String photo = String.valueOf(listUser.get(position).getPhoto());

            intent.putExtra("name", name);
            intent.putExtra("username", username);
            intent.putExtra("website", website);
            intent.putExtra("address", address);
            intent.putExtra("company", company);
            intent.putExtra("email", email);
            intent.putExtra("phone", phone);
            intent.putExtra("photo", photo);

            mContext.startActivity(intent);

        });

    }

    // Devuelve el tamaño de tu dataset
    @Override
    public int getItemCount() {
        return listUser.size();
    }
}
