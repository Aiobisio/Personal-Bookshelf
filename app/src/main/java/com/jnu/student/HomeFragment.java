package com.jnu.student;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BrowserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    public HomeFragment() {}
    private View rootView;
    private ArrayList<Book> bookArrayList = new ArrayList<>();
    private RecyclerView recyclerview;
    private HomeAdapter homeadapter;
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
        initData();
        return  rootView;
    }
    private void initData() {
        for (int i=0;i<20;i++){
            Book book = new Book("算法导论",R.drawable.book_0);
            book.setTitle("算法导论");
            book.setId(R.drawable.book_0);
            bookArrayList.add(book);
        }
    }
    private void initRecyclerview() {
        recyclerview = (RecyclerView)rootView.findViewById(R.id.recyclerview);
        homeadapter = new HomeAdapter(getActivity(), bookArrayList);
        recyclerview.setAdapter(homeadapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
    }
}
