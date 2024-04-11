package com.ecom.buylo.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ecom.buylo.Model.Product;
import com.ecom.buylo.R;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{
    Context context;
    List<Product> catRes;

    public ProductAdapter(Context context,  List<Product> catRes) {
        this.context = context;
        this.catRes =catRes;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_related_layout,parent,false);
        return new ProductAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        Product category = catRes.get(holder.getAdapterPosition());
        holder.mrp.setPaintFlags(holder.mrp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.image.setImageResource(category.getImage());
        holder.name.setText(category.getName());
        holder.discount.setText("₹"+category.getDiscount());
        holder.mrp.setText("₹"+category.getMrp());
    }

    @Override
    public int getItemCount() {
        return catRes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout categoryLayout;
        ImageView image;
        TextView name,mrp,discount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryLayout = itemView.findViewById(R.id.categoryLayout);
            image=itemView.findViewById(R.id.image);
            name=itemView.findViewById(R.id.name);
            mrp=itemView.findViewById(R.id.mrp);
            discount=itemView.findViewById(R.id.discount);
        }
    }
}
