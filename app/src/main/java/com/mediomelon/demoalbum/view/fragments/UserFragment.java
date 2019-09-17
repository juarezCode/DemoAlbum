package com.mediomelon.demoalbum.view.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mediomelon.demoalbum.R;
import com.mediomelon.demoalbum.adapter.UserAdapter;
import com.mediomelon.demoalbum.dao.AlbumDataBase;
import com.mediomelon.demoalbum.model.entity.User;
import com.mediomelon.demoalbum.utils.Constants;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {
    @BindView(R.id.recyclerViewUser)
    RecyclerView recyclerViewUser;
    @BindView(R.id.addUser)
    Button addUser;
    @BindView(R.id.txtEmpty)
    TextView txtEmpty;
    private static final String TAG = "UserFragment";
    ArrayList<User> listUser;
    private AlbumDataBase albumDataBase;

    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            listUser = (ArrayList<User>) getArguments().getSerializable("listUser");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        ButterKnife.bind(this, view);
        albumDataBase = AlbumDataBase.getDataBase(getContext());

        if (listUser.isEmpty())
            recyclerViewUser.setVisibility(View.GONE);
        else
            txtEmpty.setVisibility(View.GONE);

        recyclerViewUser.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewUser.setLayoutManager(linearLayoutManager);

        UserAdapter userAdapter = new UserAdapter(getContext(), listUser);
        recyclerViewUser.setAdapter(userAdapter);

        userAdapter.setOnItemClickListener(new UserAdapter.OnItemClickListener() {
            @Override
            public void onUpdateClick(int position) {

                //obtener objeto a actualizar por el id
                //User user = LoginActivity.albumDataBase.userDao().getUserById(listUser.get(position).getId());
                User user = albumDataBase.userDao().getUserById(listUser.get(position).getId());

                //configuracion cuadro de dialogo
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
                View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_edit_user, null);
                //bindeo de vistas

                TextInputEditText nombre = view.findViewById(R.id.edtNombre);
                TextInputEditText password = view.findViewById(R.id.edtPassword);
                EditText email = view.findViewById(R.id.edtEmail);
                EditText username = view.findViewById(R.id.edtInsertName);
                EditText company = view.findViewById(R.id.edtCompany);
                EditText address = view.findViewById(R.id.edtAddress);
                EditText website = view.findViewById(R.id.edtWebSite);
                EditText phone = view.findViewById(R.id.edtPhone);
                Button btnSave = view.findViewById(R.id.btnSave);
                Button btnCancel = view.findViewById(R.id.btnCancel);
                //mostrar datos del objeto de DB en vistas

                nombre.setText(user.getName());
                email.setText(user.getEmail());
                username.setText(user.getUsername());
                password.setText(user.getPassword());
                company.setText(user.getCompany().getName());
                address.setText(user.getAddress().getStreet());
                website.setText(user.getWebsite());
                phone.setText(user.getPhone());

                mBuilder.setView(view);
                AlertDialog dialog = mBuilder.create();
                //boton guardar del cuadro de dialogo
                btnSave.setOnClickListener(v1 -> {
                    if (!nombre.getText().toString().isEmpty() && //validar campos vacios
                            !email.getText().toString().isEmpty() &&
                            !username.getText().toString().isEmpty() &&
                            !password.getText().toString().isEmpty() &&
                            !company.getText().toString().isEmpty() &&
                            !address.getText().toString().isEmpty() &&
                            !website.getText().toString().isEmpty() &&
                            !phone.getText().toString().isEmpty()) {

                        //actualizar objeto con nuevas propiedades
                        user.setName(nombre.getText().toString());
                        user.setEmail(email.getText().toString());
                        user.setUsername(username.getText().toString());
                        user.setPassword(password.getText().toString());
                        user.getCompany().setName(company.getText().toString());
                        user.getAddress().setStreet(address.getText().toString());
                        user.setWebsite(website.getText().toString());
                        user.setPhone(phone.getText().toString());

                        //update object in database and recycler
                        //LoginActivity.albumDataBase.userDao().updateUser(user);
                        albumDataBase.userDao().updateUser(user);
                        listUser.set(position, user);
                        userAdapter.notifyItemChanged(position);
                        Toast.makeText(getContext(), "successful updated", Toast.LENGTH_SHORT).show();
                        dialog.cancel();


                    } else
                        Toast.makeText(getContext(), "insert data!!", Toast.LENGTH_SHORT).show();
                });
                //boton cancelar operacion
                btnCancel.setOnClickListener(v12 -> dialog.cancel());

                dialog.setCancelable(false);
                dialog.show();
            }

            @Override
            public void onDeleteClick(int position) {
                //obtener objeto a eliminar por el id
                //User user = LoginActivity.albumDataBase.userDao().getUserById(listUser.get(position).getId());
                User user = albumDataBase.userDao().getUserById(listUser.get(position).getId());

                //validacion si el objeto no esta eliminado
                if (!user.getStatus().equals(Constants.STATUS_DELETED)) {
                    user.setStatus(Constants.STATUS_DELETED);
                    //MainActivity.albumDataBase.userDao().deleteUserById(id);
                    //LoginActivity.albumDataBase.userDao().deleteUser(user);
                    albumDataBase.userDao().deleteUser(user);

                    //update object in database and recycler
                    listUser.remove(position);
                    if (listUser.isEmpty()) {
                        recyclerViewUser.setVisibility(View.GONE);
                        txtEmpty.setVisibility(View.VISIBLE);
                    }
                    userAdapter.notifyItemRemoved(position);
                    Toast.makeText(getContext(), "successful deleted", Toast.LENGTH_SHORT).show();

                } else
                    Toast.makeText(getContext(), "user " + user.getName() + " not found", Toast.LENGTH_SHORT).show();
            }
        });

        //add new user
        addUser.setOnClickListener(v -> {

            //obtener objeto con el id mayor
            //User userMax = LoginActivity.albumDataBase.userDao().getMaxId();
            User userMax = albumDataBase.userDao().getMaxId();

            //configuracion cuadro de dialogo
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
            View vista = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_user, null);
            //bindeo de vistas
            EditText addName = vista.findViewById(R.id.edtAddName);
            EditText addEmail = vista.findViewById(R.id.edtAddEmail);
            EditText addUsername = vista.findViewById(R.id.edtAddUsername);
            TextInputEditText addPassword = vista.findViewById(R.id.edtAddPassword);
            EditText addCompany = vista.findViewById(R.id.edtAddCompany);
            EditText addAddress = vista.findViewById(R.id.edtAddAddress);
            EditText addWebsite = vista.findViewById(R.id.edtAddWebsite);
            EditText addPhone = vista.findViewById(R.id.edtAddPhone);
            Button btnAddSave = vista.findViewById(R.id.btnAddSave);
            Button btnAddCancel = vista.findViewById(R.id.btnAddCancel);

            mBuilder.setView(vista);
            AlertDialog dialog = mBuilder.create();

            //boton guardar del cuadro de dialogo
            btnAddSave.setOnClickListener(v13 -> {
                if (!addName.getText().toString().isEmpty() && //validar campos vacios
                        !addEmail.getText().toString().isEmpty() &&
                        !addUsername.getText().toString().isEmpty() &&
                        !addPassword.getText().toString().isEmpty() &&
                        !addCompany.getText().toString().isEmpty() &&
                        !addAddress.getText().toString().isEmpty() &&
                        !addWebsite.getText().toString().isEmpty() &&
                        !addPhone.getText().toString().isEmpty()) {

                    //crear nuevo objeto con sus propiedades
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd 'at' HH:mm:ss", Locale.getDefault());
                    User user = new User();

                    user.setId(userMax.getId() + 1);
                    user.setPhoto(Constants.MAN_2);
                    user.setStatus(Constants.STATUS_ACTIVE);
                    String currentDateandTime = sdf.format(new Date());
                    user.setDate(currentDateandTime);
                    user.setName(addName.getText().toString());
                    user.setEmail(addEmail.getText().toString());
                    user.setUsername(addUsername.getText().toString());
                    user.setPassword(addPassword.getText().toString());
                    user.setCompany(new User.Company(addCompany.getText().toString()));
                    user.setAddress(new User.Address(addAddress.getText().toString(), "", "", ""));
                    user.setWebsite(addWebsite.getText().toString());
                    user.setPhone(addPhone.getText().toString());

                    //update object in database and recycler
                    //LoginActivity.albumDataBase.userDao().addUser(user);
                    albumDataBase.userDao().addUser(user);
                    listUser.add(user);
                    userAdapter.notifyItemInserted(listUser.size() - 1);
                    Toast.makeText(getContext(), "successful added", Toast.LENGTH_SHORT).show();
                    dialog.cancel();

                    recyclerViewUser.setVisibility(View.VISIBLE);
                    txtEmpty.setVisibility(View.GONE);

                } else
                    Toast.makeText(getContext(), "insert data!!", Toast.LENGTH_SHORT).show();

            });
            //boton cancelar operacion
            btnAddCancel.setOnClickListener(v14 -> dialog.cancel());

            dialog.setCancelable(false);
            dialog.show();

        });

        return view;
    }
}
