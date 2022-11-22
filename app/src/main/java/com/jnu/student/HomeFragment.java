package com.jnu.student;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
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
    //private TextView textview;
    //private Button button;
    private ArrayList<Book> bookArrayList = new ArrayList<>();
    private RecyclerView recyclerview;
    private HomeAdapter homeadapter;
    public static BrowserFragment newInstance() {
        BrowserFragment fragment = new BrowserFragment();
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
//        textview = root.findViewById(R.id.tv);
//        button = root.findViewById(R.id.but);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                textview.setText("修改");
//            }
//        });
        return  rootView;
    }
    private void initData() {
        for (int i=0;i<30;i++){
            Book bean = new Book();
            bean.setId("ID："+i);
            bookArrayList.add(bean);
        }
    }
    private void initRecyclerview() {
        recyclerview = (RecyclerView)rootView.findViewById(R.id.recyclerview);
        homeadapter = new HomeAdapter(getActivity(), bookArrayList);
        recyclerview.setAdapter(homeadapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
    }
}