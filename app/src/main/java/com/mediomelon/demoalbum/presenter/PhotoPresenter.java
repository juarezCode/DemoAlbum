package com.mediomelon.demoalbum.presenter;

import android.content.Context;

import com.mediomelon.demoalbum.interfaces.IPhotos;
import com.mediomelon.demoalbum.model.interactor.PhotoInteractor;
import com.mediomelon.demoalbum.model.entity.Photo;

import java.util.ArrayList;

public class PhotoPresenter implements IPhotos.IPresenter {

    private IPhotos.IView photoView;
    private IPhotos.IModel photoInteractor;
    private Context ctx;
    public PhotoPresenter(IPhotos.IView photoView,Context ctx) {
        this.photoView = photoView;
        this.ctx = ctx;
        photoInteractor = new PhotoInteractor(this,this.ctx);
    }

    @Override
    public void getPhotos(int id) {
        photoInteractor.getPhotos(id);
    }

    @Override
    public void showPhotos(ArrayList<Photo> photos) {
        photoView.showPhotos(photos);
    }

    @Override
    public void showErrorPhotos(String error) {
        photoView.showErrorPhotos(error);
    }
}
