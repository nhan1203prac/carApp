package com.example.cartandorder.comment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cartandorder.R;
import com.example.cartandorder.detail.DetailActivity;

import java.util.ArrayList;
import java.util.List;

public class CommentActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CommentAdapter reviewAdapter;
    private List<Comment> reviewList;
    private Button btnBack;
    ImageView profileImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);


        btnBack = findViewById(R.id.btnBack);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Dữ liệu demo
        String review = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s";
        String image = "https://static.vecteezy.com/system/resources/thumbnails/001/840/612/small_2x/picture-profile-icon-male-icon-human-or-people-sign-and-symbol-free-vector.jpg";

        reviewList = new ArrayList<>();
        reviewList.add(new Comment("Truong Ngoc Quoc", review, 5, 120, 20,image));
        reviewList.add(new Comment("Vo Thanh Nhan", review, 4, 123, 20,image));
        reviewList.add(new Comment("Huynh Nguyen Vinh Nguyen", review, 3, 110, 30,image));
        reviewList.add(new Comment("Tran Lam Nghia", review, 2, 140, 10,image));
        reviewList.add(new Comment("Doan Kim Nghia", review, 1, 100, 50,image));


        reviewAdapter = new CommentAdapter(reviewList,getApplicationContext());
        recyclerView.setAdapter(reviewAdapter);

        Spinner starFilterSpinner = findViewById(R.id.starFilterSpinner);
        Button filterButton = findViewById(R.id.filterButton);


        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedStars = Integer.parseInt(starFilterSpinner.getSelectedItem().toString());
                reviewAdapter.filterByStars(selectedStars); // Lọc theo số sao
            }
        });
        btnBack.setOnClickListener(v->{
            Intent intent = new Intent(CommentActivity.this, DetailActivity.class);
            startActivity(intent);
            finish();
        });
    }
}