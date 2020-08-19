package com.example.midterm1;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;
import androidx.room.Query;
@Dao
public interface StudentDao {

    @Query("SELECT * FROM Student")
    public Student[] loadAllStudents();



    @Insert
    public void insertStudent(Student user);

    @Delete
    public void deleteStudent(Student user);

    @Update
    public void updateUsers(Student std);
}
