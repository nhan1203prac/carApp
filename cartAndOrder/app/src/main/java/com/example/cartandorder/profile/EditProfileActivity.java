package com.example.cartandorder.profile;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cartandorder.R;

import java.util.Calendar;

public class EditProfileActivity extends AppCompatActivity {
    Button btnSubmit,btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        btnSubmit = findViewById(R.id.btn_dob);
        btnBack = findViewById(R.id.backIcon);

        btnBack.setOnClickListener(v->{
            finish();
        });
        // Khởi tạo Calendar để lấy ngày hiện tại
        final Calendar calendar = Calendar.getInstance();

        btnSubmit.setOnClickListener(v -> {
            // Lấy năm, tháng, ngày hiện tại
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            // Tạo DatePickerDialog
            DatePickerDialog datePickerDialog = new DatePickerDialog(EditProfileActivity.this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        // Định dạng ngày tháng
                        String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                        // Hiển thị ngày đã chọn lên Button
                        btnSubmit.setText(selectedDate);
                        // Hiển thị thông báo
                        Toast.makeText(EditProfileActivity.this, "Ngày đã chọn: " + selectedDate, Toast.LENGTH_SHORT).show();
                    }, year, month, day);

            // Hiển thị DatePickerDialog
            datePickerDialog.show();
        });
    }
}
