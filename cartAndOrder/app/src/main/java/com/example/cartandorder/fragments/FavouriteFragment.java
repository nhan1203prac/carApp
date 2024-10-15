package com.example.cartandorder.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cartandorder.R;
import com.example.cartandorder.contact.ChatActivity;
import com.example.cartandorder.detail.DetailActivity;
import com.example.cartandorder.favourite.Car;
import com.example.cartandorder.favourite.CarAdapter;
import com.example.cartandorder.favourite.FavouriteActivity;
import com.example.cartandorder.searchBar.SearchBar;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavouriteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavouriteFragment extends Fragment {
    private RecyclerView recyclerView;
    private CarAdapter carAdapter;
    private List<Car> carList;
    Button btnCall, btnSend;
    String numberPhone = "0867867652";
    Button btnSearch;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FavouriteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavouriteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavouriteFragment newInstance(String param1, String param2) {
        FavouriteFragment fragment = new FavouriteFragment();
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
        View view =  inflater.inflate(R.layout.fragment_favourite, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
         btnSearch = view.findViewById(R.id.search);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        carList = new ArrayList<>();
        carList.add(new Car("Mercedes Benz EQE", "Gray", "$171,250","Some desc","Mercedes"));
        carList.add(new Car("Tesla Model S", "Black", "$200,000","Some desc","Tesla"));
        carList.add(new Car("BMW i8", "Gray", "$140,000","Some desc","BMW"));
        carList.add(new Car("BMW i8", "Black", "$140,000","Some desc","BMW"));
        carAdapter = new CarAdapter(carList, new CarAdapter.OnCarClickListener() {
            @Override
            public void onCallClick(Car car) {
                Intent intentCall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+numberPhone));
                if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(requireActivity(),new String[]{Manifest.permission.CALL_PHONE},1);
                    return;
                }
                startActivity(intentCall);
            }

            @Override
            public void onSendClick(Car car) {
                Intent intentSend = new Intent(requireContext(), ChatActivity.class);
                startActivity(intentSend);
            }
        });

        recyclerView.setAdapter(carAdapter);


        btnSearch.setOnClickListener(v->{
            startActivity(new Intent(getActivity(), SearchBar.class));
        });

         return view;
    }



}