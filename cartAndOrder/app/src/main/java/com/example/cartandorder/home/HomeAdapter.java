package com.example.cartandorder.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cartandorder.R;
import com.example.cartandorder.detail.DetailActivity;
import com.example.cartandorder.favourite.Car;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.CarViewHolder> {

    private Context context;
    private List<Car> carList;

    // Constructor
    public HomeAdapter(Context context, List<Car> carList) {
        this.context = context;
        this.carList = carList;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {

        Car car = carList.get(position);
        holder.carName.setText(car.getName());
        holder.carPrice.setText(car.getPrice());


        holder.showMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                context.startActivity(intent);
            }
        });

        holder.iconView.setOnClickListener(new View.OnClickListener() {
            boolean isToggled = false;

            @Override
            public void onClick(View v) {
                if (isToggled) {

                    holder.iconView.setBackgroundTintList(ContextCompat.getColorStateList(context, R.color.black));
                } else {

                    holder.iconView.setBackgroundTintList(ContextCompat.getColorStateList(context, R.color.red));
                }

                isToggled = !isToggled;
            }
        });

    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    public static class CarViewHolder extends RecyclerView.ViewHolder {

        TextView carName, carPrice;
        Button showMoreButton, iconView;

        public CarViewHolder(@NonNull View itemView) {
            super(itemView);


            carName = itemView.findViewById(R.id.car_name3);
            carPrice = itemView.findViewById(R.id.car_price3);
            showMoreButton = itemView.findViewById(R.id.show_more_button3);
            iconView = itemView.findViewById(R.id.iconView);
        }
    }
}

