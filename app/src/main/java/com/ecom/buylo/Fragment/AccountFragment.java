package com.ecom.buylo.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ecom.buylo.Activity.DashBoardMainActivity;
import com.ecom.buylo.Activity.OtpSenderActivity;
import com.ecom.buylo.Activity.SellerDashBoardActivity;
import com.ecom.buylo.Activity.SellerHubActivity;
import com.ecom.buylo.R;
import com.ecom.buylo.Utils.MyPreferences;
import com.google.android.material.button.MaterialButton;


public class AccountFragment extends Fragment {

    MaterialButton Seller;
    LinearLayout logoutLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_account, container, false);

        Seller=view.findViewById(R.id.Seller);
        logoutLayout=view.findViewById(R.id.logoutLayout);

        Seller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyPreferences preferences = MyPreferences.getInstance(getContext());
                if (preferences.getUserName()!= null) {
                    Intent intent = new Intent(getContext(), SellerDashBoardActivity.class);
                    startActivity(intent);

                } else {
                    Intent intent = new Intent(getContext(), SellerHubActivity.class);
                    startActivity(intent);
                }
            }
        });

//        Seller.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(getContext(), CategoryActivity.class);
//                startActivity(intent);
//            }
//        });
        logoutLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyPreferences.getInstance(getContext()).logout();
                startActivity(new Intent(getContext(), OtpSenderActivity.class));

            }
        });

        return view;
    }
}