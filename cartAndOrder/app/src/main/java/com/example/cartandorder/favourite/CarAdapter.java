package com.example.cartandorder.favourite;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.cartandorder.R;
import com.example.cartandorder.detail.DetailActivity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.graphics.Color;
import android.content.res.ColorStateList;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {
    private List<Car> carList;
    private OnCarClickListener onCarClickListener;

    public interface OnCarClickListener {
        void onCallClick(Car car);
        void onSendClick(Car car);
    }

    public CarAdapter(List<Car> carList,OnCarClickListener listener) {
        this.carList = carList;
        this.onCarClickListener = listener;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_car, parent, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        Car car = carList.get(position);
        holder.carName.setText(car.getName());
        holder.textColor.setText(car.getColor());
        int color = Color.parseColor(car.getColor());
        holder.carColor.setBackgroundTintList(ColorStateList.valueOf(color));
        holder.carPrice.setText(car.getPrice());

        holder.btnCall.setOnClickListener(v -> {
            if (onCarClickListener != null) {
                onCarClickListener.onCallClick(car);
            }
        });


        holder.btnSend.setOnClickListener(v -> {
            if (onCarClickListener != null) {
                onCarClickListener.onSendClick(car);
            }
        });
        holder.carImage.setOnClickListener(v->{
            Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    static class CarViewHolder extends RecyclerView.ViewHolder {
        ImageView carImage;
        TextView carName,textColor,  carPrice;
        Button carColor,btnCall,btnSend;

        public CarViewHolder(@NonNull View itemView) {
            super(itemView);

            carName = itemView.findViewById(R.id.carName);
            textColor = itemView.findViewById(R.id.textColor);
            carColor = (Button) itemView.findViewById(R.id.carColor);
            carPrice = itemView.findViewById(R.id.carPrice);
            btnCall = itemView.findViewById(R.id.btnCall);
            btnSend = itemView.findViewById(R.id.btnSend);
            carImage = itemView.findViewById(R.id.carImage);
        }
    }
}
