package com.mediomelon.demoalbum.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mediomelon.demoalbum.model.entity.User;
import com.mediomelon.demoalbum.utils.Constants;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void addUser(User user);

    @Query("SELECT * FROM USERS WHERE user_status = '"+Constants.STATUS_ACTIVE+"'")
    List<User> getUsers();

    @Query("SELECT * FROM USERS WHERE user_id IN (:id)")
    User getUserById(int id);

    @Query("SELECT * FROM USERS WHERE user_username == (:username) AND user_status == 'Active'")
    User getUserByUsername(String username);

    @Update
    void updateUser(User user);

    @Update
    void deleteUser(User user);

    @Query("SELECT *, MAX(user_id) FROM users")
    User getMaxId();

    @Query("SELECT user_id FROM USERS WHERE user_id IN (:id) ")
    int getOnlyUserId(int id);

    @Query("UPDATE USERS SET user_status = 'Deleted' WHERE user_id IN (:id)")
    int deleteUserById(int id);
}
