package com.mediomelon.demoalbum.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mediomelon.demoalbum.model.entity.Album;
import com.mediomelon.demoalbum.model.entity.Photo;
import com.mediomelon.demoalbum.model.entity.User;
import com.mediomelon.demoalbum.utils.Constants;

@Database(entities = {Photo.class, Album.class, User.class, User.Address.class, User.Company.class}, version = 1)
public abstract class AlbumDataBase extends RoomDatabase {

    public abstract PhotoDao photoDao();

    public abstract AlbumDao albumDao();

    public abstract UserDao userDao();

    private static volatile AlbumDataBase INSTANCE;

    public static AlbumDataBase getDataBase(final Context context){
        if(INSTANCE == null){
            synchronized (AlbumDataBase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AlbumDataBase.class, Constants.NAME_DB)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
