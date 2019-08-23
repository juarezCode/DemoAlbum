package com.mediomelon.demoalbum.presenter;

import com.mediomelon.demoalbum.interfaces.IPhotos;
import com.mediomelon.demoalbum.model.PhotoInteractor;
import com.mediomelon.demoalbum.model.entity.Photo;

import java.util.ArrayList;
import java.util.List;

public class PhotoPresenter implements IPhotos.iPresenter {

    private IPhotos.iView photoView;
    private IPhotos.iModel photoInteractor;

    public PhotoPresenter(IPhotos.iView photoView) {
        this.photoView = photoView;
        photoInteractor=new PhotoInteractor(this);
    }

    @Override
    public void getPhotos() {
        photoInteractor.getPhotos();
    }

    @Override
    public void showPhotos(ArrayList<Photo> photos) {
        photoView.showPhotos(photos);
    }

    @Override
    public void showError(String error) {
        photoView.showError(error);
    }
}
