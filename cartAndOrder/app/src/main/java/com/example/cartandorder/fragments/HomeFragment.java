package com.example.cartandorder.fragments;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cartandorder.detail.DetailActivity;
import com.example.cartandorder.R;
import com.example.cartandorder.favourite.Car;
import com.example.cartandorder.favourite.CarAdapter;
import com.example.cartandorder.home.HomeAdapter;
import com.example.cartandorder.home.Home_SpecialOffers;
import com.example.cartandorder.home.TopSaleActivity;
import com.example.cartandorder.searchBar.SearchBar;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private HomeAdapter carAdapter;
    private List<Car> carList;
    private TextView see_all;
    private TextView seeAll;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);


        carList = new ArrayList<>();
        carList.add(new Car("Mercedes Benz EQE", "Gray", "$171,250","Some desc","Mercedes"));
        carList.add(new Car("Tesla Model S", "Black", "$200,000","Some desc","Tesla"));
        carList.add(new Car("BMW i8", "Gray", "$140,000","Some desc","BMW"));
        carList.add(new Car("BMW i8", "Black", "$140,000","Some desc","BMW"));
        carList.add(new Car("Tesla Model S", "Black", "$200","Some desc","Tesla"));
                carList.add(new Car("Mercedes Benz EQE", "Gray", "$171,250","Some desc","Mercedes"));

        carAdapter = new HomeAdapter(requireContext(), carList);
        recyclerView.setAdapter(carAdapter);



        EditText sBar = view.findViewById(R.id.search_bar);
        sBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SearchBar.class));
            }
        });

        seeAll = view.findViewById(R.id.seeall);
        seeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Home_SpecialOffers.class));
            }
        });

         see_all = view.findViewById(R.id.see_all);
        see_all.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(), TopSaleActivity.class);
            startActivity(intent);
        });
        return view;
    }

}