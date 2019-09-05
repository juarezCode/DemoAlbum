package com.mediomelon.demoalbum.presenter;

import com.mediomelon.demoalbum.interfaces.IUser;
import com.mediomelon.demoalbum.model.interactor.UserInteractor;
import com.mediomelon.demoalbum.model.entity.User;

import java.util.ArrayList;

public class UserPresenter implements IUser.IPresenter {
    private IUser.IView userView;
    private IUser.IModel userInteractor;

    public UserPresenter(IUser.IView userView) {
        this.userView = userView;
        userInteractor = new UserInteractor(this);
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
