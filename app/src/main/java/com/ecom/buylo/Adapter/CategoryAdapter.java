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
import com.ecom.buylo.Fragment.CategoryFragment;
import com.ecom.buylo.Model.Category;
import com.ecom.buylo.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>{
    Context context;
    List<Category> catRes;
    CategoryFragment dashboardActivity;
    RentActivity rentActivity;

    public CategoryAdapter(Context context, CategoryFragment dashboardActivity,RentActivity rentActivity, List<Category> catRes) {
        this.context = context;
        this.catRes =catRes;
        this.dashboardActivity=dashboardActivity;
        this.rentActivity=rentActivity;
    }


    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_custom_layout,parent,false);
        return new CategoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        Category resp = catRes.get(holder.getAdapterPosition());
        holder.txtCategoryName.setText(resp.getName());
        holder.imgCategory.setImageResource(resp.getImage());
//        if(!resp.getImagePath().equals("")){
//            Glide.with(context)
//                    .load(Constant.IMAGE_URL+resp.getImagePath())
//                    .into(holder.imgCategory);
//        }else{
//            Glide.with(context)
//                    .load(R.drawable.noimg)
//                    .into(holder.imgCategory);
//        }


    }

    @Override
    public int getItemCount() {
        return catRes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout categoryLayout;
        ImageView imgCategory;
        TextView txtCategoryName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryLayout = itemView.findViewById(R.id.categoryLayout);
            imgCategory=itemView.findViewById(R.id.imgCategory);
            txtCategoryName=itemView.findViewById(R.id.txtCategoryName);
        }
    }
}
