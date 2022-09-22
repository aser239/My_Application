package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;

public class EnterSignInActivity extends AppCompatActivity {

    private Button student_sign_in = findViewById(R.id.enterStudentSignIn);
    private Button teacher_sign_in = findViewById(R.id.enterTeacherSignIn);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_sign_in);

        student_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EnterSignInActivity.this,SignInActivity.class));
            }
        });

        teacher_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EnterSignInActivity.this,TeacherSignInActivity.class));
            }
        });

    }
}