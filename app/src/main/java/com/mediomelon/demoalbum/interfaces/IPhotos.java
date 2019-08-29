package com.mediomelon.demoalbum.interfaces;

import com.mediomelon.demoalbum.model.entity.Photo;

import java.util.ArrayList;

public interface IPhotos {

    interface IRepository{
        void getPhotos(int albumId);
    }

    interface IModel {
        void getPhotos(int albumId);
    }

    interface IPresenter {
        void getPhotos(int albumId);

        void showPhotos(ArrayList<Photo> photos);

        void showErrorPhotos(String error);
    }

    interface IView {
        void getPhotos(int albumId);

        void showPhotos(ArrayList<Photo> photos);

        void showErrorPhotos(String error);
    }
}
