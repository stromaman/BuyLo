package com.ecom.buylo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ecom.buylo.Activity.RentActivity;
import com.ecom.buylo.Fragment.HomeMainFragment;
import com.ecom.buylo.Model.Category;
import com.ecom.buylo.Model.RentCategory;
import com.ecom.buylo.R;

import java.util.List;

public class CategoryRentAdapter extends RecyclerView.Adapter<CategoryRentAdapter.ViewHolder>{
    Context context;
    List<RentCategory> catRes;
    RentActivity rentActivity;

    public CategoryRentAdapter(Context context, RentActivity rentActivity, List<RentCategory> catRes) {
        this.context = context;
        this.catRes =catRes;
        this.rentActivity=rentActivity;
    }
    @NonNull
    @Override
    public CategoryRentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_rent_category_layout,parent,false);
        return new CategoryRentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRentAdapter.ViewHolder holder, int position) {
        RentCategory resp = catRes.get(holder.getAdapterPosition());
        holder.txtCategoryName.setText(resp.getName());
        holder.imgCategory.setImageResource(resp.getImage());
        holder.rentCharge.setText("₹" +resp.getMoney());

    }

    @Override
    public int getItemCount() {
        return catRes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout categoryLayout;
        ImageView imgCategory;
        TextView txtCategoryName;
        TextView rentCharge;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryLayout = itemView.findViewById(R.id.categoryLayout);
            imgCategory=itemView.findViewById(R.id.imgCategory);
            txtCategoryName=itemView.findViewById(R.id.txtCategoryName);
            rentCharge=itemView.findViewById(R.id.rentCharge);
        }
    }
}
