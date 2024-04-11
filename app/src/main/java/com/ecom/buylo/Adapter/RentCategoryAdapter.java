package com.ecom.buylo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ecom.buylo.Activity.CatalogActivity;
import com.ecom.buylo.Activity.CategoryActivity;
import com.ecom.buylo.Model.response.Rent;
import com.ecom.buylo.Model.response.Subcat;
import com.ecom.buylo.R;
import com.ecom.buylo.Activity.SubCategoryActivity;

import java.util.List;

public class RentCategoryAdapter extends RecyclerView.Adapter<RentCategoryAdapter.ViewHolder> {

    Context context;
    CatalogActivity catalogActivity;
    CategoryActivity categoryActivity;
    SubCategoryActivity subcategoryActivity;
    List<Rent> stateList;
    List<Subcat> data;


    public RentCategoryAdapter(Context context, CatalogActivity catalogActivity, CategoryActivity categoryActivity,SubCategoryActivity subCategoryActivity, List<Rent> stateList, List<Subcat> data) {
        this.context = context;
        this.catalogActivity = catalogActivity;
        this.categoryActivity = categoryActivity;
        this.subcategoryActivity=subCategoryActivity;
        this.stateList = stateList;
        this.data = data;
    }


    @NonNull
    @Override
    public RentCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_area_layout, parent, false);
        return new RentCategoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RentCategoryAdapter.ViewHolder holder, int position) {
        Rent state = stateList.get(holder.getAdapterPosition());
        holder.txtStateName.setText(state.getCname());
        holder.stateLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 if (categoryActivity != null) {
                    categoryActivity.stateId = String.valueOf(state.getCid());
                    categoryActivity.CategoryName = state.getCname();
                    categoryActivity.txtState.setText(state.getCname());
                    // Filter the second list based on the selected ID
                    filterList(data, state.getCid());
                }
            }
        });

        holder.stateLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), SubCategoryActivity.class);
                intent.putExtra("category_id",state.getCid());
                intent.putExtra("category_name",state.getCname());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                Log.e("idd", String.valueOf(state.getCid()));

            }
        });

    }


    @Override
    public int getItemCount() {
        return stateList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtStateName;
        LinearLayout stateLayout, nextGo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtStateName = itemView.findViewById(R.id.txtAreaName);
            stateLayout = itemView.findViewById(R.id.areaLayout);
            nextGo = itemView.findViewById(R.id.nextGo);

        }
    }

    // Method to filter the second list based on selected ID
    public void filterList(List<Subcat> filteredData, int selectedId) {
        // Clear the existing data
        data.clear();

        // Iterate through the filteredData list to find the matching ID
        for (Subcat subcat : filteredData) {
            if (subcat.getSid() == selectedId) {
                // Found the selected item, now add it to the second list
                data.add(subcat);
            }
        }

        // Notify the adapter that data has changed
        notifyDataSetChanged();
    }
}
