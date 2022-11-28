package com.jnu.student;

import static com.jnu.student.HomeFragment.MENU_ID_ADD;
import static com.jnu.student.HomeFragment.MENU_ID_DELETE;
import static com.jnu.student.HomeFragment.MENU_ID_UPDATE;
import android.content.Context;
import android.view.ContextMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.myViewHodler> {
    private Context context;
    private ArrayList<Book> bookArrayList;

    public HomeAdapter(Context context, ArrayList<Book> bookArrayList) {
        this.context = context;
        this.bookArrayList = bookArrayList;
    }

    @NonNull
    @Override
    public myViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = View.inflate(context,R.layout.adapter_home,null);
        return new myViewHodler(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHodler holder, int position) {
        Book book = bookArrayList.get(position);
        holder.text.setText(book.getTitle());
        holder.image.setImageResource(book.getId());
    }

    @Override
    public int getItemCount() {
        return bookArrayList.size();
    }
    class myViewHodler extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        private TextView text;
        private ImageView image;
        public myViewHodler(View itemview) {
            super(itemview);
            text = (TextView)itemview.findViewById(R.id.item_title);
            image = (ImageView)itemview.findViewById(R.id.item_cover);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(0, MENU_ID_ADD, 0, "添加" );
            menu.add(0, MENU_ID_UPDATE, 0, "修改" );
            menu.add(0, MENU_ID_DELETE, 0, "删除" );
        }
    }
}


