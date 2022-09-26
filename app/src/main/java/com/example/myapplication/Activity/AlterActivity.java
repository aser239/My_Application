package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Data.LoginData;
import com.example.myapplication.Data.MsgData;
import com.example.myapplication.Interface.Api;
import com.example.myapplication.R;

public class AlterActivity extends AppCompatActivity {
    public static final String MESSAGE_STRING = "com.example.myapplication.Activity.ALTER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alter);

        init();
    }

    private void init() {
        ImageView Alter_backward = findViewById(R.id.iv_backward_alter);
        Alter_backward.setOnClickListener(v -> {
            Intent intent = new Intent(AlterActivity.this, PersonInfoActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            //intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
            finish();
        });

        TextView tv_title_alter = findViewById(R.id.tv_title_alter);
        TextView tv_title_alter_text = findViewById(R.id.tv_title_alter_text);
        EditText et_title_alter_text = findViewById(R.id.et_title_alter_text);
        Intent intent = getIntent();
        String info = intent.getStringExtra(PersonInfoActivity.MESSAGE_STRING);
        Button login_preservation = findViewById(R.id.login_preservation);
        if (info != null) {
            tv_title_alter.setText(info);
            tv_title_alter_text.setText(info);
            et_title_alter_text.setHint("请输入" + info);
            login_preservation.setOnClickListener(v -> {
                String newData = et_title_alter_text.getText().toString();
                if (newData.equals("输入不能为空！")) {
                    Toast.makeText(AlterActivity.this, "修改成功！", Toast.LENGTH_SHORT).show();
                } else {
                    LoadData(info, newData);
                    try {
                        Thread.sleep(750);
                        if (MsgData.alterMsgData.getCode() == 200) {
                            System.out.println("hello");
                            /*
                            Intent typeIntent = new Intent(AlterActivity.this,
                                    PersonInfoActivity.class);
                            typeIntent.putExtra(MESSAGE_STRING, info);
                            Intent dataIntent = new Intent(AlterActivity.this,
                                    PersonInfoActivity.class);
                            dataIntent.putExtra(MESSAGE_STRING, newData);*/
                            System.out.println(LoginData.loginUser.getRealName());
                            LoginData.loginUser.setRealName(newData);
                            System.out.println(LoginData.loginUser.getRealName());
                            System.out.println("hello");
                        }
                        Toast.makeText(AlterActivity.this, "修改成功！", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private void LoadData(String type, String data) {
        String collegeName = LoginData.loginUser.getCollegeName();
        String realName = LoginData.loginUser.getRealName();
        boolean gender = LoginData.loginUser.getGender();
        String phone = LoginData.loginUser.getPhone();
        String avatar = LoginData.loginUser.getAvatar();
        int id = LoginData.loginUser.getId();
        int idNumber = LoginData.loginUser.getIdNumber();
        String userName = LoginData.loginUser.getUsername();
        String email = LoginData.loginUser.getEmail();
        int inSchoolTime = LoginData.loginUser.getInSchoolTime();
        switch (type) {
            case "院校":
                collegeName = data;
                break;
            case "姓名":
                realName = data;
                break;
            case "性别":
                if (data.equals("男")) {
                    gender = true;
                } else if (data.equals("女")) {
                    gender = false;
                }
                break;
            case "手机号":
                phone = data;
                break;
            case "头像":
                avatar = data;
                break;
            case "学号":
                idNumber = Integer.parseInt(data);
                break;
            case "邮箱":
                email = data;
                break;
            case "入校时间":
                inSchoolTime = Integer.parseInt(data);
                break;
        }
        Api.AlterUserInfo(collegeName, realName, gender, phone,
                avatar, id, idNumber, userName, email, inSchoolTime);
    }
}