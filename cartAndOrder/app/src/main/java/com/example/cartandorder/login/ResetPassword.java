package com.example.cartandorder.login;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cartandorder.R;

public class ResetPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.reset_password);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//      Foreground text
        TextView textView = findViewById(R.id.textView3);
        String tmpcolortext = "<font color=#000000>Please Enter Your </font>"
                + "<font color=#F2C620>UserName</font>"
                + "<font color=#000000>, we can send </font>"
                + "<font color=#F2C620>new Password</font>"
                + "<font color=#000000>, to your </font>"
                + "<font color=#F2C620>Phone number</font>";
        textView.setText(Html.fromHtml(tmpcolortext));


        Button btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edt = findViewById(R.id.editTextText);
                String tmp = edt.getText().toString().trim();
                if (tmp.equals("correct")) {
                    startActivity(new Intent(ResetPassword.this, ResetPassword_True.class));
                } else {
                    DialogLogin();
                }
            }
        });
        ImageView imgViewBack = findViewById(R.id.imgBack);
        imgViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ResetPassword.this, Welcome.class));
            }
        });
    }

    private void DialogLogin() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.messenger_resetpass_fault);
        dialog.setCanceledOnTouchOutside(false);

        Button btnCheck = (Button)dialog.findViewById(R.id.btnCheck);
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        Button btnCreate = (Button)dialog.findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResetPassword.this, Register.class));
            }
        });
        dialog.show();
    }
}
