package com.mediomelon.demoalbum.model.repository;

import android.content.Context;
import android.util.Log;

import com.mediomelon.demoalbum.dao.AlbumDataBase;
import com.mediomelon.demoalbum.interfaces.IAlbum;
import com.mediomelon.demoalbum.model.entity.Album;
import com.mediomelon.demoalbum.view.activity.LoginActivity;

import java.util.ArrayList;
import java.util.List;

public class AlbumRepositoryDB implements IAlbum.IRepository {

    private static final String TAG = "AlbumRepositoryDB";
    private IAlbum.IPresenter albumPresenter;
    private AlbumDataBase albumDataBase;

    public AlbumRepositoryDB(IAlbum.IPresenter presenter, Context context) {
        this.albumPresenter = presenter;
        albumDataBase = AlbumDataBase.getDataBase(context);
    }

    @Override
    public void getAlbums() {
        //mostrar bd
        //List<Album> albums = LoginActivity.albumDataBase.albumDao().getAlbums();
        List<Album> albums = albumDataBase.albumDao().getAlbums();

        for (Album album : albums) {
            int id = album.getId();
            int userId = album.getUserId();
            String status = album.getStatus();
            String date = album.getDate();

            Log.e(TAG, "userId: " + userId + " id " + id + " " + status + " " + date);

        }
        ArrayList<Album> listPhotos = new ArrayList<>(albums);
        albumPresenter.showAlbums(listPhotos);
    }
}
