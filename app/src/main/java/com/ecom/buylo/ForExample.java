package com.ecom.buylo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.ecom.buylo.Adapter.SCategoryAdapter;
import com.ecom.buylo.Model.SCategory;

import java.util.ArrayList;
import java.util.List;


public class ForExample extends AppCompatActivity {
    private RecyclerView categoryRecyclerView;
    private SCategoryAdapter categoryAdapter;
    private List<SCategory> categoryList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_example);
        categoryRecyclerView = findViewById(R.id.categoryRecycler);
        categoryList = new ArrayList<>();
        categoryAdapter = new SCategoryAdapter(this, categoryList);
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        categoryRecyclerView.setAdapter(categoryAdapter);

        // Populate RecyclerView with categories
        populateCategoryList();
    }
    private void populateCategoryList() {
        // You can replace this with actual data fetched from server
        categoryList.add(new SCategory("1", "Clothing"));
        categoryList.add(new SCategory("2", "Electronics"));
        categoryList.add(new SCategory("3", "Home & Kitchen"));
        categoryList.add(new SCategory("4", "Beauty & Personal Care"));
        categoryList.add(new SCategory("5", "Books"));

        // Notify adapter about data change
        categoryAdapter.notifyDataSetChanged();
    }

    // Method to handle category selection (optional, you can handle it in adapter as well)
    public void onCategorySelected(SCategory category) {
        // Handle category selection here
        Toast.makeText(this, "Selected category: " + category.getCategoryName(), Toast.LENGTH_SHORT).show();
        // You can perform further actions like passing selected category data to another activity
    }
}
