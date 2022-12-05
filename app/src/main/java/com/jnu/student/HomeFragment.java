package com.jnu.student;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;


public class HomeFragment extends Fragment {
    private View rootView;
    public static final int MENU_ID_ADD = 1;
    public static final int MENU_ID_UPDATE = 2;
    public static final int MENU_ID_DELETE = 3;
    private ArrayList<Book> bookArrayList = new ArrayList<>();
    private RecyclerView recyclerview;
    private HomeAdapter homeadapter;
    private final ActivityResultLauncher<Intent> addLauncher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult()
            ,result ->{
                if(null!=result){
                    if(result.getResultCode()==EditActivity.RESULT_CODE_SUCCESS){
                    Intent intent=result.getData();
                    Bundle bundle=intent.getExtras();
                    String title=bundle.getString("title");
                    String author=bundle.getString("author");
                    String publisher=bundle.getString("publisher");
                    int position=bundle.getInt("position");
                    bookArrayList.add(position,new Book(title,author,publisher,R.drawable.book));
                    new DataProcess().save(this.getContext(),bookArrayList);
                    homeadapter.notifyItemInserted(position);
                }
            }
    });

    private final ActivityResultLauncher<Intent> updateLauncher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult()
            ,result ->{
                if(null!=result){
                    if(result.getResultCode()==EditActivity.RESULT_CODE_SUCCESS){
                    Intent intent=result.getData();
                    Bundle bundle=intent.getExtras();
                    String title=bundle.getString("title");
                    String author=bundle.getString("author");
                    String publisher=bundle.getString("publisher");
                    int position=bundle.getInt("position");
                    bookArrayList.get(position).setTitle(title);
                    bookArrayList.get(position).setAuthor(author);
                    bookArrayList.get(position).setPublisher(publisher);
                    new DataProcess().save(this.getContext(),bookArrayList);
                    homeadapter.notifyItemChanged(position);
                }
            }
    });

    public HomeFragment() {}
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {}
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null){
            rootView = inflater.inflate((R.layout.fragment_home),container, false);
        }
        recyclerview = rootView.findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        DataProcess dataprocess=new DataProcess();
        bookArrayList=dataprocess.load(this.getContext());
        Book book_0 = new Book("样书","样书作者","样书出版社",R.drawable.book);
        if (bookArrayList.size()==0){bookArrayList.add(book_0);}
        homeadapter = new HomeAdapter(getActivity(), bookArrayList);
        recyclerview.setAdapter(homeadapter);
        return  rootView;
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case MENU_ID_ADD:
                Intent intent=new Intent(HomeFragment.this.getContext(), EditActivity.class);
                intent.putExtra("position",item.getOrder());
                addLauncher.launch(intent);
                break;
            case MENU_ID_UPDATE:
                Intent intent_e=new Intent(HomeFragment.this.getContext(), EditActivity.class);
                intent_e.putExtra("position",item.getOrder());
                intent_e.putExtra("title",bookArrayList.get(item.getOrder()).getTitle());
                intent_e.putExtra("author",bookArrayList.get(item.getOrder()).getAuthor());
                intent_e.putExtra("publisher",bookArrayList.get(item.getOrder()).getPublisher());
                updateLauncher.launch(intent_e);
                break;
            case MENU_ID_DELETE:
                AlertDialog alertDialog=new AlertDialog.Builder(this.getContext())
                        .setTitle(R.string.delete_confirm)
                        .setNegativeButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                bookArrayList.remove(item.getOrder());
                                new DataProcess().save(HomeFragment.this.getContext(),bookArrayList);
                                homeadapter.notifyItemRemoved(item.getOrder());
                            }
                        }).setPositiveButton(R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {}
                        }).create();
                alertDialog.show();
                break;
        }
        return super.onContextItemSelected(item);
    }
}
