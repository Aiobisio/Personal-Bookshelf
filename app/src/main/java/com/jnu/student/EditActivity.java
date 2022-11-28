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
        EditText editTextTitle=findViewById(R.id.book_edittext);
        position=this.getIntent().getIntExtra("position",0);
        String title=this.getIntent().getStringExtra("title");
        if(null!=title) {editTextTitle.setText(title);}
        Button btna=findViewById(R.id.edit_btn_apply);
        Button btnc=findViewById((R.id.edit_btn_cancel));
        btna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                Bundle bundle=new Bundle();
                bundle.putString("title",editTextTitle.getText().toString());
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