package com.mediomelon.demoalbum.model.repository;

import android.content.Context;
import android.util.Log;

import com.mediomelon.demoalbum.dao.AlbumDatabase;
import com.mediomelon.demoalbum.interfaces.IAlbum;
import com.mediomelon.demoalbum.model.entity.Album;
import com.mediomelon.demoalbum.model.entity.User;

import java.util.ArrayList;
import java.util.List;

public class AlbumRepositoryDB implements IAlbum.IRepository {
    private static final String TAG = "AlbumRepositoryDB";
    private IAlbum.IPresenter albumPresenter;
    private Context ctx;
    private AlbumDatabase albumDatabase;

    public AlbumRepositoryDB(IAlbum.IPresenter albumPresenter, Context ctx){
        this.albumPresenter = albumPresenter;
        this.ctx = ctx;
        albumDatabase = AlbumDatabase.getDatabase(this.ctx);
    }

    @Override
    public void getAlbums() {
       List<Album> albums = albumDatabase.albumDao().getAlbumDB();
       for (Album album : albums){
           Log.e(TAG,"Name:" + album.getTitle());
       }
        ArrayList<Album> albumArrayList = new ArrayList<>(albums);
       albumPresenter.showAlbums(albumArrayList);
    }
}
