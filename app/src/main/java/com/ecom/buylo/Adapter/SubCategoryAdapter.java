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
import com.ecom.buylo.Activity.LowCategoryActivity;
import com.ecom.buylo.Model.response.Subcat;
import com.ecom.buylo.R;
import com.ecom.buylo.Activity.SubCategoryActivity;
import com.ecom.buylo.SubCategoryUploadFragment;

import java.util.List;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.ViewHolder>{
    Context context;
    CatalogActivity catalogActivity;
    SubCategoryActivity subcategoryActivity;
    SubCategoryUploadFragment subcategoryUploadFragment;
    List<Subcat> data;

    public SubCategoryAdapter (Context context, CatalogActivity catalogActivity,SubCategoryActivity subcategoryActivity,SubCategoryUploadFragment subcategoryUploadFragment,List<Subcat> data){
        this.context=context;
        this.catalogActivity=catalogActivity;
        this.subcategoryActivity=subcategoryActivity;
        this.subcategoryUploadFragment=subcategoryUploadFragment;
        this.data=data;
    }



    @NonNull
    @Override
    public SubCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_area_layout,parent,false);
        return new SubCategoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubCategoryAdapter.ViewHolder holder, int position) {
        Subcat district = data.get(holder.getAdapterPosition());
        holder.txtDistrictName.setText(district.getSname());
        holder.districtLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (subcategoryActivity != null) {
                    subcategoryActivity.districtId = String.valueOf(district.getSid());
                    subcategoryActivity.SubCategoryName = district.getSname();
                    filterList(data, district.getSid());;
                }

            }
        });

        holder.districtLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), LowCategoryActivity.class);
                intent.putExtra("category_id",district.getSid());
                intent.putExtra("category_name",district.getSname());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                Log.e("idd", String.valueOf(district.getSid()));

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtDistrictName;
        LinearLayout districtLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtDistrictName = itemView.findViewById(R.id.txtAreaName);
            districtLayout = itemView.findViewById(R.id.areaLayout);

        }
    }

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