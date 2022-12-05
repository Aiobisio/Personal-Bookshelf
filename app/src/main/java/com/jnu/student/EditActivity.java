package com.jnu.student;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {
    public static final int RESULT_CODE_SUCCESS = 187;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        EditText editTextTitle=findViewById(R.id.edit_book_title);
        EditText editTextAuthor=findViewById(R.id.edit_book_author);
        EditText editTextPublisher=findViewById(R.id.edit_book_publisher);

        position=this.getIntent().getIntExtra("position",0);
        String title=this.getIntent().getStringExtra("title");
        String author=this.getIntent().getStringExtra("author");
        String publisher=this.getIntent().getStringExtra("publisher");

        if(null!=title) {
            editTextTitle.setText(title);
            editTextAuthor.setText(author);
            editTextPublisher.setText(publisher);
        }
        Button btna=findViewById(R.id.edit_btn_apply);
        Button btnc=findViewById((R.id.edit_btn_cancel));
        btna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                Bundle bundle=new Bundle();
                bundle.putString("title",editTextTitle.getText().toString());
                bundle.putString("author",editTextAuthor.getText().toString());
                bundle.putString("publisher",editTextPublisher.getText().toString());
                bundle.putInt("position",position);
                intent.putExtras(bundle);
                setResult(RESULT_CODE_SUCCESS,intent);
                EditActivity.this.finish();
            }
        });
        btnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditActivity.this.finish();
            }
        });
    }
}