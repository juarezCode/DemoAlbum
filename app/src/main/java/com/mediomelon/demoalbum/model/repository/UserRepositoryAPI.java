package com.mediomelon.demoalbum.model.repository;

import android.content.Context;
import android.util.Log;

import com.mediomelon.demoalbum.api.ServiceClient;
import com.mediomelon.demoalbum.dao.AlbumDataBase;
import com.mediomelon.demoalbum.interfaces.IUser;
import com.mediomelon.demoalbum.model.entity.User;
import com.mediomelon.demoalbum.utils.Constants;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepositoryAPI implements IUser.IRepository {

    private IUser.IPresenter userPresenter;
    private static final String TAG = "UserRepositoryAPI";
    private ArrayList<User> listUser;
    private SimpleDateFormat sdf;
    private AlbumDataBase albumDataBase;

    public UserRepositoryAPI(IUser.IPresenter presenter, Context context) {
        this.userPresenter = presenter;
        albumDataBase = AlbumDataBase.getDataBase(context);
    }

    @Override
    public void getUsers() {

        Call<List<User>> callUser = ServiceClient.createUserService().getUsers();
        callUser.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                if (response.isSuccessful()) {
                    listUser = new ArrayList<>();
                    sdf = new SimpleDateFormat("yyyy/MM/dd 'at' HH:mm:ss", Locale.getDefault());
                    for (User user : response.body()) {
                        int imageUser = selectImageUser();
                        user.setPhoto(imageUser);
                        user.setStatus(Constants.STATUS_ACTIVE);
                        user.setPassword(Constants.PASS_DEFAULT);
                        String currentDateandTime = sdf.format(new Date());
                        user.setDate(currentDateandTime);


//                        Log.e(TAG, "name: " + user.getName());
//                        Log.e(TAG, "Address (city): " + user.getAddress().getCity());
//                        Log.e(TAG, "Company (name): " + user.getCompany().getName());
//                        Log.e(TAG, "Image (drawable): " + user.getPhoto());


                        User userId = albumDataBase.userDao().getUserById(user.getId());

                        if (userId == null) {
                            //insertar en bd;
                            albumDataBase.userDao().addUser(user);
                            Log.e(TAG, " inserted: id: " + user.getId() + " " + user.getAddress().getCity() + " " + user.getCompany().getName() + " " + user.getName());
                            listUser.add(user);
                        }
                    }
                    //mostrar bd
                    showUsers();

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
        int[] images = {Constants.GIRL_1, Constants.GIRL_2, Constants.MAN_1, Constants.MAN_2};
        int numRandom = (int) Math.round(Math.random() * 3);
        return images[numRandom];
    }

    private void showUsers() {
        //mostrar bd
        List<User> users = albumDataBase.userDao().getUsers();

        for (User user : users) {

            Log.e(TAG, "id: " + user.getId() + " " + user.getName() + " city: " + user.getAddress().getCity() + " company " + user.getCompany().getName());
        }
    }
}
