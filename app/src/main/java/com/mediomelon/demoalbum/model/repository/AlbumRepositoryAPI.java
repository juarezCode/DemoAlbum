package com.mediomelon.demoalbum.model.repository;

import android.content.Context;
import android.util.Log;

import com.mediomelon.demoalbum.api.IAlbumService;
import com.mediomelon.demoalbum.api.ServiceClient;
import com.mediomelon.demoalbum.dao.AlbumDataBase;
import com.mediomelon.demoalbum.interfaces.IAlbum;
import com.mediomelon.demoalbum.model.entity.Album;
import com.mediomelon.demoalbum.view.activity.LoginActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumRepositoryAPI implements IAlbum.IRepository {
    private static final String TAG = "AlbumRepositoryAPI";
    private IAlbum.IPresenter albumPresenter;
    private ArrayList<Album> arrayListAlbum;
    private SimpleDateFormat sdf;
    private AlbumDataBase albumDataBase;

    public AlbumRepositoryAPI(IAlbum.IPresenter presenter, Context context) {
        this.albumPresenter = presenter;
        albumDataBase = AlbumDataBase.getDataBase(context);
    }

    @Override
    public void getAlbums() {
        IAlbumService albumService = ServiceClient.createAlbumService();
        Call<List<Album>> albumDataCall = albumService.getAlbums();
        albumDataCall.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                if (response.isSuccessful()) {
                    arrayListAlbum = new ArrayList<>();
                    //Log.e(TAG, ">>>>>Response albums>>>>>" + gson.toJson(response.body()));
                    assert response.body() != null;

                    sdf = new SimpleDateFormat("yyyy/MM/dd 'at' HH:mm:ss", Locale.getDefault());
                    for (Album album : response.body()) {
                        arrayListAlbum.add(album);

                        album.setStatus("Created");//por default el status es created
                        String currentDateandTime = sdf.format(new Date());
                        album.setDate(currentDateandTime);

                        //consulta
                        Album albumId = albumDataBase.albumDao().getAlbumId(album.getId());

                        if (albumId == null) {
                            //insertar en bd;
                            albumDataBase.albumDao().addAlbum(album);
                            Log.e(TAG, " inserted: " + "id: " + album.getId() + " " + album.getUserId() + " " + album.getTitle());
                        }
                    }
                    //mostrar bd
                    showAlbums();

                    albumPresenter.showAlbums(arrayListAlbum);
                } else {
                    if (response.code() == 401) {
                        albumPresenter.showError("Not authorized");
                    } else {
                        if (response.code() == 404) {
                            albumPresenter.showError("Not Found");
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                albumPresenter.showError(t.toString());
            }
        });
    }

    private void showAlbums() {
        //mostrar bd
        List<Album> albums = albumDataBase.albumDao().getAlbums();

        for (Album album : albums) {
            int id = album.getId();
            int userId = album.getUserId();
            String title = album.getTitle();
            String status = album.getStatus();
            String date = album.getDate();

            Log.e(TAG, "userId: " + userId + " id " + id + " " + status + " " + date + " title " + title);
        }
    }
}
