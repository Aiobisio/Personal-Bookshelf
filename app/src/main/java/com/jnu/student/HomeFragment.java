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
                    int position=bundle.getInt("position");
                    bookArrayList.add(position,new Book(title,R.drawable.book_0));
                    new DataProcess().save(this.getContext(),bookArrayList);
                    homeadapter.notifyItemInserted(0);
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
                    int position=bundle.getInt("position");
                    bookArrayList.get(position).setTitle(title);
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
        initRecyclerview();
        DataProcess dataprocess=new DataProcess();
        dataprocess.save(this.getContext(),bookArrayList);
        initData();
        return  rootView;
    }
    private void initData() {
        Book book = new Book("算法导论",R.drawable.book_0);
        book.setTitle("算法导论");
        book.setId(R.drawable.book_0);
        if (bookArrayList.size()==0){bookArrayList.add(book);}
    }
    private void initRecyclerview() {
        recyclerview = (RecyclerView)rootView.findViewById(R.id.recyclerview);
        homeadapter = new HomeAdapter(getActivity(), bookArrayList);
        recyclerview.setAdapter(homeadapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
    }

    @Override
    public boolean onContextItemSelected(final MenuItem item) {
        switch(item.getItemId()){
            case 1:
                Intent intent=new Intent(HomeFragment.this.getContext(), EditActivity.class);
                intent.putExtra("position",item.getOrder());
                addLauncher.launch(intent);
                break;
            case 2:
                Intent intent_e=new Intent(HomeFragment.this.getContext(), EditActivity.class);
                intent_e.putExtra("position",item.getOrder());
                intent_e.putExtra("title",bookArrayList.get(item.getOrder()).getTitle());
                updateLauncher.launch(intent_e);
                break;
            case 3:
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
