package com.example.assignment_3;

import android.content.Context;

public class SingleRow {
    String name;
    String course;

    public SingleRow(String names, String course) {
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public SingleRow(String name, String course, Context context) {
        this.name = name;
        this.course = course;
    }

}
