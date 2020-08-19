package com.example.midterm1;


import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Student.class},version = 2)
public abstract class StudentDataBase extends RoomDatabase {
    public abstract StudentDao StudentDoa () ;



}


