package com.mediomelon.demoalbum.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mediomelon.demoalbum.model.entity.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void addUser(User user);

    @Query("SELECT * FROM users")
    List<User> getUsers();

    @Query("SELECT * FROM users WHERE id IN (:id)")
    User getUserById(int id);

    @Update
    void updateUser(User user);

    @Update
    void deleteUser(User user);
}
