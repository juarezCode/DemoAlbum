package com.mediomelon.demoalbum.model.repository;

import android.content.Context;
import android.util.Log;

import com.mediomelon.demoalbum.dao.AlbumDataBase;
import com.mediomelon.demoalbum.interfaces.IPhotos;
import com.mediomelon.demoalbum.model.entity.Photo;
import com.mediomelon.demoalbum.view.activity.LoginActivity;

import java.util.ArrayList;
import java.util.List;

public class PhotoRepositoryDB implements IPhotos.IRepository {
    private static final String TAG = "PhotoRepositoryDB";
    private IPhotos.IPresenter photoPresenter;
    private AlbumDataBase albumDataBase;

    public PhotoRepositoryDB(IPhotos.IPresenter presenter, Context context){
        this.photoPresenter = presenter;
        albumDataBase = AlbumDataBase.getDataBase(context);
    }

    @Override
    public void getPhotos(int albumId) {
        //mostrar bd
        //List<Photo> photos = LoginActivity.albumDataBase.photoDao().getPhotosById(albumId);
        List<Photo> photos = albumDataBase.photoDao().getPhotosById(albumId);

        for (Photo photo : photos) {
            int idP = photo.getId();
            int albumID = photo.getAlbumId();
            String url = photo.getUrl();
            String status = photo.getStatus();
            String date = photo.getDate();

            Log.e(TAG, "album: " + albumID + " id: " + idP + " status: " + status + " date: " + date + " url: " + url);
        }
        ArrayList<Photo> listPhotos = new ArrayList<>(photos);
        photoPresenter.showPhotos(listPhotos);
    }
}