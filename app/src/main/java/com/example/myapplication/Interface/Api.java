package com.example.myapplication.Interface;

import android.os.NetworkOnMainThreadException;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.myapplication.Data.CourseData;
import com.example.myapplication.Data.LoginData;
import com.example.myapplication.javaBean.Course;
import com.example.myapplication.javaBean.Person;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Api {
    public static Gson gson = new Gson();
    public static String appId = "c37e25afda404fbe9c401a733639c0df";
    public static String appSecret = "070986397f6c59d8942bab314d8293d68b7a5";
    public static String msg = null;


    public static void enroll(String username, int roleId, String password) {
        new Thread(() -> {
            // url路径
            String url = "http://47.107.52.7:88/member/sign/user/register";

            // 请求头
            Headers headers = new Headers.Builder()
                    .add("Accept", "application/json, text/plain, */*")
                    .add("appId", appId)
                    .add("appSecret", appSecret)
                    .add("Content-Type", "application/json")
                    .build();

            // 请求体
            // PS.用户也可以选择自定义一个实体类，然后使用类似fastjson的工具获取json串
            Map<String, Object> bodyMap = new HashMap<>();
            bodyMap.put("password", password);
            bodyMap.put("roleId", roleId);
            bodyMap.put("userName", username);
            // 将Map转换为字符串类型加入请求体中
            String body = gson.toJson(bodyMap);

            MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

            //请求组合创建
            Request request = new Request.Builder()
                    .url(url)
                    // 将请求头加至请求中
                    .headers(headers)
                    .post(RequestBody.create(MEDIA_TYPE_JSON, body))
                    .build();
            try {
                OkHttpClient client = new OkHttpClient();
                //发起请求，传入callback进行回调
                client.newCall(request).enqueue(ResponseBody.callback);
            } catch (NetworkOnMainThreadException ex) {
                ex.printStackTrace();
            }
        }).start();
    }

    public static void login(String username, String password) {
        new Thread(() -> {
            // url路径
            String url = "http://47.107.52.7:88/member/sign/user/login";

            // 请求头
            Headers headers = new Headers.Builder()
                    .add("Accept", "application/json, text/plain, */*")
                    .add("appId", appId)
                    .add("appSecret", appSecret)
                    .build();

            // 请求体
            // PS.用户也可以选择自定义一个实体类，然后使用类似fastjson的工具获取json串
            FormBody.Builder params = new FormBody.Builder();
            params.add("username", username); //添加url参数
            params.add("password", password); //添加url参数

            //请求组合创建
            Request request = new Request.Builder()
                    .url(url)
                    // 将请求头加至请求中
                    .headers(headers)
                    .post(params.build())
                    .build();
            try {
                OkHttpClient client = new OkHttpClient();
                //发起请求，传入callback进行回调
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        Type jsonType = new TypeToken<ResponseBody<Person>>() {
                        }.getType();
                        // 获取响应体的json串
                        assert response.body() != null;
                        String body = response.body().string();
                        Log.d("info", body);
                        // 解析json串到自己封装的状态
                        ResponseBody<Person> dataResponseBody = gson.fromJson(body, jsonType);
                        Api.msg = dataResponseBody.getMsg();
                        System.out.println(Api.msg);
                        LoginData.loginUser = dataResponseBody.getData();
                        //Log.d("info", dataResponseBody.toString());
                        //Log.d("Person:", LoginData.loginUser.getId());
                        //Log.d("Person:", LoginData.loginUser.getUsername());
                    }
                });
            } catch (NetworkOnMainThreadException ex) {
                ex.printStackTrace();
            }
        }).start();
    }

    public static void AddCourse(String collegeName, String courseName,
                                 String coursePhoto, String introduce, int endTime,
                                 String realName, int startTime) {
        new Thread(() -> {
            // url路径
            String url = "http://47.107.52.7:88/member/sign/course/teacher";

            // 请求头
            Headers headers = new Headers.Builder()
                    .add("Accept", "application/json, text/plain, */*")
                    .add("appId", appId)
                    .add("appSecret", appSecret)
                    .build();

            // 请求体
            // PS.用户也可以选择自定义一个实体类，然后使用类似fastjson的工具获取json串
            Map<String, Object> bodyMap = new HashMap<>();
            bodyMap.put("collegeName", collegeName);
            bodyMap.put("courseName", courseName);
            bodyMap.put("coursePhoto", coursePhoto);
            bodyMap.put("endTime", endTime);
            bodyMap.put("introduce", introduce);
            bodyMap.put("realName", realName);
            bodyMap.put("startTime", startTime);
            bodyMap.put("userId", LoginData.loginUser.getId());
            bodyMap.put("userName", LoginData.loginUser.getUsername());
            // 将Map转换为字符串类型加入请求体中
            String body = gson.toJson(bodyMap);

            MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

            //请求组合创建
            Request request = new Request.Builder()
                    .url(url)
                    // 将请求头加至请求中
                    .headers(headers)
                    .post(RequestBody.create(MEDIA_TYPE_JSON, body))
                    .build();
            try {
                OkHttpClient client = new OkHttpClient();
                //发起请求，传入callback进行回调
                client.newCall(request).enqueue(ResponseBody.callback);
            } catch (NetworkOnMainThreadException ex) {
                ex.printStackTrace();
            }
        }).start();
    }

    public static void GetCourse(int current, int size) {
        new Thread(() -> {
            // url路径
            String url = "http://47.107.52.7:88/member/sign/course/all";

            // 请求头
            Headers headers = new Headers.Builder()
                    .add("Accept", "application/json, text/plain, */*")
                    .add("appId", appId)
                    .add("appSecret", appSecret)
                    .build();

            // 请求体
            // PS.用户也可以选择自定义一个实体类，然后使用类似fastjson的工具获取json串
            Map<String, Object> bodyMap = new HashMap<>();
            bodyMap.put("current", current);
            bodyMap.put("size", size);
            // 将Map转换为字符串类型加入请求体中
            String body = gson.toJson(bodyMap);

            MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

            //请求组合创建
            Request request = new Request.Builder()
                    .url(url)
                    // 将请求头加至请求中
                    .headers(headers)
                    .post(RequestBody.create(MEDIA_TYPE_JSON, body))
                    .build();
            try {
                OkHttpClient client = new OkHttpClient();
                //发起请求，传入callback进行回调
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        Type jsonType = new TypeToken<ResponseBody<Course>>() {
                        }.getType();
                        // 获取响应体的json串
                        assert response.body() != null;
                        String body = response.body().string();
                        Log.d("info", body);
                        // 解析json串到自己封装的状态
                        ResponseBody<Course> dataResponseBody = gson.fromJson(body, jsonType);
                        CourseData.Course = dataResponseBody.getData();
                        Log.d("info", dataResponseBody.toString());
                        Log.d("Course", CourseData.Course.getCourseName());
                    }
                });
            } catch (NetworkOnMainThreadException ex) {
                ex.printStackTrace();
            }
        }).start();
    }

    public static void AlterUserInfo(String collegeName, String realName,
                                     boolean gender, String phone, String avatar,
                                     int id, int idNumber, String userName,
                                     String email, int inSchoolTime) {
        new Thread(() -> {
            // url路径
            String url = "http://47.107.52.7:88/member/sign/user/update";

            // 请求头
            Headers headers = new Headers.Builder()
                    .add("Accept", "application/json, text/plain, */*")
                    .add("appId", appId)
                    .add("appSecret", appSecret)
                    .build();

            // 请求体
            // PS.用户也可以选择自定义一个实体类，然后使用类似fastjson的工具获取json串
            Map<String, Object> bodyMap = new HashMap<>();
            bodyMap.put("collegeName", collegeName);
            bodyMap.put("realName", realName);
            bodyMap.put("gender", gender);
            bodyMap.put("phone", phone);
            bodyMap.put("avatar", avatar);
            bodyMap.put("id", id);
            bodyMap.put("idNumber", idNumber);
            bodyMap.put("userName", userName);
            bodyMap.put("email", email);
            bodyMap.put("inSchoolTime", inSchoolTime);
            // 将Map转换为字符串类型加入请求体中
            String body = gson.toJson(bodyMap);

            MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

            //请求组合创建
            Request request = new Request.Builder()
                    .url(url)
                    // 将请求头加至请求中
                    .headers(headers)
                    .post(RequestBody.create(MEDIA_TYPE_JSON, body))
                    .build();
            try {
                OkHttpClient client = new OkHttpClient();
                //发起请求，传入callback进行回调
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        Type jsonType = new TypeToken<ResponseBody<Person>>() {
                        }.getType();
                        // 获取响应体的json串
                        assert response.body() != null;
                        String body = response.body().string();
                        Log.d("info", body);
                        // 解析json串到自己封装的状态
                        ResponseBody<Person> dataResponseBody = gson.fromJson(body, jsonType);

                        LoginData.Alter = dataResponseBody.getData();
                        Log.d("info", dataResponseBody.toString());
                    }
                });
            } catch (NetworkOnMainThreadException ex) {
                ex.printStackTrace();
            }
        }).start();
    }
}
