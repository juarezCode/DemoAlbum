package com.mediomelon.demoalbum.interfaces;

import com.mediomelon.demoalbum.model.entity.Photo;

import java.util.ArrayList;

public interface IPhotos {

    interface iModel {
        void getPhotos(int id);
    }

    interface iPresenter {
        void getPhotos(int id);

        void showPhotos(ArrayList<Photo> photos);

        void showErrorPhotos(String error);
    }

    interface iView {
        void getPhotos(int id);

        void showPhotos(ArrayList<Photo> photos);

        void showErrorPhotos(String error);
    }
}
