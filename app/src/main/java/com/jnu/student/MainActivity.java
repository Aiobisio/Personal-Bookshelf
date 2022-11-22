package com.jnu.student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    LinearLayout homeLinear;
    LinearLayout newsLinear;
    LinearLayout settingsLinear;
    LinearLayout aboutLinear;
    HomeFragment fragmentHome;
    BrowserFragment fragmentBrowser;
    SettingsActivity.SettingsFragment fragmentSettings;
    AboutFragment fragmentAbout;
    private FragmentManager mfManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homeLinear= findViewById(R.id.home);
        newsLinear= findViewById(R.id.news);
        settingsLinear= findViewById(R.id.settings);
        aboutLinear= findViewById(R.id.about);
        homeLinear.setOnClickListener(this);
        newsLinear.setOnClickListener(this);
        settingsLinear.setOnClickListener(this);
        aboutLinear.setOnClickListener(this);
        mfManager = getSupportFragmentManager();
        homeLinear.performClick(); //模拟手动点触触发Home页面
        //关于屏幕休眠管理的Flag设置
        Context screen_context = this;
        SharedPreferences prefs = screen_context.getSharedPreferences
                ("com.jnu.student_preferences", Context.MODE_PRIVATE);
        Boolean screen = prefs.getBoolean("attachment",false);
        if(screen==true){
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        } else {getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);}
    }

    @Override
    public void onClick(View view) {
        FragmentTransaction fragmentTransaction = mfManager.beginTransaction();
        hideAllFragment(fragmentTransaction);
        switch (view.getId()){
            case R.id.home:
                setAllFalse();
                homeLinear.setSelected(true);
                if (fragmentHome==null){
                    fragmentHome=new HomeFragment();
                    fragmentTransaction.add(R.id.fragment_frame,fragmentHome);
                }else{
                    fragmentTransaction.show(fragmentHome);
                }
                break;
            case R.id.news:
                setAllFalse();
                newsLinear.setSelected(true);
                if(fragmentBrowser==null){
                    fragmentBrowser=new BrowserFragment();
                    fragmentTransaction.add(R.id.fragment_frame,fragmentBrowser);
                }else {
                    fragmentTransaction.show(fragmentBrowser);
                }
                break;
            case R.id.settings:
                setAllFalse();
                settingsLinear.setSelected(true);
                if(fragmentSettings ==null){
                    fragmentSettings =new SettingsActivity.SettingsFragment();
                    fragmentTransaction.add(R.id.fragment_frame, fragmentSettings);
                }else {
                    fragmentTransaction.show(fragmentSettings);
                }
                break;
            case R.id.about:
                setAllFalse();
                aboutLinear.setSelected(true);
                if(fragmentAbout==null){
                    fragmentAbout=new AboutFragment();
                    fragmentTransaction.add(R.id.fragment_frame,fragmentAbout);
                }else {
                    fragmentTransaction.show(fragmentAbout);
                }
                break;
        }
        fragmentTransaction.commit();
    }
    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if(fragmentHome!=null){
            fragmentTransaction.hide(fragmentHome);
        }
        if(fragmentBrowser!=null){
            fragmentTransaction.hide(fragmentBrowser);
        }
        if(fragmentSettings !=null){
            fragmentTransaction.hide(fragmentSettings);
        }
        if(fragmentAbout!=null){
            fragmentTransaction.hide(fragmentAbout);
        }

    }
    private void setAllFalse() {
        homeLinear.setSelected(false);
        newsLinear.setSelected(false);
        settingsLinear.setSelected(false);
        aboutLinear.setSelected(false);
    }
}