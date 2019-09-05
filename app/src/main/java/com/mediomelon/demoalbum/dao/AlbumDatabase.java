package com.mediomelon.demoalbum.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mediomelon.demoalbum.model.entity.Album;
import com.mediomelon.demoalbum.model.entity.Photo;
import com.mediomelon.demoalbum.model.entity.User;

@Database(entities = {User.class, Album.class, Photo.class}, version = 1, exportSchema = false)
public abstract class AlbumDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract AlbumDao albumDao();
    public abstract PhotoDao photoDao();

    private static volatile AlbumDatabase INSTANCE;

    public static AlbumDatabase getDatabase(final Context ctx){
        if(INSTANCE == null){
            synchronized (AlbumDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(ctx.getApplicationContext(),
                            AlbumDatabase.class,"albums_database")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
