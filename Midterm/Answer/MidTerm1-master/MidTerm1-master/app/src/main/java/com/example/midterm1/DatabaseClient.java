package com.example.midterm1;

import android.content.Context;

import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DatabaseClient {

    private static StudentDataBase sDatabase;
    public static DatabaseClient sDBInstance;

    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(4);

    public static DatabaseClient getInstance(Context context){

        if (sDBInstance == null) {
            sDatabase = Room.databaseBuilder(context, StudentDataBase.class, "StudentDB").build();        }


        return sDBInstance;
    }

//    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
//        @Override
//        public void migrate(SupportSQLiteDatabase database) {
//            databaseWriteExecutor.execute(() -> {
//                database.execSQL("CREATE TABLE sampleTable (id INTEGER primary key autoincrement Not Null, studentName TEXT, level INTEGER , year INTEGER)");
//                database.execSQL("INSERT INTO sampleTable(id, studentName, level, year) SELECT id, studentName, level, year FROM Student;");
//                database.execSQL("DROP TABLE Student");
//                database.execSQL("ALTER TABLE sampleTable RENAME TO Student;");
//            });
//        }
//    };


    public static StudentDataBase getDatabase(){
        return sDatabase;
    }


    public static void insertToDB (Context context, Student std){
            databaseWriteExecutor.execute(()->{
                getInstance(context).sDatabase.StudentDoa().insertStudent(std);

            });

    }

//    public static Student[] getAllStudents (Context context){
//        Student[] allSTD;
//        databaseWriteExecutor.execute(()->{
//         allSTD =  getInstance(context).sDatabase.StudentDoa().loadAllStudents();
//
//
//        });
//
//        return allSTD;
//
//    }



}
