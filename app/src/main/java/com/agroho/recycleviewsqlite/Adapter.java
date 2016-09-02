package com.agroho.recycleviewsqlite;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rezau on 2016-08-31.
 */
public class Adapter extends RecyclerView.Adapter<Adapter.RecycleViewHolder> {

    private List<Product> productList;
    private ItemClickCallBack itemClickCallBack;

    public interface ItemClickCallBack {
        void onItemClick(int position);
    }

    public Adapter(List<Product> productList) {

        this.productList = productList;
    }

    public void setItemClickCallBack(final ItemClickCallBack itemClickCallBack){
        this.itemClickCallBack = itemClickCallBack;
    }


    @Override
    public RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list,parent,false);
        return new RecycleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecycleViewHolder holder, int position) {

        Product product = productList.get(position);
        holder.productName.setText(product.getProductName());
        holder.productCategory.setText(product.getProductCategory());
        holder.productLocation.setText(product.getProductLocation());
        holder.productPrice.setText(Integer.toString(product.getProductPrice()));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class RecycleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView productName,productCategory, productLocation, productPrice;
        public View containerProductList;

        public RecycleViewHolder(View itemView) {
            super(itemView);

            productName = (TextView)itemView.findViewById(R.id.product_name);
            productCategory = (TextView)itemView.findViewById(R.id.category);
            productLocation = (TextView)itemView.findViewById(R.id.location);
            productPrice = (TextView)itemView.findViewById(R.id.price);
            containerProductList = itemView.findViewById(R.id.container_product_list);
            containerProductList.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            if (view.getId()==R.id.container_product_list){

                itemClickCallBack.onItemClick(getAdapterPosition());

            }
        }

       /* public void notify(ArrayList<Product> products){

            if (productList != null) {
                productList.addAll(products);
            }
            else {
                productList = products;
            }

            notifyDataSetChanged();
        }*/
    }
}
