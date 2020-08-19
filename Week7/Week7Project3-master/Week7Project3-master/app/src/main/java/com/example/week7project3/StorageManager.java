package com.example.week7project3;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class StorageManager {

    private static int STORAGE_PERMISSION_CODE = 23;

    public String getExternalPublic() {
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_ALARMS); // Folder Name
        File myFile = new File(folder, "userData1.txt"); // Filename
        String text = getdata(myFile);
        if (text != null) {
            return text;
        } else {
            return "no Data";
        }
    }
    public String getExternalPrivate(Context activity,String fileName) {
        File folder =  activity.getExternalFilesDir("Week7PrivateExternalData"); // Folder Name
        File myFile = new File(folder, fileName); // Filename
        String text = getdata(myFile);
        if (text != null) {
            return text;
        } else {
            return "no Data";
        }
    }
    public void saveExternalPublic(Activity activity, String toStore) {
        //Permission to access external storage
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);

        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_ALARMS);// Folder Name
        File myFile = new File(folder, "userData1.txt");// Filename
        writeData(myFile, toStore);
       
    }

    public void saveExternalPrivate(Activity activity, String fileName, String toStore) {
        File folder = activity.getExternalFilesDir("Week7PrivateExternalData");// Folder Name
        File myFile = new File(folder, fileName);// Filename
        writeData(myFile, toStore);

    }

    private void writeData(File myFile, String data) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(myFile);
            fileOutputStream.write(data.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void saveInternal(Activity activity, String toStore){
        FileOutputStream fileOutputStream = null;
        try {
            File file = activity.getFilesDir();
            fileOutputStream = activity.openFileOutput("userData3.txt", Context.MODE_PRIVATE); //MODE PRIVATE //MODE_APPEND
            fileOutputStream.write(toStore.getBytes());
            Toast.makeText(activity, "Saved \n" + "Path --" + file + "\tuserData3.txt", Toast.LENGTH_SHORT).show();

            return;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String loadFromInternal(Activity activity){
        String loadedData = "";
        try {
            FileInputStream fileInputStream =  activity.openFileInput("userData3.txt");
            int read = -1;
            StringBuffer buffer = new StringBuffer();
            while((read =fileInputStream.read())!= -1){
                buffer.append((char)read);
            }
            Log.d("Code", buffer.toString());

            loadedData = buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loadedData;
    }

    private String getdata(File myfile) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(myfile);
            int i = -1;
            StringBuffer buffer = new StringBuffer();
            while ((i = fileInputStream.read()) != -1) {
                buffer.append((char) i);
            }
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
