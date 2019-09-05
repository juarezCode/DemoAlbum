package com.mediomelon.demoalbum.interfaces;

import com.mediomelon.demoalbum.model.entity.Album;

import java.util.ArrayList;
import java.util.List;

public interface IAlbum {

    interface IRepository{
        void getAlbums();
    }

    interface IModel{
        void getAlbums();
    }

    interface IPresenter{
        void getAlbums();
        void showAlbums(ArrayList<Album> albums);
        void showError(String error);
    }

    interface IView{
        void getAlbums();
        void showAlbums(ArrayList<Album> albums);
        void showErrorAlbum(String error);
    }
}
