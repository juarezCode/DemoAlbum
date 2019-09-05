package com.mediomelon.demoalbum.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.mediomelon.demoalbum.util.Constants;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Entity(tableName = Constants.TABLE_ALBUM)
public class Album implements Serializable {
    @ColumnInfo
    private int userId;
    @PrimaryKey
    @NotNull
    private int id;
    @ColumnInfo
    private String title;
    @ColumnInfo()
    private String status;
    @ColumnInfo
    private String date;

    public Album(int userId, int id, String title) {
        this.userId = userId;
        this.id = id;
        this.title = title;
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
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
