package com.ecom.buylo.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ecom.buylo.Adapter.CategoryAdapter;
import com.ecom.buylo.Model.Category;
import com.ecom.buylo.R;

import java.util.ArrayList;

public class CategoryFragment extends Fragment {
    RecyclerView categoryRecycler;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_category, container, false);
        categoryRecycler=view.findViewById(R.id.categoryRecycler);

        ArrayList<Category> categoriess= new ArrayList<>();
        categoriess.add(new Category(R.drawable.presser,"Cloth"));
        categoriess.add(new Category(R.drawable.juicer,"Juicer"));
        categoriess.add(new Category(R.drawable.laptop,"Laptop"));
        categoriess.add(new Category(R.drawable.snack,"snack"));
        categoriess.add(new Category(R.drawable.laptop,"Laptop"));
        categoriess.add(new Category(R.drawable.kurkur,"snack"));
        categoryRecycler.setHasFixedSize(true);
        CategoryAdapter adapter1 =new CategoryAdapter(getContext(), CategoryFragment.this,null,categoriess);
        categoryRecycler.setAdapter(adapter1);


        return view;
    }
}