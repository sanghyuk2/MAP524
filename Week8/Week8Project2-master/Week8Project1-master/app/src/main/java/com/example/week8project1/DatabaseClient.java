package com.example.week8project1;

import android.content.Context;
import android.content.Intent;

import androidx.room.Room;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DatabaseClient {

    private Context mCtx;
    private static DatabaseClient mInstance;

    //our app database object
    private AppDatabase appDatabase;
    private static final int NUMBER_OF_THREADS=4;
    public static final ExecutorService databaseWriteExecutor= Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private DatabaseClient(Context mCtx) {
        this.mCtx = mCtx;

        //creating the app database with Room database builder
        //MyToDos is the name of the database
        appDatabase = Room.databaseBuilder(mCtx, AppDatabase.class, "MyToDos").build();
    }

    public static synchronized DatabaseClient getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new DatabaseClient(mCtx);
        }
        return mInstance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }

    public static void insert(Context context, Task task){
        DatabaseClient.databaseWriteExecutor.execute(()->{
            DatabaseClient.getInstance(context).getAppDatabase()
                    .taskDao()
                    .insert(task);
        });
    }
    public static void update(Context context, Task task){
        DatabaseClient.databaseWriteExecutor.execute(()->{

            DatabaseClient.getInstance(context).getAppDatabase()
                    .taskDao()
                    .update(task);

        });
    }




    public static void deleteTask(Context context, Task task) {
        DatabaseClient.databaseWriteExecutor.execute(()->{
           DatabaseClient.getInstance(context).getAppDatabase()
                    .taskDao()
                    .delete(task);


        });
    }
}

