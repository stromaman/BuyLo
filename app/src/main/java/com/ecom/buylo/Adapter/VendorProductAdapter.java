package com.ecom.buylo.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ecom.buylo.Activity.MyShopActivity;
import com.ecom.buylo.Model.Product;
import com.ecom.buylo.Model.RecentlyView;
import com.ecom.buylo.Model.response.ProductVendor;
import com.ecom.buylo.R;
import com.ecom.buylo.Utils.Constant;

import java.util.List;

public class VendorProductAdapter extends RecyclerView.Adapter<VendorProductAdapter.ViewHolder>{
    Context context;
    MyShopActivity myShopActivity;
    List<ProductVendor> catRes;

    public VendorProductAdapter(Context context,MyShopActivity myShopActivity,  List<ProductVendor> catRes) {
        this.context = context;
        this.myShopActivity=myShopActivity;
        this.catRes =catRes;
    }


    @NonNull
    @Override
    public VendorProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_related_layout,parent,false);
        return new VendorProductAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductVendor category = catRes.get(holder.getAdapterPosition());
        holder.mrp.setPaintFlags(holder.mrp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.mrp.setText("₹"+category.getMrp());
        holder.discount.setText("₹"+category.getSellingPrice());
        holder.off.setText(category.getDiscount());
        holder.name.setText(category.getProductName());
        if(!category.getProductImage().equals("")){
            Glide.with(context)
                    .load(Constant.IMAGE_URL+category.getProductImage())
                    .into(holder.image);
            Log.e("image",category.getProductImage());
        }else{
            Glide.with(context)
                    .load(R.drawable.noimg)
                    .into(holder.image);
        }

    }

    @Override
    public int getItemCount() {
        return catRes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout categoryLayout;
        ImageView image;
        TextView name,mrp,discount,off;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryLayout = itemView.findViewById(R.id.categoryLayout);
            image=itemView.findViewById(R.id.image);
            name=itemView.findViewById(R.id.name);
            mrp=itemView.findViewById(R.id.mrp);
            off=itemView.findViewById(R.id.off);
            discount=itemView.findViewById(R.id.discount);
        }
    }
}
