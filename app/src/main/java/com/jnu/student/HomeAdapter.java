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

    @Override
    @NonNull
    public myViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = View.inflate(context,R.layout.adapter_home,null);
        return new myViewHodler(itemview);
    }

    @Override
    public void onBindViewHolder(myViewHodler holder, int position) {
        holder.getTitle().setText(bookArrayList.get(position).getTitle());
        holder.getAuthor().setText(bookArrayList.get(position).getAuthor());
        holder.getPublisher().setText(bookArrayList.get(position).getPublisher());
        holder.getImage().setImageResource(bookArrayList.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return bookArrayList.size();
    }
    class myViewHodler extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        private TextView title;
        private TextView author;
        private TextView publisher;
        private ImageView image;
        public TextView getTitle(){return title;}
        public TextView getAuthor(){return author;}
        public TextView getPublisher(){return publisher;}
        public ImageView getImage(){return image;}
        public myViewHodler(View itemview) {
            super(itemview);
            title = itemview.findViewById(R.id.item_title);
            author = itemview.findViewById(R.id.item_author);
            publisher = itemview.findViewById(R.id.item_publisher);
            image = itemview.findViewById(R.id.item_cover);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(0, MENU_ID_ADD, getAdapterPosition(), "添加" );
            menu.add(0, MENU_ID_UPDATE, getAdapterPosition(), "修改" );
            menu.add(0, MENU_ID_DELETE, getAdapterPosition(), "删除" );
        }
    }
}


