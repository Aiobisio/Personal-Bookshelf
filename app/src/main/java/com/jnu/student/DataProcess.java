package com.jnu.student;

import android.content.Context;
import androidx.annotation.NonNull;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DataProcess {
    public void save(Context context, ArrayList<Book> data){
        try{
            FileOutputStream dataStream=context.openFileOutput("data_log.dat",Context.MODE_PRIVATE);
            ObjectOutputStream out=new ObjectOutputStream(dataStream);
            out.writeObject(dataStream);
            out.close();
            dataStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @NonNull
    public ArrayList<Book> Load(Context context){
        ArrayList<Book>data=null;
        try{
            FileInputStream file_in=context.openFileInput("datalog.dat");
            ObjectInput in=new ObjectInputStream(file_in);
            data=(ArrayList<Book>)in.readObject();
            in.close();
            file_in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
