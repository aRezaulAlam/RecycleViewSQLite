package com.agroho.recycleviewsqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ItemDetailsActivity extends AppCompatActivity {

    TextView detailsItemName, detailsItemCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        Bundle extras = getIntent().getBundleExtra("ExtraBundle");


        detailsItemName = (TextView)findViewById(R.id.details_item_name);
        detailsItemCategory = (TextView)findViewById(R.id.details_item_category);

        detailsItemName.setText(extras.getString("itemName"));
        detailsItemCategory.setText(extras.getString("itemCategory"));


    }
}
