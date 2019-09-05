package com.mediomelon.demoalbum.model.interactor;

import android.util.Log;

import com.mediomelon.demoalbum.interfaces.IAlbum;
import com.mediomelon.demoalbum.model.entity.Album;
import com.mediomelon.demoalbum.model.repository.AlbumRepositoryAPI;
import com.mediomelon.demoalbum.model.repository.AlbumRepositoryDB;
import com.mediomelon.demoalbum.view.activity.MainActivity;

import java.util.List;

public class AlbumInteractor implements IAlbum.IModel {

    private final static String TAG = "AlbumInteractor";
    private IAlbum.IRepository albumRepositoryAPI;
    private IAlbum.IRepository albumRepositoryDB;

    public AlbumInteractor(IAlbum.IPresenter albumPresenter){
        albumRepositoryAPI = new AlbumRepositoryAPI(albumPresenter);
        albumRepositoryDB =  new AlbumRepositoryDB(albumPresenter);
    }

    @Override
    public void getAlbums() {
        //consulta, muestra una lista de photos
        List<Album> listAlbums = MainActivity.dataBase.albumDao().getAlbums();

        if (!listAlbums.isEmpty()) {
            Log.e(TAG, "traer datos (listAlbums) de => DATABASE");
            albumRepositoryDB.getAlbums();
        } else {
            Log.e(TAG, "traer datos (listAlbums) de => WEB API");
            albumRepositoryAPI.getAlbums();
        }

    }
}
