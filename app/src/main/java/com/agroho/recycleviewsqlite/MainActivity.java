package com.agroho.recycleviewsqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Adapter.ItemClickCallBack{

    RecyclerView recyclerView;
    Adapter productAdapter;
    List<Product> productList = new ArrayList<>();
    EditText productName,productCategory,productLocation,productPrice;

    Button addProductButton;

    DatabaseHelper productDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productDb = new DatabaseHelper(this);

        productName = (EditText)findViewById(R.id.editTextProductName);
        productCategory = (EditText)findViewById(R.id.editTextProductCategory);
        productLocation = (EditText)findViewById(R.id.editTextProductLocation);
        productPrice = (EditText)findViewById(R.id.editTextProductPrice);

        addProductButton = (Button)findViewById(R.id.button);

        addProductData();

        recyclerView = (RecyclerView)findViewById(R.id.recycleview_product);

       prepareProductData();

    }


    private void addProductData(){
        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Product product = new Product(productName.getText().toString(),productCategory.getText().toString(),productLocation.getText().toString(),
                        Integer.parseInt(productPrice.getText().toString()));

                boolean isInserted =  productDb.insertProductData(product);
                productAdapter.notifyDataSetChanged();
                prepareProductData();
                if (isInserted == true){

                    Toast.makeText(MainActivity.this, "Data inserted Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Unsuccessful Insertion", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void prepareProductData() {

        productList = productDb.getAllProductList();
        productAdapter = new Adapter(productList);
        RecyclerView.LayoutManager productLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(productLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(productAdapter);
        productAdapter.setItemClickCallBack(this);


        //ArrayList<Product> products = new ArrayList<>();


     /*   int s;
        for (s = 0;s<products.size();s++){

            Toast.makeText(MainActivity.this, products.get(s).getProductName(), Toast.LENGTH_SHORT).show();

        }

        Product product;

        product = new Product("Mango","Fruit","Dhaka",1500);
        productList.add(product);

        product = new Product("Banana","Fruit","Comilla",500);
        productList.add(product);

        product = new Product("Head First Java","Book","New Market, Dhaka",800);
        productList.add(product);

        product = new Product("Tupi","Islamic Dress","Dhaka",50);
        productList.add(product);

        product = new Product("Shirt","Casual Dress","Dhaka",2100);
        productList.add(product);*/

        productAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(int position) {

        Product product = productList.get(position);

        Intent i = new Intent(this,ItemDetailsActivity.class);

        Bundle extras = new Bundle();
        extras.putString("itemName",product.getProductName());
        extras.putString("itemCategory",product.getProductCategory());

        i.putExtra("ExtraBundle",extras);

        startActivity(i);



        Toast.makeText(MainActivity.this, product.getProductName(), Toast.LENGTH_SHORT).show();

    }
}
