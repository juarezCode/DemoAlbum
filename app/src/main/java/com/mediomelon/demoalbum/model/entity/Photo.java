package com.mediomelon.demoalbum.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.mediomelon.demoalbum.utils.Constants;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = Constants.TABLE_PHOTO)
public class Photo {
    @ColumnInfo
    private int albumId;
    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "photo_id")
    private int id;
    @ColumnInfo
    private String title;
    @ColumnInfo
    private String url;
    @ColumnInfo
    private String thumbnailUrl;
    @ColumnInfo(defaultValue = "pending")
    private String status;
    @ColumnInfo
    private String date;

    public Photo(int albumId, int id, String title, String url, String thumbnailUrl) {
        this.albumId = albumId;
        this.id = id;
        this.title = title;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
    }

    public int getAlbumId() {
        return albumId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}