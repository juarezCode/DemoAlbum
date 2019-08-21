package com.mediomelon.demoalbum.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.mediomelon.demoalbum.R;
import com.mediomelon.demoalbum.interfaces.IUser;
import com.mediomelon.demoalbum.model.entity.User;
import com.mediomelon.demoalbum.presenter.UserPresenter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IUser.IView {

    private IUser.IPresenter userPresenter;
    private final static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userPresenter = new UserPresenter(this);
        getUsers();
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
