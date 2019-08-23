package com.mediomelon.demoalbum.interfaces;

import com.mediomelon.demoalbum.model.entity.Photo;

import java.util.ArrayList;

public interface IPhotos {

    interface iModel{
        void getPhotos();
    }
    interface iPresenter{
        void getPhotos();
        void showPhotos(ArrayList<Photo> photos);
        void showError(String error);
    }
    interface iView{
        void getPhotos();
        void showPhotos(ArrayList<Photo> photos);
        void showError(String error);
    }
}
