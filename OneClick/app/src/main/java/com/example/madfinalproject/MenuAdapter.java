package com.example.madfinalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder>{
    ArrayList<String> references;
    Context context;
    ArrayList<Menu> menuArrayList;

    public MenuAdapter(Context context, ArrayList<Menu> menuArrayList) {
        this.context = context;
        this.menuArrayList = menuArrayList;
    }

    @NonNull
    @Override
    public MenuAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.menuitem,parent,false);
        return new MenuAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuAdapter.MyViewHolder holder, int position) {
        Menu menu = menuArrayList.get(position);
        holder.name.setText(menu.getName());
        holder.content.setText(menu.getContent());
        holder.price.setText(menu.getPrice());
    }

    @Override
    public int getItemCount() {
        return menuArrayList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name, content, price;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtMenuName);
            content = itemView.findViewById(R.id.txtMenuContent);
            price= itemView.findViewById(R.id.txtMenuPrice);
        }
}}
