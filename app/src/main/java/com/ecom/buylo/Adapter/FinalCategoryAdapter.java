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
import com.ecom.buylo.Activity.FinalCategoryActivity;
import com.ecom.buylo.Model.response.FinalCategoryy;
import com.ecom.buylo.R;

import java.util.List;

public class FinalCategoryAdapter extends RecyclerView.Adapter<FinalCategoryAdapter.ViewHolder> {
    Context context;
    List<FinalCategoryy> data;
    CatalogActivity catalogActivity;
    FinalCategoryActivity categoryActivity;

    public FinalCategoryAdapter(Context context,CatalogActivity catalogActivity,FinalCategoryActivity categoryActivity,List<FinalCategoryy>data){
        this.context=context;
        this.catalogActivity=catalogActivity;
        this.categoryActivity=categoryActivity;
        this.data=data;
    }


    @NonNull
    @Override
    public FinalCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_area_layout,parent,false);
        return new FinalCategoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FinalCategoryAdapter.ViewHolder holder, int position) {
        FinalCategoryy district = data.get(holder.getAdapterPosition());
        holder.txtDistrictName.setText(district.getSSSname());
//        holder.LowcatLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (catalogActivity != null) {
//                    catalogActivity.finalId = String.valueOf(district.getSSSid());
//                    Log.e("finalidd", String.valueOf(district.getSSSid()));
//
//                }
//            }
//        });

        holder.LowcatLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), CatalogActivity.class);
                intent.putExtra("product_name", district.getConcatenatedName());
                intent.putExtra("product_id", district.getSSSid());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                Log.e("idd", String.valueOf(district.getConcatenatedName()));
                Log.e("finalid", String.valueOf(district.getSSSid()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtDistrictName;
        LinearLayout LowcatLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtDistrictName = itemView.findViewById(R.id.txtAreaName);
            LowcatLayout = itemView.findViewById(R.id.areaLayout);

        }
    }

    public void filterList(List<FinalCategoryy> districtFilteredList) {
        data = districtFilteredList;
        notifyDataSetChanged();
    }
}
