package com.mediomelon.demoalbum.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.mediomelon.demoalbum.R;
import com.mediomelon.demoalbum.interfaces.IPhotos;
import com.mediomelon.demoalbum.model.entity.Photo;
import com.mediomelon.demoalbum.presenter.PhotoPresenter;

import java.util.ArrayList;

public class PhotosActivity extends AppCompatActivity implements IPhotos.iView{

    private IPhotos.iPresenter photosPresenter;
    private final static String TAG = "PhotosActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);

        photosPresenter=new PhotoPresenter(this);

        getPhotos();
    }

    @Override
    public void getPhotos() {
        photosPresenter.getPhotos();
    }

    @Override
    public void showPhotos(ArrayList<Photo> photos) {

    }

    @Override
    public void showError(String error) {
        Log.e(TAG,"Error: " + error);
    }
}
