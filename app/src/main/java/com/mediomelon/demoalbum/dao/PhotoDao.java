package com.mediomelon.demoalbum.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.mediomelon.demoalbum.model.entity.Photo;

import java.util.List;

@Dao
public interface PhotoDao {

    @Insert
    void addPhoto(Photo photo);

    @Query("SELECT * FROM photos WHERE albumId IN (:albumId)")
    List<Photo> getPhotos(int albumId);

}
