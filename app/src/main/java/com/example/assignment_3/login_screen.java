package com.example.assignment_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class login_screen extends AppCompatActivity {
    String name, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        String password;
        Bundle bundle = getIntent().getExtras();
        bundle.get("name_key");
        bundle.get("password_key");

    }

    public void login(View view) {

    }
}
