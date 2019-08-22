package com.mediomelon.demoalbum.api;

import com.mediomelon.demoalbum.model.entity.Album;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IAlbumService {

    @GET("albums")
    Call<List<Album>> getAlbums();
}
