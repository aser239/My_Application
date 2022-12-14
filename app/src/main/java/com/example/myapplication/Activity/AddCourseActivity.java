package com.example.myapplication.Activity;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;

import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.myapplication.Interface.Api;
import com.example.myapplication.R;

import java.util.Locale;

@RequiresApi(api = Build.VERSION_CODES.N)
public class AddCourseActivity extends AppCompatActivity implements View.OnClickListener{

    // private TextView txtTime;
    private Button btnDate1;
    private Button btnDate2;
    private Button btnAdd;
    public static EditText etCollegeName;
    public static EditText etCourseName;
    public static EditText etCoursePhoto;
    public static EditText etIntroduce;
    public static EditText etEndTime;
    public static EditText etRealName;
    public static EditText etStartTime;

    Calendar calendar= Calendar.getInstance(Locale.CHINA);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        btnDate1 = findViewById(R.id.bt_fiTime);
        btnDate2 = findViewById(R.id.bt_stTime);
        btnAdd = findViewById(R.id.confirmAddCourse);

        //txtDate= findViewById(R.id.txtDate);
        etEndTime = findViewById(R.id.et_fiTime);
        etStartTime = findViewById(R.id.et_stTime);
        etCollegeName = findViewById(R.id.et_collegeName);
        etCourseName =  findViewById(R.id.et_courseName);
        etIntroduce = findViewById(R.id.et_introduce);
        etRealName = findViewById(R.id.et_realName);
        etCoursePhoto = findViewById(R.id.et_photo);
        btnDate1.setOnClickListener(this);
        btnDate2.setOnClickListener(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // etCoursePhoto.setText("https://guet-lab.oss-cn-hangzhou.aliyuncs.com/api/2022/09/22/777f78b9-4b7d-401f-986a-7bc61f903201.jpg");
                int endTime = Integer.parseInt(etEndTime.getText().toString());
                int startTime = Integer.parseInt(etStartTime.getText().toString());
                String CoursePhoto = etCoursePhoto.getText().toString();
                String CollegeName = etCollegeName.getText().toString();
                String CourseName = etCourseName.getText().toString();
                String Introduce = etIntroduce.getText().toString();
                String RealName = etRealName.getText().toString();


                Api.AddCourse(CollegeName,CourseName, CoursePhoto,Introduce,endTime,RealName,startTime);

            }
        });

    }

    /**
     * ????????????
     * @param activity
     * @param themeResId
     * @param Time
     * @param calendar
     */
    public static void showDatePickerDialog(Activity activity, int themeResId, final EditText Time, Calendar calendar) {
        // ??????????????????DatePickerDialog???????????????????????????????????????
        new DatePickerDialog(activity, themeResId, new DatePickerDialog.OnDateSetListener() {
            // ???????????????(How the parent is notified that the date is set.)
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // ????????????????????????????????????????????????????????????
                Time.setText(year+""+ (monthOfYear+ 1)+""+ ""+dayOfMonth);
            }
        }
                // ??????????????????
                , calendar.get(Calendar.YEAR)
                , calendar.get(Calendar.MONTH)
                , calendar.get(Calendar.DAY_OF_MONTH)).show();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_fiTime:
                showDatePickerDialog(this,  4, etEndTime, calendar);;
                break;
            case R.id.bt_stTime:
                showDatePickerDialog(this,4, etStartTime, calendar);
            default:
                break;
        }

    }

}