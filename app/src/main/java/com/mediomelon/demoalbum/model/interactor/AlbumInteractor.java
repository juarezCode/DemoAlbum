package com.mediomelon.demoalbum.model.interactor;

import android.content.Context;
import android.util.Log;

import com.mediomelon.demoalbum.dao.AlbumDataBase;
import com.mediomelon.demoalbum.interfaces.IAlbum;
import com.mediomelon.demoalbum.model.entity.Album;
import com.mediomelon.demoalbum.model.repository.AlbumRepositoryAPI;
import com.mediomelon.demoalbum.model.repository.AlbumRepositoryDB;
import com.mediomelon.demoalbum.view.activity.LoginActivity;

import java.util.List;

public class AlbumInteractor implements IAlbum.IModel {

    private final static String TAG = "AlbumInteractor";
    private IAlbum.IRepository albumRepositoryAPI;
    private IAlbum.IRepository albumRepositoryDB;
    private AlbumDataBase albumDataBase;

    public AlbumInteractor(IAlbum.IPresenter albumPresenter, Context context) {
        albumRepositoryAPI = new AlbumRepositoryAPI(albumPresenter, context);
        albumRepositoryDB = new AlbumRepositoryDB(albumPresenter, context);
        this.albumDataBase = AlbumDataBase.getDataBase(context);
    }

    @Override
    public void getAlbums() {
        //consulta, muestra una lista de photos
        //List<Album> listAlbums = LoginActivity.albumDataBase.albumDao().getAlbums();
        List<Album> listAlbums = albumDataBase.albumDao().getAlbums();

        if (!listAlbums.isEmpty()) {
            Log.e(TAG, "traer datos (listAlbums) de => DATABASE");
            albumRepositoryDB.getAlbums();
        } else {
            Log.e(TAG, "traer datos (listAlbums) de => WEB API");
            albumRepositoryAPI.getAlbums();
        }

    }
}
