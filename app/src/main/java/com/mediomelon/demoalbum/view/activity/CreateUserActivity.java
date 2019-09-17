package com.mediomelon.demoalbum.view.activity;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.mediomelon.demoalbum.R;
import com.mediomelon.demoalbum.dao.AlbumDataBase;
import com.mediomelon.demoalbum.model.entity.Album;
import com.mediomelon.demoalbum.model.entity.User;
import com.mediomelon.demoalbum.utils.Constants;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateUserActivity extends AppCompatActivity {

    private static final String TAG = "CreateUserActivity";
    @BindView(R.id.edtInsertName)
    TextInputEditText name;
    @BindView(R.id.edtInsertUsername)
    TextInputEditText username;
    @BindView(R.id.edtInsertPassword)
    TextInputEditText password;
    @BindView(R.id.edtInsertEmail)
    TextInputEditText email;
    @BindView(R.id.edtInsertCompany)
    TextInputEditText company;
    @BindView(R.id.edtInsertAddress)
    TextInputEditText address;
    @BindView(R.id.edtInsertWebsite)
    TextInputEditText website;
    @BindView(R.id.edtInsertPhone)
    TextInputEditText phone;
    @BindView(R.id.btnInsertSave)
    Button save;
    @BindView(R.id.btnInsertCancel)
    Button cancel;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.rbImg1)
    RadioButton rdImg1;
    @BindView(R.id.rbImg2)
    RadioButton rdImg2;
    @BindView(R.id.rbImg3)
    RadioButton rdImg3;
    @BindView(R.id.rbImg4)
    RadioButton rdImg4;
    private AlbumDataBase albumDataBase;
    int selectImage = Constants.GIRL_1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        ButterKnife.bind(this);
        albumDataBase = AlbumDataBase.getDataBase(this);

        save.setOnClickListener(v -> {

            Log.e(TAG, "imagen seleccionada" + selectImage);

            //obtener objeto con el id mayor
            User userMax = albumDataBase.userDao().getMaxId();

            if (!name.getText().toString().isEmpty() && !username.getText().toString().isEmpty()
                    && !password.getText().toString().isEmpty() && !email.getText().toString().isEmpty()
                    && !company.getText().toString().isEmpty() && !address.getText().toString().isEmpty()
                    && !website.getText().toString().isEmpty() && !phone.getText().toString().isEmpty()) {


                //crear nuevo objeto con sus propiedades
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd 'at' HH:mm:ss", Locale.getDefault());
                String currentDateandTime = sdf.format(new Date());

                User user = new User();
                user.setId(userMax.getId() + 1);
                user.setPhoto(selectImage);
                user.setStatus(Constants.STATUS_ACTIVE);
                user.setDate(currentDateandTime);
                user.setName(name.getText().toString());
                user.setUsername(username.getText().toString());
                user.setPassword(password.getText().toString());
                user.setEmail(email.getText().toString());
                user.setCompany(new User.Company(company.getText().toString()));
                user.setAddress(new User.Address(address.getText().toString(), "", "", ""));
                user.setWebsite(website.getText().toString());
                user.setPhone(phone.getText().toString());

                albumDataBase.userDao().addUser(user);
                Toast.makeText(this, "successful added", Toast.LENGTH_SHORT).show();


            } else
                Toast.makeText(this, "insert data", Toast.LENGTH_SHORT).show();
        });
        cancel.setOnClickListener(v -> {
            name.setText("");
            username.setText("");
        });



        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (rdImg1.isChecked())
                selectImage = Constants.GIRL_1;
            else if (rdImg2.isChecked())
                selectImage = Constants.GIRL_2;
            else if (rdImg3.isChecked())
                selectImage = Constants.MAN_1;
            else if (rdImg4.isChecked())
                selectImage = Constants.MAN_2;
        });


    }
}
