package com.mediomelon.demoalbum.model.repository;

import android.content.Context;
import android.util.Log;

import com.mediomelon.demoalbum.api.ServiceClient;
import com.mediomelon.demoalbum.dao.AlbumDataBase;
import com.mediomelon.demoalbum.interfaces.IPhotos;
import com.mediomelon.demoalbum.model.entity.Photo;
import com.mediomelon.demoalbum.utils.Constants;
import com.mediomelon.demoalbum.utils.InternetConnection;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoRepositoryAPI implements IPhotos.IRepository {
    private IPhotos.IPresenter photoPresenter;
    private final static String TAG = "PhotoRepositoryAPI";
    private ArrayList<Photo> photoList;
    private SimpleDateFormat sdf;
    private AlbumDataBase albumDataBase;

    public PhotoRepositoryAPI(IPhotos.IPresenter presenter, Context context) {
        this.photoPresenter = presenter;
        albumDataBase = AlbumDataBase.getDataBase(context);

    }

    @Override
    public void getPhotos(int albumId) {
        new InternetConnection(internet -> {
            if(internet){
                Call<List<Photo>> callPhoto = ServiceClient.createPhotoService().getPhotos(albumId);
                callPhoto.enqueue(new Callback<List<Photo>>() {
                    @Override
                    public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                        if (response.isSuccessful()) {
                            photoList = new ArrayList<>();
                            sdf = new SimpleDateFormat("yyyy/MM/dd 'at' HH:mm:ss", Locale.getDefault());
                            for (Photo photo : response.body()) {
                                photoList.add(photo);

                                photo.setStatus(Constants.STATUS_ACTIVE);//por default el status es created
                                String currentDateandTime = sdf.format(new Date());
                                photo.setDate(currentDateandTime);

                                //consulta
                                Photo photoId = albumDataBase.photoDao().getId(photo.getId());

                                if (photoId == null) {
                                    //insertar en bd;
                                    albumDataBase.photoDao().addPhoto(photo);
                                    Log.e(TAG, " inserted: " + "id: " + photo.getId() + " " + photo.getStatus() + " " + photo.getTitle());
                                }
                            }


                            //mostrar bd
                            showPhotos();

                            //actualizar un registro
//                    String currentDateandTime = sdf.format(new Date());
//                    Photo photoUpdate2 = new Photo(1, 2, "mi album", "mialbum.com", "mialbum.com");
//                    photoUpdate2.setStatus("Modified");
//                    photoUpdate2.setDate(currentDateandTime);
//                    Photo photoUpdate6 = new Photo(1, 6, "mi album 6", "mialbum.com", "mialbum.com");
//                    photoUpdate6.setStatus("Modified");
//                    photoUpdate6.setDate(currentDateandTime);
//                    Photo photoUpdate9 = new Photo(1, 9, "mi album 9", "mialbum.com", "mialbum.com");
//                    photoUpdate9.setStatus("Modified");
//                    photoUpdate9.setDate(currentDateandTime);
//
//                    PhotosActivity.albumDataBase.photoDao().updateUser(photoUpdate2);
//                    PhotosActivity.albumDataBase.photoDao().updateUser(photoUpdate6);
//                    PhotosActivity.albumDataBase.photoDao().updateUser(photoUpdate9);
//                    Log.e(TAG, "id actualizado: " + photoUpdate2.getId());
//                    Log.e(TAG, "id actualizado: " + photoUpdate6.getId());
//                    Log.e(TAG, "id actualizado: " + photoUpdate9.getId());

                            //borrar registro
//                    Photo photoDelete = PhotosActivity.albumDataBase.photoDao().getId(5);
//                    photoDelete.setStatus("Deleted");
//                    Photo photoDelete10 = PhotosActivity.albumDataBase.photoDao().getId(10);
//                    photoDelete10.setStatus("Deleted");
//                    Photo photoDelete11 = PhotosActivity.albumDataBase.photoDao().getId(11);
//                    photoDelete11.setStatus("Deleted");
//                    Photo photoDelete15 = PhotosActivity.albumDataBase.photoDao().getId(15);
//                    photoDelete15.setStatus("Deleted");
//
//                    if (photoDelete != null || photoDelete.getStatus() != "Deleted") {
//                        PhotosActivity.albumDataBase.photoDao().deletePhotos(photoDelete);
//                        Log.e(TAG, "deleted id: " + photoDelete.getId());
//                    }
//                    if (photoDelete10 != null || photoDelete10.getStatus() != "Deleted") {
//                        PhotosActivity.albumDataBase.photoDao().deletePhotos(photoDelete10);
//                        Log.e(TAG, "deleted id: " + photoDelete10.getId());
//                    }
//                    if (photoDelete11 != null || photoDelete11.getStatus() != "Deleted") {
//                        PhotosActivity.albumDataBase.photoDao().deletePhotos(photoDelete11);
//                        Log.e(TAG, "deleted id: " + photoDelete11.getId());
//                    }
//                    if (photoDelete15 != null || photoDelete15.getStatus() != "Deleted") {
//                        PhotosActivity.albumDataBase.photoDao().deletePhotos(photoDelete15);
//                        Log.e(TAG, "deleted id: " + photoDelete15.getId());
//                    }

                            //mostrar bd
                            //showPhotos();


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
            }else {
                photoPresenter.showErrorInternetConnection();
            }
        });



    }

    private void showPhotos() {
        //mostrar bd
        List<Photo> photos = albumDataBase.photoDao().getPhotos();

        for (Photo photo : photos) {
            int id = photo.getId();
            int albumID = photo.getAlbumId();
            String url = photo.getUrl();
            String status = photo.getStatus();
            String date = photo.getDate();

            Log.e(TAG, "album: " + albumID + " id: " + id + " status: " + status + " date: " + date + " url: " + url);
        }
    }
}
