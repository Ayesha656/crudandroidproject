package com.example.crudbasicproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    public MyAdapter(Context context, ArrayList arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    Context context;
    ArrayList arrayList;

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
     return  new MyAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {

        Products products = (Products) arrayList.get(position);
        holder.pname.setText(products.getProductname());
        holder.pdesc.setText(products.getProductprice());
        holder.pprice.setText(products.getProductdescription());



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView pname,pdesc,pprice;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            pname = itemView.findViewById(R.id.name);
            pdesc = itemView.findViewById(R.id.description);
            pprice = itemView.findViewById(R.id.price);

        }
    }
}
