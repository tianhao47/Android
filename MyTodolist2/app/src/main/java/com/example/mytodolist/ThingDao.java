package com.example.mytodolist;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ThingDao{
    @Query("Select * FROM Thing")
    List<Thing> getAll();

    @Update
    void update(Thing item);

    @Insert
    void insert(Thing item);

    @Delete
    void delete(Thing item);
}
