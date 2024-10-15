package com.example.cartandorder.searchBar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cartandorder.R;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class SearchBar extends AppCompatActivity {

    LinearLayout linearLayout, linearSort;
    BottomSheetDialog bottomSheetDialog;
    String [] array_brands = {"Mercedes-Benz", "VinFast", "Lotus", "Mclaren", "Maserati", "Mazda"};
    int currentBackgroundResource = R.drawable.n_btn_search_filter;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.search_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText sBar = findViewById(R.id.search_bar);
        linearLayout = findViewById(R.id.linearlayout);
        addListItem();
//        remove item
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            TextView tmp = (TextView)linearLayout.getChildAt(i);
            Drawable drawable = ContextCompat.getDrawable(linearLayout.getContext(), R.drawable.n_svg_x);
            tmp.setOnTouchListener(new View.OnTouchListener() {
                @SuppressLint("ClickableViewAccessibility")
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getRawX() >= (tmp.getRight() - drawable.getBounds().width() - tmp.getPaddingEnd())) {
                        tmp.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                RemoveItem(view);
                            }
                        });
                    } else {
                        sBar.setText(tmp.getText());
                    }
                    return false;
                }
            });
        }

        ImageView imgViewBack = findViewById(R.id.imgBack);
        imgViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });

        TextView clearAll = findViewById(R.id.ClearAll);
        clearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                while(linearLayout.getChildCount() != 0) linearLayout.removeView(linearLayout.getChildAt(0));
            }
        });

//        filter
        sBar.setOnTouchListener(new View.OnTouchListener() {
            Drawable[] drawables = sBar.getCompoundDrawablesRelative();
            Drawable drawable = drawables[2];
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getRawX() >= (sBar.getRight() - drawable.getBounds().width() - sBar.getPaddingEnd())) {
                    sBar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            BottomDialog();
                            sBar.setOnClickListener(null);
                        }
                    });
                }
                return false;
            }
        });
//        fill
        sBar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE || (keyEvent != null && keyEvent.getAction() == KeyEvent.ACTION_DOWN && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    String searchText = sBar.getText().toString();
                    if (searchText.equals("Mercedes-Benz")) {
                        startActivity(new Intent(SearchBar.this, Search_Item.class));
                    } else {
                        startActivity(new Intent(SearchBar.this, Search_Empty.class));
                    }
                    return true;
                }
                return false;
            }
        });
    }

    private void BottomDialog() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.search_filter);
        bottomSheetDialog.setCancelable(true);
        bottomSheetDialog.setCanceledOnTouchOutside(true);

        LinearLayout linearCondition = bottomSheetDialog.findViewById(R.id.linearcondition);
        for (int i = 0; i < linearCondition.getChildCount(); i++) {
            Button btn = (Button)linearCondition.getChildAt(i);
            btn.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceType")
                @Override
                public void onClick(View view) {
                    chooseButton(btn, linearCondition);
                }
            });
        }

        LinearLayout linearSort = bottomSheetDialog.findViewById(R.id.linearsort);
        for (int i = 0; i < linearSort.getChildCount(); i++) {
            Button btn = (Button)linearSort.getChildAt(i);
            btn.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceType")
                @Override
                public void onClick(View view) {
                    chooseButton(btn, linearSort);
                }
            });
        }

        LinearLayout linearRating = bottomSheetDialog.findViewById(R.id.linearrating);
        for (int i = 0; i < linearRating.getChildCount(); i++) {
            Button btn = (Button)linearRating.getChildAt(i);
            btn.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceType")
                @Override
                public void onClick(View view) {
                    for (int j = 0; j < linearRating.getChildCount(); j++) {
                        ((Button)linearRating.getChildAt(j)).setBackgroundResource(R.drawable.n_btn_search_filter);
                        ((Button)linearRating.getChildAt(j)).setTextColor(getResources().getColor(R.color.black));
                    }
                    btn.setBackgroundResource(R.drawable.n_btn_search_filter_choose);
                    btn.setTextColor(getResources().getColor(R.color.white));
                }
            });
        }

        Button btnReset = bottomSheetDialog.findViewById(R.id.reset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText num1 = bottomSheetDialog.findViewById(R.id.editTextNumber);
                EditText num2 = bottomSheetDialog.findViewById(R.id.editTextNumber2);
                num1.setText("");
                num2.setText("");
                for (int j = 0; j < linearSort.getChildCount(); j++) {
                    ((Button)linearSort.getChildAt(j)).setBackgroundResource(R.drawable.n_btn_search_filter);
                    ((Button)linearSort.getChildAt(j)).setTextColor(getResources().getColor(R.color.black));
                }
                for (int j = 0; j < linearCondition.getChildCount(); j++) {
                    ((Button)linearCondition.getChildAt(j)).setBackgroundResource(R.drawable.n_btn_search_filter);
                    ((Button)linearCondition.getChildAt(j)).setTextColor(getResources().getColor(R.color.black));
                }
                for (int j = 0; j < linearRating.getChildCount(); j++) {
                    ((Button)linearRating.getChildAt(j)).setBackgroundResource(R.drawable.n_btn_search_filter);
                    ((Button)linearRating.getChildAt(j)).setTextColor(getResources().getColor(R.color.black));
                }
            }
        });

        Button btnApply = bottomSheetDialog.findViewById(R.id.apply);
        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Apply", Toast.LENGTH_LONG).show();
                bottomSheetDialog.cancel();
            }
        });
        bottomSheetDialog.show();
    }

    private void RemoveItem(View view) {
        int id = view.getId();
        String resourceNameID = getResources().getResourceEntryName(id);
        linearLayout.removeView(view);
        TextView tvTMP = new TextView(this);
        tvTMP.setText("");
        linearLayout.addView(tvTMP);
    }

    private boolean CheckItem(String str) {
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            if (((TextView)linearLayout.getChildAt(i)).getText().toString().equals(array_brands[i])) return true;
        }
        return false;
    }

    private void addListItem() {
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            TextView tmp = (TextView)linearLayout.getChildAt(i);
            tmp.setText(array_brands[i]);
        }

    }

    private void chooseButton(Button btn, LinearLayout linear) {
        for (int j = 0; j < linear.getChildCount(); j++) {
            ((Button)linear.getChildAt(j)).setBackgroundResource(R.drawable.n_btn_search_filter);
            ((Button)linear.getChildAt(j)).setTextColor(getResources().getColor(R.color.black));
        }
        btn.setBackgroundResource(R.drawable.n_btn_search_filter_choose);
        btn.setTextColor(getResources().getColor(R.color.white));
    }

    private void resetFilter() {

    }
}
