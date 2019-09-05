package com.mediomelon.demoalbum.model.interactor;

import android.content.Context;
import android.util.Log;

import com.mediomelon.demoalbum.api.ServiceClient;
import com.mediomelon.demoalbum.dao.AlbumDatabase;
import com.mediomelon.demoalbum.interfaces.IPhotos;
import com.mediomelon.demoalbum.model.entity.Photo;
import com.mediomelon.demoalbum.model.entity.User;
import com.mediomelon.demoalbum.model.repository.PhotoRepositoryAPI;
import com.mediomelon.demoalbum.model.repository.PhotoRepositoryDB;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoInteractor implements IPhotos.IModel {

    private IPhotos.IPresenter photoPresenter;
    private final static String TAG = "PhotoInteractor";
    private Context ctx;
    private AlbumDatabase albumDatabase;
    private List<Photo> photos;
    private IPhotos.IRepository photoRepositoryAPI;
    private IPhotos.IRepository photoRepositoryDB;

    public PhotoInteractor(IPhotos.IPresenter photoPresenter, Context ctx) {
        this.photoPresenter = photoPresenter;
        this.ctx = ctx;
        this.photoRepositoryAPI = new PhotoRepositoryAPI(this.photoPresenter,this.ctx);
        this.photoRepositoryDB = new PhotoRepositoryDB(this.photoPresenter,this.ctx);
        albumDatabase = AlbumDatabase.getDatabase(this.ctx);

    }

    @Override
    public void getPhotos(int id) {
        if(verifyDBExists(id)){
            photoRepositoryDB.getPhotos(id);
        }else{
            photoRepositoryAPI.getPhotos(id);
        }
    }

    private boolean verifyDBExists(int id) {
        photos = albumDatabase.photoDao().getPhotos(id);
        for(Photo photo : photos){
            Log.e(TAG,"Name: " + photo.getTitle());
        }
        if(photos.size()>0)
            return true;
        else
            return false;
    }
}
