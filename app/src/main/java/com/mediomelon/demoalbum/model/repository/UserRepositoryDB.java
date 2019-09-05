package com.mediomelon.demoalbum.model.repository;

import android.content.Context;
import android.util.Log;

import com.mediomelon.demoalbum.dao.AlbumDatabase;
import com.mediomelon.demoalbum.interfaces.IUser;
import com.mediomelon.demoalbum.model.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryDB implements IUser.IRepository {

    private IUser.IPresenter userPresenter;
    private Context ctx;
    private AlbumDatabase albumDatabase;

    public UserRepositoryDB(IUser.IPresenter userPresenter, Context ctx){
        this.userPresenter = userPresenter;
        this.ctx = ctx;
        albumDatabase = AlbumDatabase.getDatabase(ctx);
    }

    @Override
    public void getUsers() {
        List<User> users = albumDatabase.userDao().getUsersDB();
        ArrayList<User> userArrayList = new ArrayList<>(users);
        for(User user : userArrayList){
            Log.e("::Gustavo::","Name: " + user.getName());
        }
        userPresenter.showUsers(userArrayList);
    }
}
