package com.mediomelon.demoalbum.view;

import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.mediomelon.demoalbum.R;
import com.mediomelon.demoalbum.view.fragments.AlbumFragment;
import com.mediomelon.demoalbum.view.fragments.UserFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailUserActivity extends AppCompatActivity {
    @BindView(R.id.name) TextView name;
    @BindView(R.id.nameUser) TextView userName;
    @BindView(R.id.website) TextView website;
    @BindView(R.id.address) TextView address;
    @BindView(R.id.company) TextView company;
    @BindView(R.id.email) TextView email;
    @BindView(R.id.phone) TextView phone;
    @BindView(R.id.imageViewUsers) ImageView imageViewusers;
    @BindView(R.id.btn_back) ImageButton back;

    @BindView(R.id.bottom_navigation) BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);

        ButterKnife.bind(this);

        back.setVisibility(View.VISIBLE);

        Bundle bundle = getIntent().getExtras();

        String getName = bundle.getString("name");
        String getUsername = bundle.getString("username");
        String getWebsite = bundle.getString("website");
        String getAddress = bundle.getString("address");
        String getCompany = bundle.getString("company");
        String getEmail = bundle.getString("email");
        String getPhone = bundle.getString("phone");
        String image = bundle.getString("photo");

        name.setText(getName);
        userName.setText(getUsername);
        website.setText(getWebsite);
        address.setText(getAddress);
        company.setText(getCompany);
        email.setText(getEmail);
        phone.setText(getPhone);

        imageViewusers.setImageResource(Integer.parseInt(image));

        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

       back.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
              /*FLAG_ACTIVITY_SINGLE_TOP: si la actividad que se lanza con el intent
                       ya esta en la pila de actividades, en lugar de lanzar una nueva instancia
                       de dicha actividad, el resto de actividades en la pila seran cerradas y se resolvera
                       el intent por la actividad a la que se llamo;*/
               startActivity(new Intent(getBaseContext(), MainActivity.class)
                       .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
               finish();
           }
       });

    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = menuItem -> {

        switch (menuItem.getItemId()) {
            case R.id.navigation_users:
                //ir a la pantalla anterior (MainActivity) con el fragment por default UserFragment
                Intent intentUsers= new Intent(getBaseContext(), MainActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                //mandar identificador valorfragment y el valor 0
                intentUsers.putExtra("valorframent",0);
                startActivity(intentUsers);
                finish();
                return true;

            case R.id.navigation_albums:
                //ir a la pantalla anterior (MainActivity) con el fragment por default AlbumFragment
                Intent intentAlbum=new Intent(DetailUserActivity.this, MainActivity.class);
                //mandar identificador valorfragment y el valor 2 para inicialiar la pantalla anterior con
                //el fragment Albumfragment en lugar de Userfragment
                intentAlbum.putExtra("valorframent",2);
                startActivity(intentAlbum);
                finish();
                return true;
        }
        return false;
    };
}
