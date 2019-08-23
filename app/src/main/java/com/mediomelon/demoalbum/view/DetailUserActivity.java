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

        String getname = bundle.getString("name");
        String getusername = bundle.getString("username");
        String getwebsite = bundle.getString("website");
        String getaddress = bundle.getString("address");
        String getcompany = bundle.getString("company");
        String getemail = bundle.getString("email");
        String getphone = bundle.getString("phone");
        String image = bundle.getString("photo");

        name.setText(getname);
        userName.setText(getusername);
        website.setText(getwebsite);
        address.setText(getaddress);
        company.setText(getcompany);
        email.setText(getemail);
        phone.setText(getphone);

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
        Fragment fragment;
        switch (menuItem.getItemId()) {
            case R.id.navigation_users:
                return true;

            case R.id.navigation_albums:
                Intent intent=new Intent(DetailUserActivity.this, MainActivity.class);
                intent.putExtra("valorframent",1);
                startActivity(intent);
                finish();
                return true;
        }
        return false;
    };
    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout_bottom_navigation, fragment);
        transaction.commit();
    }
}
