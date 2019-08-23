package com.mediomelon.demoalbum.model;

import android.util.Log;

import com.mediomelon.demoalbum.api.ServiceClient;
import com.mediomelon.demoalbum.interfaces.IPhotos;
import com.mediomelon.demoalbum.model.entity.Photo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoInteractor implements IPhotos.iModel {

    private IPhotos.iPresenter photoPresenter;
    private final static String TAG = "PhotoInteractor";
    private ArrayList<Photo> photoList;

    public PhotoInteractor(IPhotos.iPresenter photoPresenter) {
        this.photoPresenter = photoPresenter;
    }

    @Override
    public void getPhotos() {
        Call<List<Photo>> callPhoto = ServiceClient.createPhotoService().getPhotos("1");
        callPhoto.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                if(response.isSuccessful()){
                    photoList = new ArrayList<>();
                    for(Photo photo : response.body()){
                       photoList.add(photo);

                        Log.e(TAG," albumId : "+ photo.getAlbumId());
                    }
                    photoPresenter.showPhotos(photoList);
                } else if (response.code() == 401)
                    photoPresenter.showError("Bad authentication");
                else if (response.code() == 404)
                    photoPresenter.showError("Users Not Found");
                else
                    Log.e(TAG, "Unexpected error");
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {

            }
        });

    }
}
