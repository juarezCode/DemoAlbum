package com.mediomelon.demoalbum.model.interactor;

import android.util.Log;

import com.mediomelon.demoalbum.interfaces.IUser;
import com.mediomelon.demoalbum.model.entity.User;
import com.mediomelon.demoalbum.model.repository.UserRepositoryAPI;
import com.mediomelon.demoalbum.model.repository.UserRepositoryDB;
import com.mediomelon.demoalbum.view.activity.MainActivity;

import java.util.List;

public class UserInteractor implements IUser.IModel {
    private IUser.IRepository userRepositoryAPI;
    private IUser.IRepository userRepositoryDB;
    private static final String TAG = "UserInteractor";

    public UserInteractor(IUser.IPresenter userPresenter) {
        this.userRepositoryAPI = new UserRepositoryAPI(userPresenter);
        this.userRepositoryDB = new UserRepositoryDB(userPresenter);
    }

    @Override
    public void getUsers() {
        //consulta, muestra una lista de photos
        List<User> listUsers = MainActivity.dataBase.userDao().getUsers();

        if (!listUsers.isEmpty()) {
            Log.e(TAG, "traer datos (listUsers) de => DATABASE");
            userRepositoryDB.getUsers();
        } else {
            Log.e(TAG, "traer datos (listUsers) de => WEB API");
            userRepositoryAPI.getUsers();
        }
    }
}
