package com.example.mytodolist;

import android.content.Context;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Thing {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name ="title")
    public String title;
    @ColumnInfo(name ="content")
    public String content;
    @ColumnInfo(name ="date")
    public String date;
    public int getId(){
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) { this.title = title; }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
}
