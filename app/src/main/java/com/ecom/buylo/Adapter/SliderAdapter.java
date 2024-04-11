package com.ecom.buylo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.ecom.buylo.Fragment.HomeMainFragment;
import com.ecom.buylo.Model.SliderData;
import com.ecom.buylo.R;
import com.ecom.buylo.Activity.RentActivity;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterViewHolder> {


    List<SliderData> mSliderItems;
    HomeMainFragment homeMainFragment;
    RentActivity rentActivity;
    Context context;
    // Constructor
    public SliderAdapter(Context context, HomeMainFragment homeMainFragment,RentActivity rentActivity, List<SliderData> sliderDataArrayList) {
        this.mSliderItems = sliderDataArrayList;
        this.homeMainFragment=homeMainFragment;
        this.rentActivity=rentActivity;
        this.context = context;
    }

    @Override
    public SliderAdapterViewHolder onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_layout, null);
        return new SliderAdapterViewHolder(inflate);
    }

    /**
     * Bind data at position into viewHolder
     *
     * @param viewHolder item view holder
     * @param position   item position
     */
    @Override
    public void onBindViewHolder(SliderAdapterViewHolder viewHolder, int position) {
        SliderData sliderItem = mSliderItems.get(position);
        /*  viewHolder.imageViewBackground.setImageResource(sliderItem.getImgUrl());*/
        viewHolder.imageViewBackground.setImageDrawable(context.getResources().getDrawable(mSliderItems.get(position).
                getImgUrl(), context.getTheme()));
    }


    @Override
    public int getCount() {
        return mSliderItems.size();
    }

    public class SliderAdapterViewHolder extends ViewHolder {
        View itemView;
        ImageView imageViewBackground;

        TextView mytext;
        public SliderAdapterViewHolder(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.myimage);
            this.itemView = itemView;

        }
    }

}
