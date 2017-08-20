package com.example.android.intern;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by HAPPY on 09/04/2017.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Upload> uploads;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView email, flatno, bill;

        public MyViewHolder(View view) {
            super(view);
            email = (TextView) view.findViewById(R.id.email);
            flatno = (TextView) view.findViewById(R.id.flatno);
            bill = (TextView) view.findViewById(R.id.bill);
        }
    }


    public MyAdapter(List<Upload> uploads) {
        this.uploads = uploads;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_electricity, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Upload upload = uploads.get(position);
        holder.email.setText(upload.getEmail());
        holder.flatno.setText(upload.getFlatno());
        holder.bill.setText(upload.getBill());
    }

    @Override
    public int getItemCount() {
        return uploads.size();
    }
}
