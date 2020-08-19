package com.sizey.cardata;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

// Room DB entity
@Database(entities = {Car.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CarDao carDao();

    // Room DB singleton
    private static AppDatabase INSTANCE;


    // To use Room DB, create Room DB instance
    public static AppDatabase getInstance(Context context){

        if (INSTANCE==null){
            synchronized (AppDatabase.class){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class,"car.db")
                        .build();
            }
        }
        return INSTANCE;
    }
    public static void destroyInstance(){
        INSTANCE = null;
    }
}
