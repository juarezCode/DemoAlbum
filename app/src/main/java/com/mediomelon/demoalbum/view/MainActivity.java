package com.mediomelon.demoalbum.view;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.mediomelon.demoalbum.R;
import com.mediomelon.demoalbum.view.fragments.AlbumFragment;
import com.mediomelon.demoalbum.view.fragments.UserFragment;
import com.mediomelon.demoalbum.interfaces.IUser;
import com.mediomelon.demoalbum.model.entity.User;
import com.mediomelon.demoalbum.presenter.UserPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
import java.util.List;

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;

public class MainActivity extends AppCompatActivity implements IUser.IView {

    private IUser.IPresenter userPresenter;
    private final static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(new UserFragment());
        userPresenter = new UserPresenter(this);
        getUsers();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = menuItem -> {
        Fragment fragment;
        switch (menuItem.getItemId()) {
            case R.id.navigation_users:
                fragment = new UserFragment();
                loadFragment(fragment);
                return true;

            case R.id.navigation_albums:
                fragment = new AlbumFragment();
                loadFragment(fragment);
                return true;
        }
        return false;
    };

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout_bottom_navigation, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void getUsers() {
        userPresenter.getUsers();
    }

    @Override
    public void showUsers(List<User> listUser) {
        //lista de usuarios
        Log.e(TAG, "users list: " + listUser.toString());
    }

    @Override
    public void showErrorUsers(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}
