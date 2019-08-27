package com.mediomelon.demoalbum.model;

import android.util.Log;

import com.google.gson.Gson;
import com.mediomelon.demoalbum.api.IAlbumService;
import com.mediomelon.demoalbum.api.ServiceClient;
import com.mediomelon.demoalbum.interfaces.IAlbum;
import com.mediomelon.demoalbum.model.entity.Album;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumInteractor implements IAlbum.IModel {

    private final static String TAG = "AlbumInteractor";
    private IAlbum.IPresenter albumPresenter;
    private ArrayList<Album> arrayListAlbum;

    public AlbumInteractor(IAlbum.IPresenter albumPresenter){
        this.albumPresenter = albumPresenter;
    }

    @Override
    public void getAlbums() {
        IAlbumService albumService = ServiceClient.createAlbumService();
        Call<List<Album>> albumDataCall = albumService.getAlbums();
        albumDataCall.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                Gson gson = new Gson();
                if(response.isSuccessful()){
                    arrayListAlbum = new ArrayList<>();
                    Log.e(TAG,">>>>>Response albums>>>>>" + gson.toJson(response.body()));
                    assert response.body() != null;
                    for(Album album : response.body()){
                        arrayListAlbum.add(album);
                    }
                    albumPresenter.showAlbums(arrayListAlbum);
                }else{
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
}
