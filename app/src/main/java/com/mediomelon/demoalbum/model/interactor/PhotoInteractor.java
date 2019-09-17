package com.mediomelon.demoalbum.model.interactor;

import android.content.Context;
import android.util.Log;

import com.mediomelon.demoalbum.dao.AlbumDataBase;
import com.mediomelon.demoalbum.interfaces.IPhotos;
import com.mediomelon.demoalbum.model.entity.Photo;
import com.mediomelon.demoalbum.model.repository.PhotoRepositoryAPI;
import com.mediomelon.demoalbum.model.repository.PhotoRepositoryDB;
import com.mediomelon.demoalbum.view.activity.LoginActivity;

import java.util.List;

public class PhotoInteractor implements IPhotos.IModel {

    private static final String TAG = "PhotoInteractor";
    private IPhotos.IRepository photoRepositoryAPI;
    private IPhotos.IRepository photoRepositoryDB;
    private AlbumDataBase albumDataBase;

    public PhotoInteractor(IPhotos.IPresenter photoPresenter, Context context) {
        photoRepositoryAPI = new PhotoRepositoryAPI(photoPresenter, context);
        photoRepositoryDB = new PhotoRepositoryDB(photoPresenter, context);
        albumDataBase = AlbumDataBase.getDataBase(context);
    }

    @Override
    public void getPhotos(int albumId) {

        //consulta, muestra una lista de photos
        //List<Photo> listPhotos = LoginActivity.albumDataBase.photoDao().getPhotosById(albumId);
        List<Photo> listPhotos = albumDataBase.photoDao().getPhotosById(albumId);

        if (!listPhotos.isEmpty()) {
            Log.e(TAG, "traer datos (listPhotos) de => DATABASE");
            photoRepositoryDB.getPhotos(albumId);
        } else {
            Log.e(TAG, "traer datos (listPhotos) de => WEB API");
            photoRepositoryAPI.getPhotos(albumId);
        }

    }
}
