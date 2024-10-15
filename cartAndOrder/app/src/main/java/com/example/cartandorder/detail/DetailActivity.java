package com.example.cartandorder.detail;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cartandorder.R;
import com.example.cartandorder.comment.CommentActivity;
import com.example.cartandorder.contact.ChatActivity;
import com.example.cartandorder.fragments.HomeFragment;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SlideImageAdapter imageSliderAdapter;
    private List<Integer> images;
    Button btnNext,btnPrev;
    Button btnCall, btnSend ;
    Button btnBack;
    TextView seeMore;
    String numberPhone = "0867867652";
    final int[] currentPosition = {0};
    int totalItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);


        btnNext = findViewById(R.id.btnNext);
        btnPrev = findViewById(R.id.btnPrev);
        btnCall = findViewById(R.id.btnCall);
        btnSend = findViewById(R.id.btnSend);
        btnBack = findViewById(R.id.btnBack);

        seeMore = findViewById(R.id.seeMore);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        images = new ArrayList<>();
        images.add(R.drawable.car_galery_1);
        images.add(R.drawable.car_galery_2);
        images.add(R.drawable.car_galery_3);
        images.add(R.drawable.car_galery_4);
        images.add(R.drawable.car_galery_5);

        totalItems = images.size();

        recyclerView = findViewById(R.id.recyclerView);
        imageSliderAdapter = new SlideImageAdapter(images);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(imageSliderAdapter);

        btnNext.setOnClickListener(v -> {
            if (currentPosition[0] < totalItems - 1) {
                currentPosition[0]++;
                recyclerView.smoothScrollToPosition(currentPosition[0]);
            }
        });

        btnPrev.setOnClickListener(v -> {
            if (currentPosition[0] > 0) {
                currentPosition[0]--;
                recyclerView.smoothScrollToPosition(currentPosition[0]);
            }
        });

        btnCall.setOnClickListener(v->{
            Intent intentCall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+numberPhone));
            if (ActivityCompat.checkSelfPermission(DetailActivity.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(DetailActivity.this,new String[]{Manifest.permission.CALL_PHONE},1);
                return;
            }
            startActivity(intentCall);
        });

        btnSend.setOnClickListener(v->{
            Intent navigateToChatPage = new Intent(DetailActivity.this, ChatActivity.class);
            startActivity(navigateToChatPage);
        });
        seeMore.setOnClickListener(v->{
            Intent navigateToReviewPage = new Intent(DetailActivity.this, CommentActivity.class);
            startActivity(navigateToReviewPage);
        });
        btnBack.setOnClickListener(v->{

            finish(); // Kết thúc Activity 2
        });
    }
}
