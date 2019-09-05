package com.mediomelon.demoalbum.model.repository;

import android.content.Context;
import android.util.Log;

import com.mediomelon.demoalbum.dao.AlbumDatabase;
import com.mediomelon.demoalbum.interfaces.IPhotos;
import com.mediomelon.demoalbum.model.entity.Photo;

import java.util.ArrayList;
import java.util.List;

public class PhotoRepositoryDB implements IPhotos.IRepository {

    private static final String TAG = "PhotoRepositoryDB";
    private AlbumDatabase albumDatabase;
    private IPhotos.IPresenter photoPresenter;
    private Context ctx;
    public PhotoRepositoryDB(IPhotos.IPresenter photoPresenter, Context ctx){
        this.photoPresenter = photoPresenter;
        this.ctx = ctx;
        albumDatabase = AlbumDatabase.getDatabase(this.ctx);
    }
    @Override
    public void getPhotos(int id) {
        List<Photo> photos = albumDatabase.photoDao().getPhotos(id);
        for (Photo photo: photos){
            Log.e(TAG,"Name Photo: " + photo.getTitle());
        }

        ArrayList<Photo> photoArrayList = new ArrayList<>(photos);
        photoPresenter.showPhotos(photoArrayList);
    }
}
