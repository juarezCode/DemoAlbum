package com.mediomelon.demoalbum.model.repository;

import android.content.Context;

import com.mediomelon.demoalbum.dao.AlbumDataBase;
import com.mediomelon.demoalbum.interfaces.IUser;
import com.mediomelon.demoalbum.model.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryDB implements IUser.IRepository {

    private IUser.IPresenter userPresenter;
    private AlbumDataBase albumDataBase;

    public UserRepositoryDB(IUser.IPresenter presenter, Context context) {
        this.userPresenter = presenter;
        albumDataBase = AlbumDataBase.getDataBase(context);
    }

    @Override
    public void getUsers() {
        //mostrar bd
        //List<User> users = LoginActivity.albumDataBase.userDao().getUsers();
        List<User> users = albumDataBase.userDao().getUsers();

//        for (User user : users) {
//            Log.e(TAG, "id: " + user.getId() + " " + user.getName() + " city: " + user.getAddress().getCity() + " company " + user.getCompany().getName());
//        }

        ArrayList<User> listUsers = new ArrayList<>(users);
        userPresenter.showUsers(listUsers);
    }
}
