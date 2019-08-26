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

public class PhotoInteractor implements IPhotos.IModel {

    private IPhotos.IPresenter photoPresenter;
    private final static String TAG = "PhotoInteractor";
    private ArrayList<Photo> photoList;

    public PhotoInteractor(IPhotos.IPresenter photoPresenter) {
        this.photoPresenter = photoPresenter;
    }

    @Override
    public void getPhotos(int id) {
        Call<List<Photo>> callPhoto = ServiceClient.createPhotoService().getPhotos(id);
        callPhoto.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                if (response.isSuccessful()) {
                    photoList = new ArrayList<>();
                    for (Photo photo : response.body()) {
                        photoList.add(photo);

                        Log.e(TAG, " albumId : " + photo.getTitle());
                    }
                    photoPresenter.showPhotos(photoList);

                } else if (response.code() == 401)
                    photoPresenter.showErrorPhotos("Bad authentication");
                else if (response.code() == 404)
                    photoPresenter.showErrorPhotos("Users Not Found");
                else
                    Log.e(TAG, "Unexpected error");
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                photoPresenter.showErrorPhotos(t.toString());
            }
        });

    }
}