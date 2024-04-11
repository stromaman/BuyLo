package com.ecom.buylo.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ecom.buylo.Activity.CatalogActivity;
import com.ecom.buylo.Activity.DashBoardMainActivity;
import com.ecom.buylo.Activity.MyShopActivity;
import com.ecom.buylo.R;
import com.google.android.material.button.MaterialButton;
import com.skydoves.elasticviews.ElasticCardView;


public class HomeFragment extends Fragment {




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_home, container, false);


//        uploadCatalog.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent( getContext(), CategoryActivity.class);
//                startActivity(intent);
//            }
//        });


        return view;
    }
}