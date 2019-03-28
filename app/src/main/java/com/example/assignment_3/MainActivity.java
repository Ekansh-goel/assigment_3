package com.example.assignment_3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Spinner spnrblood_group, spnrcourse;
    private Button btnsave;
    private EditText editname, editpssword, editpsswordconfirm, editmobile, editmail;
    String course, bloodgroup;

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

        btnsave = findViewById(R.id.btnsave);
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
//                Toast.makeText(MainActivity.this, adt_bloodgroup.getItem(position).toString(), Toast.LENGTH_SHORT).show();
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
        btnsave.setOnClickListener(new View.OnClickListener() {
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
                    startActivity(intent);
                }

            }
        });

    }
}
