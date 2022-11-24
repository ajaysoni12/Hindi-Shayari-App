package com.devdroid.hindishayariapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devdroid.hindishayariapp.AllShayariActivity;
import com.devdroid.hindishayariapp.MainActivity;
import com.devdroid.hindishayariapp.Model.CategoryModal;
import com.devdroid.hindishayariapp.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    Context context;
    ArrayList<CategoryModal> list;

    String[] colorList = {"#f0932b", "#6ab04c", "#c7ecee", "#be2edd", "#7ed6df", "#f9ca24"};

    public CategoryAdapter(Context context, ArrayList<CategoryModal> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.txtCategoryName.setBackgroundColor(Color.parseColor(colorList[position % 6]));

        holder.txtCategoryName.setText(list.get(position).getName().toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AllShayariActivity.class);
                intent.putExtra("id", list.get(position).getId());
                intent.putExtra("name", list.get(position).getName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtCategoryName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtCategoryName = itemView.findViewById(R.id.txtCategoryName);
        }
    }
}
