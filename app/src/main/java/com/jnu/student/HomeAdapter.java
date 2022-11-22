package com.jnu.student;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.myViewHodler> {
    private Context context;
    private ArrayList<Book> bookArrayList;

    public HomeAdapter(Context context, ArrayList<Book> beanArrayList) {
        this.context = context;
        this.bookArrayList = beanArrayList;
    }

    @NonNull
    @Override
    public myViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = View.inflate(context,R.layout.adapter_home,null);
        return new myViewHodler(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHodler holder, int position) {
        Book data = bookArrayList.get(position);
        holder.text.setText(data.getId());
    }

    @Override
    public int getItemCount() {
        return bookArrayList.size();
    }
    public class myViewHodler extends RecyclerView.ViewHolder{
        private TextView text;
        public myViewHodler(View itemview) {
            super(itemview);
            text = (TextView)itemview.findViewById(R.id.item_tv);
        }
    }
}

