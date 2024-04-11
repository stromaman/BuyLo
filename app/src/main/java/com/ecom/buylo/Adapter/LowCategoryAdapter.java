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
import com.ecom.buylo.Activity.LowCategoryActivity;
import com.ecom.buylo.Model.response.LowCategory;
import com.ecom.buylo.R;

import java.util.List;

public class LowCategoryAdapter extends RecyclerView.Adapter<LowCategoryAdapter.ViewHolder> {
    Context context;
    CatalogActivity catalogActivity;
    LowCategoryActivity categoryActivity;
    List<LowCategory> data;

    public LowCategoryAdapter(Context context,CatalogActivity catalogActivity,LowCategoryActivity categoryActivity,List<LowCategory>data){
        this.context=context;
        this.catalogActivity=catalogActivity;
        this.categoryActivity=categoryActivity;
        this.data=data;
    }



    @NonNull
    @Override
    public LowCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_area_layout,parent,false);
        return new LowCategoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LowCategoryAdapter.ViewHolder holder, int position) {
        LowCategory district = data.get(holder.getAdapterPosition());
        holder.txtDistrictName.setText(district.getSScName());
        holder.LowcatLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (catalogActivity != null) {
                    catalogActivity.productId = String.valueOf(district.getSScid());
                    catalogActivity.SubCategoryName = district.getSScName();
                    catalogActivity.txtDistrict.setText(district.getSScName());
                    catalogActivity.selectDistrictDialog.dismiss();
                    Log.e("SubCategory", catalogActivity.districtId);
                    Log.e("SubCategory", catalogActivity.SubCategoryName);
                }
                else if (categoryActivity != null) {
                    categoryActivity.productId = String.valueOf(district.getSScid());
                    categoryActivity.SubCategoryName = district.getSScName();
                  //  categoryActivity.txtDistric.setText(district.getSScName());
                 //   categoryActivity.selectDistrictDialog.dismiss();
                    Log.e("SubCategory", categoryActivity.districtId);
                    Log.e("SubCategory", categoryActivity.SubCategoryName);
                }
            }
        });

        holder.LowcatLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), FinalCategoryActivity.class);
                intent.putExtra("category_id",district.getSScid());
                intent.putExtra("category_name",district.getSScName());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                Log.e("idd", String.valueOf(district.getSScid()));

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

    public void filterList(List<LowCategory> districtFilteredList) {
        data = districtFilteredList;
        notifyDataSetChanged();
    }
}
