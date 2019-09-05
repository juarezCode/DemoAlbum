package com.mediomelon.demoalbum.model.interactor;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import com.mediomelon.demoalbum.R;
import com.mediomelon.demoalbum.api.ServiceClient;
import com.mediomelon.demoalbum.dao.AlbumDatabase;
import com.mediomelon.demoalbum.interfaces.IUser;
import com.mediomelon.demoalbum.model.entity.User;
import com.mediomelon.demoalbum.model.repository.UserRepositoryAPI;
import com.mediomelon.demoalbum.model.repository.UserRepositoryDB;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserInteractor implements IUser.IModel {
    private static final String TAG = "UserInteractor";
    private IUser.IPresenter userPresenter;
    private IUser.IRepository userRepositoryAPI;
    private IUser.IRepository userRepositoryDB;
    private Context ctx;
    private AlbumDatabase albumDatabase;
    private List<User> users;

    public UserInteractor(IUser.IPresenter userPresenter,Context ctx) {
        this.userPresenter = userPresenter;
        this.ctx = ctx;
        userRepositoryAPI = new UserRepositoryAPI(this.userPresenter,this.ctx);
        userRepositoryDB = new UserRepositoryDB(this.userPresenter,this.ctx);
        albumDatabase = AlbumDatabase.getDatabase(this.ctx);

    }

    @Override
    public void getUsers() {
        if(verifyDBExists()){
            Log.e(TAG,"Desde base de datos");
            userRepositoryDB.getUsers();
        }else{
            Log.e(TAG,"Desde base api");
            userRepositoryAPI.getUsers();
        }

    }

    private boolean verifyDBExists() {
        users = albumDatabase.userDao().getUsersDB();
        for(User user : users){
            Log.e(TAG,"Name: " + user.getName());
        }
        if(users.size()>0)
            return true;
        else
            return false;
    }


}
