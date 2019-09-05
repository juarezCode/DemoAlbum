package com.mediomelon.demoalbum.model.interactor;

import android.util.Log;

import com.mediomelon.demoalbum.interfaces.IPhotos;
import com.mediomelon.demoalbum.model.entity.Photo;
import com.mediomelon.demoalbum.model.repository.PhotoRepositoryAPI;
import com.mediomelon.demoalbum.model.repository.PhotoRepositoryDB;
import com.mediomelon.demoalbum.view.activity.MainActivity;

import java.util.List;

public class PhotoInteractor implements IPhotos.IModel {

    private static final String TAG = "PhotoInteractor";
    private IPhotos.IRepository photoRepositoryAPI;
    private IPhotos.IRepository photoRepositoryDB;

    public PhotoInteractor(IPhotos.IPresenter photoPresenter) {
        photoRepositoryAPI = new PhotoRepositoryAPI(photoPresenter);
        photoRepositoryDB = new PhotoRepositoryDB(photoPresenter);
    }

    @Override
    public void getPhotos(int albumId) {

        //consulta, muestra una lista de photos
        List<Photo> listPhotos = MainActivity.dataBase.photoDao().getPhotosById(albumId);

        if (!listPhotos.isEmpty()) {
            Log.e(TAG, "traer datos (listPhotos) de => DATABASE");
            photoRepositoryDB.getPhotos(albumId);
        } else {
            Log.e(TAG, "traer datos (listPhotos) de => WEB API");
            photoRepositoryAPI.getPhotos(albumId);
        }

    }
}
