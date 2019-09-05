package com.mediomelon.demoalbum.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.mediomelon.demoalbum.model.entity.Album;

import java.util.List;

@Dao
public interface AlbumDao {
    @Insert
    void addAlbum(Album album);

    @Query("SELECT * FROM ALBUMS")
    List<Album> getAlbums();

    @Query("SELECT * FROM ALBUMS WHERE id IN (:id)")
    Album getAlbumId(int id);
}
