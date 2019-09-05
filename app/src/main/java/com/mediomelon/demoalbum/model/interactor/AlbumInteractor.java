package com.mediomelon.demoalbum.model.interactor;

import android.content.Context;
import android.util.Log;

import com.mediomelon.demoalbum.dao.AlbumDatabase;
import com.mediomelon.demoalbum.interfaces.IAlbum;
import com.mediomelon.demoalbum.model.entity.Album;
import com.mediomelon.demoalbum.model.repository.AlbumRepositoryAPI;
import com.mediomelon.demoalbum.model.repository.AlbumRepositoryDB;

import java.util.ArrayList;
import java.util.List;


public class AlbumInteractor implements IAlbum.IModel {

    private final static String TAG = "AlbumInteractor";
    private IAlbum.IRepository albumRepositoryAPI;
    private IAlbum.IRepository albumRepositoryDB;
    private IAlbum.IPresenter albumPresenter;
    private List<Album> albums;
    private Context ctx;
    private AlbumDatabase albumDatabase;

    public AlbumInteractor(IAlbum.IPresenter albumPresenter,Context ctx){
        this.albumPresenter = albumPresenter;
        this.ctx = ctx;
        this.albumRepositoryAPI = new AlbumRepositoryAPI(this.albumPresenter,this.ctx);
        this.albumRepositoryDB = new AlbumRepositoryDB(this.albumPresenter,this.ctx);
        this.albumDatabase = AlbumDatabase.getDatabase(this.ctx);
    }

    @Override
    public void getAlbums() {
        if(verifyDBExists()){
            Log.e(TAG,"Desde base de datos");
            albumRepositoryDB.getAlbums();
        }else{
            Log.e(TAG,"Desde base api");
            albumRepositoryAPI.getAlbums();
        }
    }

    private boolean verifyDBExists() {
        albums = albumDatabase.albumDao().getAlbumDB();
        for(Album album : albums){
            Log.e(TAG,"Name: " + album.getTitle());
        }
        if(albums.size()>0)
            return true;
        else
            return false;
    }
}
