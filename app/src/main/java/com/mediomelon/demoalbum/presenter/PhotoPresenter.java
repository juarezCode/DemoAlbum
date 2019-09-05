package com.mediomelon.demoalbum.presenter;

import com.mediomelon.demoalbum.interfaces.IPhotos;
import com.mediomelon.demoalbum.model.interactor.PhotoInteractor;
import com.mediomelon.demoalbum.model.entity.Photo;

import java.util.ArrayList;

public class PhotoPresenter implements IPhotos.IPresenter {

    private IPhotos.IView photoView;
    private IPhotos.IModel photoInteractor;

    public PhotoPresenter(IPhotos.IView photoView) {
        this.photoView = photoView;
        photoInteractor = new PhotoInteractor(this);
    }

    @Override
    public void getPhotos(int albumId) {
        photoInteractor.getPhotos(albumId);
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
