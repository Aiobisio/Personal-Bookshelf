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
            FileOutputStream dataStream=context.openFileOutput("data_log",Context.MODE_PRIVATE);
            ObjectOutputStream out=new ObjectOutputStream(dataStream);
            out.writeObject(data);
            out.close();
            dataStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @NonNull
    public ArrayList<Book> load(Context context){
        ArrayList<Book> data=new ArrayList<>();
        try{
            FileInputStream file_in=context.openFileInput("data_log");
            ObjectInput in=new ObjectInputStream(file_in);
            data=(ArrayList<Book>) in.readObject();
            in.close();
            file_in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
