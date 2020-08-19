package com.example.week7project2;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class JsonManager {
    // ArrayList for person names, email Id's and mobile numbers
    ArrayList<String> personNames = new ArrayList<>();
    ArrayList<String> emailIds = new ArrayList<>();
    ArrayList<String> mobileNumbers = new ArrayList<>();

    public String loadJSONFromAsset(Context context,String fileName) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


    public CustomAdapter buildAdapterFromJsonData(Context context,String fileName, int storageType){
        JSONObject obj;
        try {
            personNames = new ArrayList<>();
            emailIds = new ArrayList<>();
            mobileNumbers = new ArrayList<>();

            if (storageType == 1)//json in assets folder
            // get JSONObject from JSON file
            {
                obj = new JSONObject(loadJSONFromAsset(context, fileName));
            }
            else { // get json from external storage
                 obj = new JSONObject(getExternalPrivate(context, fileName));
            }
            // fetch JSONArray named users
            JSONArray userArray = obj.getJSONArray("users");
            // implement for loop for getting users list data
            for (int i = 0; i < userArray.length(); i++) {
                // create a JSONObject for fetching single user data
                JSONObject userDetail = userArray.getJSONObject(i);
                // fetch email and name and store it in arraylist
                personNames.add(userDetail.getString("name"));
                emailIds.add(userDetail.getString("email"));
                // create a object for getting contact data from JSONObject
                JSONObject contact = userDetail.getJSONObject("contact");
                // fetch mobile number and store it in arraylist
                mobileNumbers.add(contact.getString("mobile"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //  call the constructor of CustomAdapter to send the reference and data to Adapter
        CustomAdapter customAdapter = new CustomAdapter(context, personNames, emailIds, mobileNumbers);
        return customAdapter;
    }

    public void writeJsonToExternalPrivateStorage(Context context,String fileName, String jsonToStore){
        OutputStream os = null;
        try {
            File folder = context.getExternalFilesDir("Week7PrivateExternalData");// Folder Name
            File myFile = new File(folder, fileName);// Filename
            OutputStream outStream = new FileOutputStream(myFile);
            outStream.write(jsonToStore.getBytes());

        } catch (IOException ex) {
            ex.printStackTrace();
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
