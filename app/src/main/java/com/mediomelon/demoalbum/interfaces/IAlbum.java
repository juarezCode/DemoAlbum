package com.mediomelon.demoalbum.interfaces;

import com.mediomelon.demoalbum.model.entity.Album;

import java.util.List;

public interface IAlbum {

    interface IModel{
        void getAlbums();
    }

    interface IPresenter{
        void getAlbums();
        void showAlbums(List<Album> albums);
        void showError(String error);
    }

    interface IView{
        void getAlbums();
        void showAlbums(List<Album> albums);
        void showErrorAlbum(String error);
    }
}
