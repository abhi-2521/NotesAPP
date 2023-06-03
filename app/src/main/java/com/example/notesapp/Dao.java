package com.example.notesapp;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@androidx.room.Dao
public interface Dao {
    @Query("select * from notes")
    List<Notes> getList();
    @Insert
    public void add(Notes notes);
    @Delete
    public void delete(Notes notes);
    @Update
    public void update(Notes notes);
}
