package com.mediomelon.demoalbum.api;

import com.mediomelon.demoalbum.model.entity.Photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IPhotoService {
    //https://jsonplaceholder.typicode.com/photos?albumId={albumId}
    @GET("photos")
    Call<List<Photo>> getPhotos(@Query("albumId") String albumId);
}
