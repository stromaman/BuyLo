package com.ecom.buylo.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ecom.buylo.Model.SubCategory;
import com.ecom.buylo.R;

import java.util.List;

public class SubCategoryAdapterr extends RecyclerView.Adapter<SubCategoryAdapterr.SubCategoryViewHolder> {
    private List<SubCategory> subCategoryList;

    public SubCategoryAdapterr(List<SubCategory> subCategoryList) {
        this.subCategoryList = subCategoryList;
    }

    public static class SubCategoryViewHolder extends RecyclerView.ViewHolder {
        public TextView subCategoryNameTextView;

        public SubCategoryViewHolder(View itemView) {
            super(itemView);
            subCategoryNameTextView = itemView.findViewById(R.id.subCategoryNameTextView);
        }
    }

    @NonNull
    @Override
    public SubCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subcategory, parent, false);
        return new SubCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubCategoryViewHolder holder, int position) {
        SubCategory subCategory = subCategoryList.get(position);
        holder.subCategoryNameTextView.setText(subCategory.getCategoryName());
    }

    @Override
    public int getItemCount() {
        return subCategoryList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView subCategoryNameTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            subCategoryNameTextView = itemView.findViewById(R.id.subCategoryNameTextView);
        }
    }
}

