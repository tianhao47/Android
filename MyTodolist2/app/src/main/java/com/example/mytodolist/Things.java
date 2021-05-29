package com.example.mytodolist;


import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.RoomDatabase;

@Database(entities= {Thing.class},version = 1)

public abstract class Things extends RoomDatabase{
    public abstract ThingDao thingDao();
}


