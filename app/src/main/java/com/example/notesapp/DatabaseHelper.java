package com.example.notesapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Notes.class,exportSchema = false,version = 1)
public abstract class DatabaseHelper extends RoomDatabase {
    private static final String DATABASE="NotesDB";
    private static DatabaseHelper helper;
    public static synchronized DatabaseHelper getHelper(Context context)
    {
        if (helper==null)
        {
            helper= Room.databaseBuilder(context,DatabaseHelper.class,DATABASE)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration().
                    build();
        }
        return helper;
    }
    public abstract Dao getDao();
}
