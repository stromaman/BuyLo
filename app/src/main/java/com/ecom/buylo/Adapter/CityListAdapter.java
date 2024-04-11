package com.ecom.buylo.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ecom.buylo.Activity.SellerHubActivity;
import com.ecom.buylo.Model.response.City;
import com.ecom.buylo.Model.response.District;
import com.ecom.buylo.R;

import java.util.List;

public class CityListAdapter extends RecyclerView.Adapter<CityListAdapter.ViewHolder> {

    Context context;
    SellerHubActivity sellerHubActivity;
    List<City> districtList;

    public CityListAdapter(Context context, SellerHubActivity sellerHubActivity, List<City> districtList) {
        this.context = context;
        this.sellerHubActivity=sellerHubActivity;
        this.districtList = districtList;
    }

    /*public DistrictListAdapter(Context context, CheckoutActivity checkoutActivity, JoinSellerActivity joinSellerActivity, DirectBuyActivity directBuyActivity, List<District> districtList) {
        this.context = context;
        this.checkoutActivity = checkoutActivity;
        this.joinSellerActivity = joinSellerActivity;
        this.directBuyActivity = directBuyActivity;
        this.districtList = districtList;
    }*/

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_area_layout1,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        City district = districtList.get(holder.getAdapterPosition());
        holder.txtDistrictName.setText(district.getName());
        holder.districtLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sellerHubActivity!=null){
                    sellerHubActivity.cityId = String.valueOf(district.getId());
                    sellerHubActivity.txtCity.setText(district.getName());
                    sellerHubActivity.selectDistricDialog.dismiss();
                    Log.e("DISTRICT ID",sellerHubActivity.cityId);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return districtList.size();
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

    public void filterLis(List<City> districFilteredList) {
        districtList = districFilteredList;
        notifyDataSetChanged();
    }

}


