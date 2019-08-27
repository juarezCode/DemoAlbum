package com.mediomelon.demoalbum.view.activity;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mediomelon.demoalbum.R;
import com.mediomelon.demoalbum.interfaces.IAlbum;
import com.mediomelon.demoalbum.interfaces.IUser;
import com.mediomelon.demoalbum.model.entity.Album;
import com.mediomelon.demoalbum.model.entity.User;
import com.mediomelon.demoalbum.presenter.AlbumPresenter;
import com.mediomelon.demoalbum.presenter.UserPresenter;
import com.mediomelon.demoalbum.view.fragments.AlbumFragment;
import com.mediomelon.demoalbum.view.fragments.UserFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements IUser.IView, IAlbum.IView {

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    //@BindView(R.id.toolbar)
    //Toolbar toolbar;

    private static final String TAG = "MainActivity";
    private IUser.IPresenter userPresenter;
    private IAlbum.IPresenter albumPresenter;
    private Bundle args;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        userPresenter = new UserPresenter(this);
        albumPresenter = new AlbumPresenter(this);

        //BottomNavigationView
        bottomNavigationView.setVisibility(View.GONE);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        args = new Bundle();

        getUsers();
        getAlbums();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = menuItem -> {
        Fragment fragment;
        switch (menuItem.getItemId()) {
            case R.id.navigation_users:
                fragment = new UserFragment();
                fragment.setArguments(args);
                loadFragment(fragment);
                return true;

            case R.id.navigation_albums:
                fragment = new AlbumFragment();
                fragment.setArguments(args);
                loadFragment(fragment);
                return true;
        }
        return false;
    };

    @Override
    public void getUsers() {
        userPresenter.getUsers();
    }

    @Override
    public void showUsers(ArrayList<User> listUser) {
        //lista de usuarios
        bottomNavigationView.setVisibility(View.VISIBLE);
        args.putSerializable("listUser", listUser);

        Fragment fragment;
        fragment = new UserFragment();
        fragment.setArguments(args);
        loadFragment(fragment);
    }

    @Override
    public void showErrorUsers(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    //>>>>>>>>>>>>>>>>>>>Empieza Albumes>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Override
    public void getAlbums() {
        albumPresenter.getAlbums();
    }

    @Override
    public void showAlbums(ArrayList<Album> albums) {
        progressBar.setVisibility(View.GONE);
        //Fragment
        //fragment = new AlbumFragment();
        args.putSerializable("albums",albums);
        //fragment.setArguments(args);
        //loadFragment(fragment);

        for(Album album : albums){
            Log.e(TAG,"Nombre del album : " + album.getTitle());
        }
    }

    @Override
    public void showErrorAlbum(String error) {
        Log.e(TAG,"Error: " + error);
    }

    //>>>>>>>>>>>>>>>>>>>Termina Albumes>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout_bottom_navigation, fragment);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    /*Inicializar el fragment por defecto Userfragment,
    e incializar el fragment cuando volvamos a esta pantalla
    desde DetailsUserActivity por el BottomNavigationView
    valorfragment valor 0 para el fragment Userfragment
    valorfragment valor 2 para el fragment Albumfragment*/
    /*
    private void seleccionFragmentInicial() {
        //recibiendo datos de 2da pantalla
        int valor = getIntent().getIntExtra("valorframent", 0);
        Fragment fragment = new Fragment();
        //if elegir fragments a iniciar
        if(valor == 0){
            fragment = new UserFragment();
        }else if (valor == 2){
            fragment = new AlbumFragment();
        }
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout_bottom_navigation, fragment);
        transaction.commit();
    } */
}
