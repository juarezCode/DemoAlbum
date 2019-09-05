package com.mediomelon.demoalbum.model.repository;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import com.amitshekhar.DebugDB;
import com.mediomelon.demoalbum.R;
import com.mediomelon.demoalbum.api.ServiceClient;
import com.mediomelon.demoalbum.dao.AlbumDatabase;
import com.mediomelon.demoalbum.dao.UserDao;
import com.mediomelon.demoalbum.interfaces.IUser;
import com.mediomelon.demoalbum.model.entity.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepositoryAPI implements IUser.IRepository {

    private IUser.IPresenter userPresenter;
    private static final String TAG = "UserRepositoryAPI";
    private ArrayList<User> listUser;
    private List<User> usersDB;
    private AlbumDatabase albumDatabase;
    private Context ctx;

    public UserRepositoryAPI(IUser.IPresenter userPresenter,Context ctx){
        this.userPresenter = userPresenter;
        this.ctx = ctx;
        albumDatabase = AlbumDatabase.getDatabase(this.ctx);
    }

    @Override
    public void getUsers() {
        Call<List<User>> callUser = ServiceClient.createUserService().getUsers();
        callUser.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                if (response.isSuccessful()) {
                    listUser = new ArrayList<>();
                    for (User user : response.body()) {
                        int imageUser = selectImageUser();
                        user.setPhoto(imageUser);

                        listUser.add(user);
                        albumDatabase.userDao().addUser(user);
                        Log.e(TAG, "name: " + user.getName());
                        Log.e(TAG, "Address (city): " + user.getAddress().getCity());
                        Log.e(TAG, "Company (name): " + user.getCompany().getName());
                        Log.e(TAG, "Image (drawable): " + user.getPhoto());
                    }
                    DebugDB.getAddressLog();
                    userPresenter.showUsers(listUser);

                } else if (response.code() == 401)
                    userPresenter.showErrorUsers("Bad authentication");
                else if (response.code() == 404)
                    userPresenter.showErrorUsers("Users Not Found");
                else
                    Log.e(TAG, "Unexpected error");
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                userPresenter.showErrorUsers(t.toString());
            }
        });
    }

    private int selectImageUser() {
        int[] images = {R.drawable.girl_1, R.drawable.girl_2, R.drawable.man_1, R.drawable.man_2};
        int numRandom = (int) Math.round(Math.random() * 3);
        return images[numRandom];
    }
}
