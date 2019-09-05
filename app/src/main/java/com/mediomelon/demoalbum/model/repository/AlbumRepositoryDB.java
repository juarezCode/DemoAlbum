package com.mediomelon.demoalbum.model.repository;

import android.util.Log;

import com.mediomelon.demoalbum.interfaces.IAlbum;
import com.mediomelon.demoalbum.model.entity.Album;
import com.mediomelon.demoalbum.view.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class AlbumRepositoryDB implements IAlbum.IRepository {

    private static final String TAG = "AlbumRepositoryDB";
    private IAlbum.IPresenter albumPresenter;

    public AlbumRepositoryDB(IAlbum.IPresenter presenter) {
        this.albumPresenter = presenter;
    }

    @Override
    public void getAlbums() {
        //mostrar bd
        List<Album> albums = MainActivity.dataBase.albumDao().getAlbums();

        for (Album album : albums) {
            int id = album.getId();
            int userId = album.getUserId();
            String title = album.getTitle();
            String status = album.getStatus();
            String date = album.getDate();


            Log.e(TAG, "userId: " + userId + " id " + id + " " + status + " " + date);

        }
        ArrayList<Album> listPhotos = new ArrayList<>(albums);
        albumPresenter.showAlbums(listPhotos);
    }
}
