package com.ecom.buylo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ecom.buylo.Fragment.HomeMainFragment;
import com.ecom.buylo.Model.Season;
import com.ecom.buylo.R;
import com.ecom.buylo.Activity.RentActivity;

import java.util.List;

public class SeasonListAdapter  extends RecyclerView.Adapter<SeasonListAdapter.ViewHolder>{
    Context context;
    List<Season> catRes;
    HomeMainFragment dashboardActivity;
    RentActivity rentActivity;

    public SeasonListAdapter(Context context, HomeMainFragment dashboardActivity,RentActivity rentActivity, List<Season> catRes) {
        this.context = context;
        this.catRes =catRes;
        this.dashboardActivity=dashboardActivity;
        this.rentActivity=rentActivity;
    }

    @NonNull
    @Override
    public SeasonListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_season_layout,parent,false);
        return new SeasonListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeasonListAdapter.ViewHolder holder, int position) {
        Season category = catRes.get(holder.getAdapterPosition());
        holder.imgCategory.setImageResource(category.getImage());
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
