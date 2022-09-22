package com.example.myapplication.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import com.example.myapplication.Data.LoginData;
import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity {

    private Button course = findViewById(R.id.bt_enterCourse);
    private Button enter_sign_in = findViewById(R.id.bt_enterSignIn);
    private Button enter_user_center = findViewById(R.id.bt_enterUserCenter);
    private Button time = findViewById(R.id.time);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //点击主界面的用户中心按钮跳转至用户中心界面
        enter_user_center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,EnterUserCenterActivity.class));
            }
        });

        //点击主界面的签到按钮跳转至签到界面
        enter_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,EnterSignInActivity.class));
            }
        });

        //点击主界面的课程按钮跳转至相应的课程界面
        course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LoginData.loginUser.getRoleId() == 0){
                    startActivity(new Intent(MainActivity.this,StudentCourseActivity.class));
                }else if (LoginData.loginUser.getRoleId() == 1){
                    startActivity(new Intent(MainActivity.this,TeacherCourseActivity.class));
                }
            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,TimeActivity.class));
            }
        });
    }
}