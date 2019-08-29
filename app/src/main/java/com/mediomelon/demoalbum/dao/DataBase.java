package com.mediomelon.demoalbum.dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.mediomelon.demoalbum.model.entity.Album;
import com.mediomelon.demoalbum.model.entity.Photo;
import com.mediomelon.demoalbum.model.entity.User;

@Database(entities = {Photo.class, Album.class, User.class, User.Address.class, User.Company.class}, version = 1)
public abstract class DataBase extends RoomDatabase {

    public abstract PhotoDao photoDao();

    public abstract AlbumDao albumDao();

    public abstract UserDao userDao();

}
