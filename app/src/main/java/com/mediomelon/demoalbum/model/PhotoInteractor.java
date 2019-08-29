package com.mediomelon.demoalbum.model;

import android.util.Log;

import com.mediomelon.demoalbum.api.ServiceClient;
import com.mediomelon.demoalbum.interfaces.IPhotos;
import com.mediomelon.demoalbum.model.entity.Photo;
import com.mediomelon.demoalbum.repository.PhotoRepositoryAPI;
import com.mediomelon.demoalbum.repository.PhotoRepositoryDB;
import com.mediomelon.demoalbum.view.activity.MainActivity;
import com.mediomelon.demoalbum.view.activity.PhotosActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        if (listPhotos.size() >= 1) {
            Log.e(TAG, "traer datos (listPhotos) de => DATABASE");
            photoRepositoryDB.getPhotos(albumId);
        } else {
            Log.e(TAG, "traer datos (listPhotos) de => WEB API");
            photoRepositoryAPI.getPhotos(albumId);
        }

    }
}
