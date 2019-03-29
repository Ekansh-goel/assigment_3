package com.example.assignment_3;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Spinner spnrblood_group, spnrcourse;
    private Button btnsavedata, btnloaddata;
    private EditText editname, editpssword, editpsswordconfirm, editmobile, editmail;
    String course, bloodgroup;
    MyDataBaseHelperDemo myDataBaseHelperDemo;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editname = findViewById(R.id.edtname);
        editpssword = findViewById(R.id.edtpassword);
        editpsswordconfirm = findViewById(R.id.edtpasswordconfirm);
        editmobile = findViewById(R.id.edtmobile);
        editmail = findViewById(R.id.edtmail);
        spnrblood_group = findViewById(R.id.spnrblood_group);
        spnrcourse = findViewById(R.id.spnrcourse);
        btnloaddata = findViewById(R.id.btnloaddta);
        btnsavedata = findViewById(R.id.btnsavedata);
        myDataBaseHelperDemo = new MyDataBaseHelperDemo(this);
        String[] str_course = getResources().getStringArray(R.array.coure);
        String[] strb_boodgroup = getResources().getStringArray(R.array.group);

        final ArrayAdapter adt_bloodgroup = new ArrayAdapter(this, android.R.layout.simple_list_item_1, strb_boodgroup);
        spnrblood_group.setAdapter(adt_bloodgroup);

        final ArrayAdapter adtcourse = new ArrayAdapter(this, android.R.layout.simple_list_item_1, str_course);
        spnrcourse.setAdapter(adtcourse);

        spnrblood_group.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bloodgroup = adt_bloodgroup.getItem(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnrcourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                course = adtcourse.getItem(position).toString();
//                 Toast.makeText(MainActivity.this, adtcourse.getItem(position).toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnsavedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = editname.getText().toString().trim();
                String password = editpssword.getText().toString().trim();
                String passwordconfirm = editpsswordconfirm.getText().toString().trim();
                String mobile = editmobile.getText().toString().trim();
                String mail = editmail.getText().toString().trim();

                // bloodgroup
                //course

                if (!password.equals(passwordconfirm)) {
                    Toast.makeText(MainActivity.this, "ERROR!! recheck your password", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, login_screen.class);
                    intent.putExtra("name_key", name);
                    intent.putExtra("password_key", password);
                    intent.putExtra("mobile_key", mobile);
                    intent.putExtra("mail_key", mail);
                    intent.putExtra("blood_key", bloodgroup);
                    intent.putExtra("course_key", course);


                    db = myDataBaseHelperDemo.getWritableDatabase();
                    ContentValues cv = new ContentValues();
                    cv.put(MyDataBaseHelperDemo.NAME, name);
                    cv.put(MyDataBaseHelperDemo.PASSWORD, password);
                    cv.put(MyDataBaseHelperDemo.COURSE, course);
                    cv.put(MyDataBaseHelperDemo.MOBILE, mobile);
                    cv.put(MyDataBaseHelperDemo.MAIL, mail);
                    cv.put(MyDataBaseHelperDemo.BLOOD, bloodgroup);
                    long d = db.insert(MyDataBaseHelperDemo.TABLE_NAME, null, cv);
                    if (d == -1) {
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Welcome user", Toast.LENGTH_SHORT).show();
                    }

                    startActivity(intent);


                }
                btnloaddata.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        db = myDataBaseHelperDemo.getWritableDatabase();
                        String[] columns = {MyDataBaseHelperDemo.UID, MyDataBaseHelperDemo.NAME, MyDataBaseHelperDemo.PASSWORD};
                        Cursor cursor = db.query(MyDataBaseHelperDemo.TABLE_NAME, columns, null, null, null, null, null, null);
                        StringBuffer buffer = new StringBuffer();
                        while (cursor.moveToNext()) {
                            int index1 = cursor.getColumnIndex((MyDataBaseHelperDemo.UID));
                            int index2 = cursor.getColumnIndex((MyDataBaseHelperDemo.NAME));
                            int index3 = cursor.getColumnIndex((MyDataBaseHelperDemo.PASSWORD));
                            int uid = cursor.getInt(index1);
                            String name1 = cursor.getString(index2);
                            String passs = cursor.getString(index3);
                            buffer.append(uid + "/" + name1 + "/" + passs + "\n");
                        }
                        Toast.makeText(MainActivity.this, buffer.toString(), Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });

    }
}
