package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.R;

public class AlterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alter);

        init();
    }

    private void init() {
        TextView tv_title_alter = findViewById(R.id.tv_title_alter);
        TextView tv_title_alter_text = findViewById(R.id.tv_title_alter_text);
        Intent intent = getIntent();
        String info = intent.getStringExtra(PersonInfoActivity.MESSAGE_STRING);
        if (info != null) {
                tv_title_alter.setText(info);
                tv_title_alter_text.setText(info);
        }
    }
}