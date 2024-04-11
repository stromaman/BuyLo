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
import com.ecom.buylo.Model.response.State;
import com.ecom.buylo.R;

import java.util.List;

public class StateListAdapter extends RecyclerView.Adapter<StateListAdapter.ViewHolder> {

    Context context;
    SellerHubActivity checkoutActivity;
    List<State> stateList;

    public StateListAdapter(Context context, SellerHubActivity checkoutActivity,List<State> stateList) {
        this.context = context;
        this.checkoutActivity = checkoutActivity;
        this.stateList = stateList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_area_layout1,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        State state = stateList.get(holder.getAdapterPosition());
        holder.txtStateName.setText(state.getName());
        holder.stateLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkoutActivity!=null){
                    checkoutActivity.stateId = String.valueOf(state.getId());
                    checkoutActivity.txtState.setText(state.getName());
                    checkoutActivity.selectStateDialog.dismiss();
                    Log.e("STATE ID",checkoutActivity.stateId);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return stateList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtStateName;
        LinearLayout stateLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtStateName = itemView.findViewById(R.id.txtAreaName);
            stateLayout = itemView.findViewById(R.id.areaLayout);

        }
    }

    public void filterList(List<State> filteredStateList) {
        stateList = filteredStateList;
        notifyDataSetChanged();
    }

}


