package com.mediomelon.demoalbum.presenter;

import com.mediomelon.demoalbum.interfaces.IAlbum;
import com.mediomelon.demoalbum.model.AlbumInteractor;
import com.mediomelon.demoalbum.model.entity.Album;

import java.util.List;

public class AlbumPresenter implements IAlbum.IPresenter {

    private IAlbum.IView albumView;
    private IAlbum.IModel albumInteractor;

    public AlbumPresenter(IAlbum.IView albumView){
        this.albumView = albumView;
        albumInteractor = new AlbumInteractor(this);
    }

    @Override
    public void getAlbums() {
        albumInteractor.getAlbums();
    }

    @Override
    public void showAlbums(List<Album> albums) {
        albumView.showAlbums(albums);
    }

    @Override
    public void showError(String error) {
        albumView.showErrorAlbum(error);
    }
}
