package com.example.assignment_3;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class RVvclass extends AppCompatActivity {
    private RecyclerView rv;
    private RecyclerView.LayoutManager layoutManager;
    private SingleRow singleRow;
    private ArrayList<SingleRow> singleRowArrayList;
    MyDataBaseHelperDemo myDataBaseHelperDemo;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rvvclass);

        //findViewByID
        rv = findViewById(R.id.rv);
        layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        myDataBaseHelperDemo = new MyDataBaseHelperDemo(this);

        db = myDataBaseHelperDemo.getWritableDatabase();
        String[] columns = {MyDataBaseHelperDemo.NAME, MyDataBaseHelperDemo.COURSE};
        Cursor cursor = db.query(MyDataBaseHelperDemo.TABLE_NAME, columns, null, null, null, null, null, null);

        singleRowArrayList = new ArrayList<>();

        while (cursor.moveToNext()) {
            int index1 = cursor.getColumnIndex((MyDataBaseHelperDemo.NAME));
            int index3 = cursor.getColumnIndex((MyDataBaseHelperDemo.COURSE));

            String names = cursor.getString(index1);
            String course = cursor.getString(index3);
//            Toast.makeText(this, names+""+course, Toast.LENGTH_SHORT).show();
            singleRow = new SingleRow(names, course);
            singleRowArrayList.add(singleRow);
        }
        RecyclerClick recyclerClick = new RecyclerClick() {
            @Override
            public void onclick(View view, int position) {
                SingleRow singleRow = singleRowArrayList.get(position);
                String namee = singleRow.getName();
                Toast.makeText(RVvclass.this, namee, Toast.LENGTH_SHORT).show();

            }
        };

        MyAdapter myAdapter = new MyAdapter(this, singleRowArrayList, recyclerClick);
        rv.setAdapter(myAdapter);

    }
}