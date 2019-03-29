package com.example.assignment_3;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login_screen extends AppCompatActivity {
    Button btnsave;
    String name, password, course, mobile, mail, blood;
    MyDataBaseHelperDemo myDataBaseHelperDemo;
    SQLiteDatabase db;
    EditText edtname, edtpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        myDataBaseHelperDemo = new MyDataBaseHelperDemo(this);
        edtname = findViewById(R.id.edtname_2);
        edtpassword = findViewById(R.id.edtpass_2);
        btnsave = findViewById(R.id.btnsavedata);

    }

    public void login(View view) {
        name = edtname.getText().toString().trim();
        password = edtpassword.getText().toString().trim();
        db = myDataBaseHelperDemo.getWritableDatabase();

        String[] columns = {MyDataBaseHelperDemo.NAME, MyDataBaseHelperDemo.PASSWORD};

        Cursor cursor = db.query(MyDataBaseHelperDemo.TABLE_NAME, columns, null, null, null, null, null, null);

        Boolean b = false;

        while (cursor.moveToNext()) {
            int index2 = cursor.getColumnIndex((MyDataBaseHelperDemo.NAME));
            int index3 = cursor.getColumnIndex((MyDataBaseHelperDemo.PASSWORD));
            String name1 = cursor.getString(index2);
            String passs = cursor.getString(index3);
            if (name.equals(name1) && password.equals(passs)) {
                Toast.makeText(this, "condiion hit", Toast.LENGTH_SHORT).show();
                b = true;
                break;
            }
        }
        if (b) {
            Toast.makeText(this, "welcome", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(login_screen.this, RVvclass.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "recheck password or username", Toast.LENGTH_SHORT).show();
        }


    }
}
