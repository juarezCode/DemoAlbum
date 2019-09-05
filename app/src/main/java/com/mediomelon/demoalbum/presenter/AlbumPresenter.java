package com.mediomelon.demoalbum.presenter;

import android.content.Context;

import com.mediomelon.demoalbum.interfaces.IAlbum;
import com.mediomelon.demoalbum.model.interactor.AlbumInteractor;
import com.mediomelon.demoalbum.model.entity.Album;

import java.util.ArrayList;

public class AlbumPresenter implements IAlbum.IPresenter {

    private IAlbum.IView albumView;
    private IAlbum.IModel albumInteractor;
    private Context ctx;

    public AlbumPresenter(IAlbum.IView albumView,Context ctx){
        this.albumView = albumView;
        this.ctx = ctx;
        albumInteractor = new AlbumInteractor(this,this.ctx);
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
