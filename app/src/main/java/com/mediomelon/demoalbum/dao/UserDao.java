package com.mediomelon.demoalbum.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.mediomelon.demoalbum.model.entity.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void addUser(User user);

    @Query("SELECT * FROM users")
    List<User> getUsersDB();
}
