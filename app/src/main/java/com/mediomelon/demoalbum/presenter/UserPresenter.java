package com.mediomelon.demoalbum.presenter;

import android.content.Context;

import com.mediomelon.demoalbum.interfaces.IUser;
import com.mediomelon.demoalbum.model.interactor.UserInteractor;
import com.mediomelon.demoalbum.model.entity.User;

import java.util.ArrayList;

public class UserPresenter implements IUser.IPresenter {
    private IUser.IView userView;
    private IUser.IModel userInteractor;
    private Context ctx;

    public UserPresenter(IUser.IView userView, Context ctx) {
        this.userView = userView;
        this.ctx = ctx;
        userInteractor = new UserInteractor(this,this.ctx);
    }

    @Override
    public void getUsers() {
        userInteractor.getUsers();
    }

    @Override
    public void showUsers(ArrayList<User> listUser) {
        userView.showUsers(listUser);
    }

    @Override
    public void showErrorUsers(String error) {
        userView.showErrorUsers(error);
    }
}
