package com.example.cartandorder.login;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cartandorder.MainActivity;
import com.example.cartandorder.R;
import com.example.cartandorder.fragments.HomeFragment;


public class SignInByPass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login_by_pass);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView tviewRegister = findViewById(R.id.tvSignUp);
        tviewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInByPass.this, Register.class));
            }
        });

        ImageView imgViewBack = findViewById(R.id.imgBack);
        imgViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInByPass.this, Welcome.class));
            }
        });

        Button btnLogin = findViewById(R.id.btnSignIn);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              Check User, Pass
                EditText eTxtPass = findViewById(R.id.editTextText2);
                String tmp = eTxtPass.getText().toString().trim();
                if (tmp.equals("error")) {
                    DialogLogin();
                } else {
                    Toast.makeText(getApplicationContext(),"Login success", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(SignInByPass.this, MainActivity.class));
                }
            }
        });

        TextView tviewHOSPass = findViewById(R.id.textView7);
        tviewHOSPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edt = findViewById(R.id.editTextText2);
                TextView txtv = findViewById(R.id.textView7);
                int tmp = edt.getInputType();
                if ((tmp & InputType.TYPE_TEXT_VARIATION_PASSWORD) == InputType.TYPE_TEXT_VARIATION_PASSWORD) {
                    txtv.setText("See");
                    Drawable vectorDrawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.n_svg_see_pass);
                    txtv.setCompoundDrawablesRelativeWithIntrinsicBounds(vectorDrawable, null, null, null);
                    edt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    edt.setInputType(InputType.TYPE_CLASS_TEXT);
                } else {
                    txtv.setText("Hide");
                    Drawable vectorDrawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.n_svg_hide_pass);
                    txtv.setCompoundDrawablesRelativeWithIntrinsicBounds(vectorDrawable, null, null, null);
                    edt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    edt.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });

        TextView tviewRP = findViewById(R.id.textView5);
        tviewRP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInByPass.this, ResetPassword.class));
            }
        });
    }

    private void DialogLogin() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.messenger_login_fault);
        dialog.setCanceledOnTouchOutside(false);

        Button btnTryAgain = (Button)dialog.findViewById(R.id.button5);
        btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
