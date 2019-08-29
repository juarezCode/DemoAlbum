package com.mediomelon.demoalbum.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mediomelon.demoalbum.model.entity.Photo;

import java.util.List;

@Dao
public interface PhotoDao {

    @Insert
    void addPhoto(Photo photo);

    @Query("select * from photos")
    List<Photo> getPhotos();

    @Query("SELECT * FROM photos WHERE id IN (:id)")
    Photo getId(int id);

    @Query("SELECT * FROM photos WHERE albumId IN (:albumId)")
    List<Photo> getPhotosById(int albumId);

    @Update
    void deletePhotos(Photo photo);

    @Update
    void updateUser(Photo photo);

}
