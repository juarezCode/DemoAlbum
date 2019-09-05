package com.mediomelon.demoalbum.interfaces;

import com.mediomelon.demoalbum.model.entity.Photo;

import java.util.ArrayList;

public interface IPhotos {

    interface IRepository{
        void getPhotos(int id);
    }

    interface IModel {
        void getPhotos(int id);
    }

    interface IPresenter {
        void getPhotos(int id);

        void showPhotos(ArrayList<Photo> photos);

        void showErrorPhotos(String error);
    }

    interface IView {
        void getPhotos(int id);

        void showPhotos(ArrayList<Photo> photos);

        void showErrorPhotos(String error);
    }
}
