package com.mediomelon.demoalbum.interfaces;

import com.mediomelon.demoalbum.model.entity.User;

import java.util.ArrayList;

public interface IUser {
    interface IModel {
        void getUsers();

    }

    interface IPresenter {
        void getUsers();

        void showUsers(ArrayList<User> listUser);

        void showErrorUsers(String error);

    }

    interface IView {

        void getUsers();

        void showUsers(ArrayList<User> listUser);

        void showErrorUsers(String error);
    }
}
