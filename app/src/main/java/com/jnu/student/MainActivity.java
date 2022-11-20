package com.jnu.student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
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
        homeLinear= (LinearLayout) findViewById(R.id.home);
        newsLinear= (LinearLayout) findViewById(R.id.news);
        settingsLinear= (LinearLayout) findViewById(R.id.settings);
        aboutLinear= (LinearLayout) findViewById(R.id.about);
        homeLinear.setOnClickListener(this);
        newsLinear.setOnClickListener(this);
        settingsLinear.setOnClickListener(this);
        aboutLinear.setOnClickListener(this);
        mfManager = getSupportFragmentManager();
        homeLinear.performClick(); //模拟手动点触触发Home页面
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