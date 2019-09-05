package com.mediomelon.demoalbum.model.repository;

import com.mediomelon.demoalbum.interfaces.IUser;
import com.mediomelon.demoalbum.model.entity.User;
import com.mediomelon.demoalbum.view.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryDB implements IUser.IRepository {

    private IUser.IPresenter userPresenter;

    public UserRepositoryDB(IUser.IPresenter presenter) {
        this.userPresenter = presenter;
    }

    @Override
    public void getUsers() {
        //mostrar bd
        List<User> users = MainActivity.dataBase.userDao().getUsers();

//        for (User user : users) {
//            Log.e(TAG, "id: " + user.getId() + " " + user.getName() + " city: " + user.getAddress().getCity() + " company " + user.getCompany().getName());
//        }

        ArrayList<User> listUsers = new ArrayList<>(users);
        userPresenter.showUsers(listUsers);
    }
}
