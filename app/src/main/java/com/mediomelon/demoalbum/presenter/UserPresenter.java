package com.mediomelon.demoalbum.presenter;

import com.mediomelon.demoalbum.interfaces.IUser;
import com.mediomelon.demoalbum.model.UserInteractor;
import com.mediomelon.demoalbum.model.entity.User;

import java.util.List;

public class UserPresenter implements IUser.IPresenter {
    private IUser.IView userView;
    private IUser.IModel userInteractor;
    private final static String TAG = "UserPresenter";

    public UserPresenter(IUser.IView userView) {
        this.userView = userView;
        userInteractor = new UserInteractor(this);
    }

    @Override
    public void getUsers() {
        userInteractor.getUsers();
    }

    @Override
    public void showUsers(List<User> listUser) {
        userView.showUsers(listUser);
    }

    @Override
    public void showErrorUsers(String error) {
        userView.showErrorUsers(error);
    }
}
