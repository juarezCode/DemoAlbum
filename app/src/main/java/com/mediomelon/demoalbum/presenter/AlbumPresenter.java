package com.mediomelon.demoalbum.presenter;

import android.content.Context;

import com.mediomelon.demoalbum.interfaces.IAlbum;
import com.mediomelon.demoalbum.model.interactor.AlbumInteractor;
import com.mediomelon.demoalbum.model.entity.Album;

import java.util.ArrayList;

public class AlbumPresenter implements IAlbum.IPresenter {

    private IAlbum.IView albumView;
    private IAlbum.IModel albumInteractor;

    public AlbumPresenter(IAlbum.IView albumView, Context context) {
        this.albumView = albumView;
        albumInteractor = new AlbumInteractor(this, context);
    }

    @Override
    public void getAlbums() {
        albumInteractor.getAlbums();
    }

    @Override
    public void showAlbums(ArrayList<Album> albums) {
        albumView.showAlbums(albums);
    }

    @Override
    public void showError(String error) {
        albumView.showErrorAlbum(error);
    }
}
