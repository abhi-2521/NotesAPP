package com.example.notesapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Notes {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "Title")
    String Title;
    @ColumnInfo(name = "Content")
    String Content;

    public Notes(int id, String title, String content) {
        this.id = id;
        Title = title;
        Content = content;
    }

    public Notes() {
    }

    @Ignore
    public Notes(String title, String content) {
        Title = title;
        Content = content;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
