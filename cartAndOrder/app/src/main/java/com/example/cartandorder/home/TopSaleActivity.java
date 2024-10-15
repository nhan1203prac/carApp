package com.example.cartandorder.home;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cartandorder.R;
import com.example.cartandorder.favourite.Car;

import java.util.ArrayList;
import java.util.List;

public class TopSaleActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CarSaleAdapter carSaleAdapter;
    private List<Car> carList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_top_sale);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        carList = new ArrayList<>();
        carList.add(new Car("Mercedes Benz EQE", "Gray", "$171,250","Some desc","Mercedes"));
        carList.add(new Car("Tesla Model S", "Black", "$200,000","Some desc","Tesla"));
        carList.add(new Car("BMW i8", "Gray", "$140,000","Some desc","BMW"));
        carList.add(new Car("BMW i8", "Black", "$140,000","Some desc","BMW"));
        carList.add(new Car("Tesla Model S", "Black", "$200","Some desc","Tesla"));
        carList.add(new Car("Mercedes Benz EQE", "Gray", "$171,250","Some desc","Mercedes"));


        carSaleAdapter = new CarSaleAdapter(carList,this);

        recyclerView.setAdapter(carSaleAdapter);

//        Button all = findViewById(R.id.All);
//        Button mercedes = findViewById(R.id.Mercedes);
//        Button tesla = findViewById(R.id.Tesla);
//        Button bmw = findViewById(R.id.BMW);
//        Button acura = findViewById(R.id.Acura);

        String[] carBrands = {"All", "Mercedes", "Tesla", "BMW", "Acura"};
        for (String item : carBrands) {
            int resId = getResources().getIdentifier(item, "id", getPackageName());
            Button button = findViewById(resId);


            button.setOnClickListener(v -> {

                carSaleAdapter.filterByBrand(item);


                button.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.black));
                button.setTextColor(ContextCompat.getColor(this, R.color.white));


                for (String pt : carBrands) {
                    int otherResId = getResources().getIdentifier(pt, "id", getPackageName());
                    Button otherButton = findViewById(otherResId);


                    if (otherButton != button) {
                        otherButton.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.white));
                        otherButton.setTextColor(ContextCompat.getColor(this, R.color.black));
                    }
                }
            });

            Button btnBack = findViewById(R.id.btnBack);
            btnBack.setOnClickListener(v->{
                finish();
            });
        }




    }
}