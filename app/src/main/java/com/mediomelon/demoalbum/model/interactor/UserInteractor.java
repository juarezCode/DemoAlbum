package com.mediomelon.demoalbum.model.interactor;

import android.content.Context;
import android.util.Log;

import com.mediomelon.demoalbum.dao.AlbumDataBase;
import com.mediomelon.demoalbum.interfaces.IUser;
import com.mediomelon.demoalbum.model.entity.User;
import com.mediomelon.demoalbum.model.repository.UserRepositoryAPI;
import com.mediomelon.demoalbum.model.repository.UserRepositoryDB;

import java.util.List;

public class UserInteractor implements IUser.IModel {
    private IUser.IRepository userRepositoryAPI;
    private IUser.IRepository userRepositoryDB;
    private static final String TAG = "UserInteractor";
    private AlbumDataBase albumDataBase;

    public UserInteractor(IUser.IPresenter userPresenter, Context context) {
        this.userRepositoryAPI = new UserRepositoryAPI(userPresenter, context);
        this.userRepositoryDB = new UserRepositoryDB(userPresenter, context);
        albumDataBase = AlbumDataBase.getDataBase(context);
    }

    @Override
    public void getUsers() {
        //consulta, muestra una lista de photos
        //List<User> listUsers = LoginActivity.albumDataBase.userDao().getUsers();
        List<User> listUsers = albumDataBase.userDao().getUsers();

        if (!listUsers.isEmpty()) {
            Log.e(TAG, "traer datos (listUsers) de => DATABASE");
            userRepositoryDB.getUsers();
        } else {
            Log.e(TAG, "traer datos (listUsers) de => WEB API");
            userRepositoryAPI.getUsers();
        }
    }
}
