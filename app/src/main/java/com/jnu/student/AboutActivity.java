package com.jnu.student;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class AboutActivity extends AppCompatActivity {
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initViews();
        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setImage(R.drawable.about_show)
                .setDescription("这是一款个人电子书架软件，希望您能喜欢")
                .addItem(new Element().setTitle("Version 1.0."))
                .addGroup("联系作者")
                .addEmail("2512288019@qq.com")
                .addGitHub("Aiobisio")
                .create();
        relativeLayout.addView(aboutPage);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }

    private void initViews(){
        relativeLayout= (RelativeLayout) findViewById(R.id.relativeLayout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}