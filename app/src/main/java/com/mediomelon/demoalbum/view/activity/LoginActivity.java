package com.mediomelon.demoalbum.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.mediomelon.demoalbum.R;
import com.mediomelon.demoalbum.dao.AlbumDataBase;
import com.mediomelon.demoalbum.model.entity.User;
import com.mediomelon.demoalbum.utils.Constants;
import com.mediomelon.demoalbum.utils.InternetConnection;
import com.mediomelon.demoalbum.utils.RootCheck;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.loginUsername)
    TextInputLayout layoutUsername;
    @BindView(R.id.edtInsertName)
    TextInputEditText txtUsername;
    @BindView(R.id.loginPass)
    TextInputLayout layoutPassword;
    @BindView(R.id.edtLoginPass)
    TextInputEditText txtPassword;
    @BindView(R.id.txtCreateAccount)
    TextView txtNewAccount;
    @BindView(R.id.txtUpdateAccount)
    TextView updatePassword;
    private AlbumDataBase albumDataBase;
    private User user;
    SharedPreferences session;
    Intent intent;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);//avoid screenshots
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        albumDataBase = AlbumDataBase.getDataBase(getApplicationContext());
        session = getSharedPreferences("session", MODE_PRIVATE);
        intent = new Intent(this, MainActivity.class);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "lo que sea");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        //comprobar si el dispositivo esta rooteado
        if(RootCheck.isDeviceRooted())
            AlertRootDevice();
        else
            Toast.makeText(getApplicationContext(), "SECURE device ", Toast.LENGTH_SHORT).show();

        //comprobar si existe conexion a internet
        new InternetConnection(internet -> {
            if(!internet)
                Toast.makeText(getApplicationContext(), "NO Internet Connected", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(), "Internet Connected", Toast.LENGTH_SHORT).show();
        });

        //comprobar si existe sesion activa
        if (session.contains("username") && session.contains("password"))
            startActivity(intent);

        btnLogin.setOnClickListener(v -> {
            //validacion campos vacios
            if (!txtUsername.getText().toString().isEmpty() && !txtPassword.getText().toString().isEmpty()) {

                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();

                //buscar al usuario por su username
                User userName = albumDataBase.userDao().getUserByUsername(username);

                //validacion si existe el usuario
                if (userName != null && !userName.getStatus().equals(Constants.STATUS_DELETED)) {
                    //validacion si coincide password
                    if (userName.getPassword().equals(password)) {
                        //guardar en preferencias
                        SharedPreferences.Editor sessionEditor = session.edit();
                        sessionEditor.putString("username", username);
                        sessionEditor.putString("password", password);
                        sessionEditor.commit();
                        Toast.makeText(this, "Success login", Toast.LENGTH_SHORT).show();
                        startActivity(intent);

                    } else
                        Toast.makeText(this, "password incorrect", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(this, "username not found", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(this, "enter data", Toast.LENGTH_SHORT).show();
        });

        txtNewAccount.setOnClickListener(v -> {
            Intent intent = new Intent(this, CreateUserActivity.class);
            startActivity(intent);
        });

        updatePassword.setOnClickListener(v -> {
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
            View view = getLayoutInflater().inflate(R.layout.dialog_update_password, null);

            TextInputEditText username = view.findViewById(R.id.edtUpdateName);
            TextInputEditText pass = view.findViewById(R.id.edtUpdatePassword);
            TextInputLayout layoutPass = view.findViewById(R.id.layoutPass);
            TextInputEditText passConfirm = view.findViewById(R.id.edtUpdatePassConfirm);
            TextInputLayout layoutPassConfirm = view.findViewById(R.id.layoutPassConfirm);
            Button searchAccount = view.findViewById(R.id.btnSearchAccount);
            Button btnUpdatepassword = view.findViewById(R.id.btnUpdatePassword);

            mBuilder.setView(view);
            AlertDialog dialog = mBuilder.create();
            layoutPass.setVisibility(View.GONE);
            layoutPassConfirm.setVisibility(View.GONE);
            btnUpdatepassword.setVisibility(View.GONE);

            searchAccount.setOnClickListener(v1 -> {

                if (!username.getText().toString().isEmpty()) {
                    user = albumDataBase.userDao().getUserByUsername(username.getText().toString());

                    if (user != null && !user.getStatus().equals(Constants.STATUS_DELETED)) {

                        layoutPass.setVisibility(View.VISIBLE);
                        layoutPassConfirm.setVisibility(View.VISIBLE);
                        btnUpdatepassword.setVisibility(View.VISIBLE);

                    } else {
                        layoutPass.setVisibility(View.GONE);
                        layoutPassConfirm.setVisibility(View.GONE);
                        btnUpdatepassword.setVisibility(View.GONE);
                        Toast.makeText(this, "username doesn´t exist", Toast.LENGTH_SHORT).show();
                    }
                } else
                    Toast.makeText(this, "enter username", Toast.LENGTH_SHORT).show();

            });
            btnUpdatepassword.setOnClickListener(v12 -> {

                if (!pass.getText().toString().isEmpty() && !passConfirm.getText().toString().isEmpty()) {

                    if (pass.getText().toString().equals(passConfirm.getText().toString())) {

                        user.setPassword(pass.getText().toString());
                        albumDataBase.userDao().updateUser(user);
                        Toast.makeText(this, "password update", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    } else
                        Toast.makeText(this, "password doesn´t match", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(this, "enter password", Toast.LENGTH_SHORT).show();
            });

            dialog.show();
        });

    }
    public void AlertRootDevice() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Alert Root device");
        builder.setIcon(R.drawable.ic_img_error);
        builder.setMessage("por politicas de seguridad la aplicacion no puede ser ejecutada en dispositivos rooteados")
                .setPositiveButton("OK", (dialog, id) -> {
                    dialog.dismiss();
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                });
        // Create the AlertDialog object and return it
        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();
    }
}
