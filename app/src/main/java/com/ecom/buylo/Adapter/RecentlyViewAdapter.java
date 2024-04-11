package com.ecom.buylo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ecom.buylo.Fragment.HomeMainFragment;
import com.ecom.buylo.Model.RecentlyView;
import com.ecom.buylo.Activity.ProductShowActivity;
import com.ecom.buylo.R;
import com.ecom.buylo.Activity.RentActivity;

import java.util.List;

public class RecentlyViewAdapter extends RecyclerView.Adapter<RecentlyViewAdapter.ViewHolder>{
    Context context;
    List<RecentlyView> catRes;
    HomeMainFragment dashboardActivity;
    RentActivity rentActivity;

    public RecentlyViewAdapter(Context context, HomeMainFragment dashboardActivity,RentActivity rentActivity, List<RecentlyView> catRes) {
        this.context = context;
        this.catRes =catRes;
        this.dashboardActivity=dashboardActivity;
        this.rentActivity=rentActivity;
    }

    @NonNull
    @Override
    public RecentlyViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_recently_viewed_layout,parent,false);
        return new RecentlyViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentlyViewAdapter.ViewHolder holder, int position) {
        RecentlyView category = catRes.get(holder.getAdapterPosition());
        holder.imgCategory.setImageResource(category.getImage());

        holder.imgCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), ProductShowActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return catRes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCategory;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCategory=itemView.findViewById(R.id.imgCategory);
        }
    }
}
