package com.ecom.buylo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ecom.buylo.Model.SCategory;
import com.ecom.buylo.R;

import java.util.List;

public class SCategoryAdapter extends RecyclerView.Adapter<SCategoryAdapter.ViewHolder> {
    private List<SCategory> categoryList;
    private Context context;

    public SCategoryAdapter(Context context, List<SCategory> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }
    @NonNull
    @Override
    public SCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new SCategoryAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull SCategoryAdapter.ViewHolder holder, int position) {
        SCategory category = categoryList.get(position);
        holder.categoryName.setText(category.getCategoryName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle category selection here
                Toast.makeText(context, "Selected category: " + category.getCategoryName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.txtCategoryName);
        }
    }
}

