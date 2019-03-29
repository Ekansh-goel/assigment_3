package com.example.assignment_3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements RecyclerClick {
    private Context context;
    private ArrayList<SingleRow> singleRowArrayList;
    RecyclerClick recyclerClick;

    public MyAdapter(Context context, ArrayList<SingleRow> singleRowArrayList, RecyclerClick recyclerClick) {
        this.context = context;
        this.singleRowArrayList = singleRowArrayList;
        this.recyclerClick = recyclerClick;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int pos) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_row, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view, recyclerClick);

        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int pos) {
        //Getting data from data source
        SingleRow s = singleRowArrayList.get(pos);
        String name = s.getName();
        String course = s.getCourse();

        //Loading the views with data
        myViewHolder.txtName.setText(name);
        myViewHolder.txtcourse.setText(course);


    }

    @Override
    public int getItemCount() {
        return singleRowArrayList.size();
    }

    @Override
    public void onclick(View view, int position) {

    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtName, txtcourse;
        RecyclerClick recyclerClick;

        public MyViewHolder(@NonNull View itemView, RecyclerClick recyclerClick) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtuser_name);
            txtcourse = itemView.findViewById(R.id.txtuser_course);
            this.recyclerClick = recyclerClick;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            recyclerClick.onclick(v, getAdapterPosition());
        }
    }

}
