package com.yongliu.habitloop.models;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by YongLiu on 3/24/16.
 * temp storage
 */
public class Storage {
    private static final String STORAGE_FILENAME = "habits_storage";
    public static ArrayList<Habit> mHabits= new ArrayList<Habit>();
    private final Context mContext;

    public Storage(Context cxt){
        mContext = cxt;
        mHabits = readFromInternalStorage();
    }

    public void saveToInternalStorage(ArrayList<Habit> listToSave) {
        try {
            FileOutputStream fos = mContext.openFileOutput(STORAGE_FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream of = new ObjectOutputStream(fos);
            of.writeObject(listToSave);
            of.flush();
            of.close();
            fos.close();
        }
        catch (Exception e) {
            Trace.e("InternalStorage", e.getMessage());
        }
    }

    public void saveToInternalStorage(ArrayList<Habit> listToSave, String username) {
        try {
            FileOutputStream fos = mContext.openFileOutput(STORAGE_FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream of = new ObjectOutputStream(fos);
            of.writeObject(listToSave);
            of.writeObject(username);
            of.flush();
            of.close();
            fos.close();
        }
        catch (Exception e) {
            Trace.e("InternalStorage", e.getMessage());
        }
    }

    public ArrayList<Habit> readFromInternalStorage() {
        ArrayList<Habit> listReturn = new ArrayList<Habit>();
        FileInputStream fis = null;
        try {
            fis = mContext.openFileInput(STORAGE_FILENAME);

        } catch (FileNotFoundException e) {
            Trace.e("InternalStorage", e.getMessage());
        }

        if(fis != null){
            ObjectInputStream oi = null;
            try {
                oi = new ObjectInputStream(fis);
                if(oi != null)
                    listReturn = (ArrayList<Habit>) oi.readObject();
                oi.close();
            } catch (IOException e) {
                e.printStackTrace();
            }catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
        return listReturn;
    }

    public String readUserFromInternalStorage() {
        String stringReturn = "";
        FileInputStream fis = null;
        try {
            fis = mContext.openFileInput(STORAGE_FILENAME);

        } catch (FileNotFoundException e) {
            Trace.e("InternalStorage", e.getMessage());
        }

        if(fis != null){
            ObjectInputStream oi = null;
            try {
                oi = new ObjectInputStream(fis);
                if(oi != null)
                    stringReturn = (String) oi.readObject();
                oi.close();
            } catch (IOException e) {
                e.printStackTrace();
            }catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
        return stringReturn;
    }


}
