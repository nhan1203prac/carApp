package com.example.cartandorder.contact.chatAndCall;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cartandorder.R;
import com.example.cartandorder.contact.ChatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChatListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChatListFragment extends Fragment {

    private RecyclerView recyclerViewChat;
    private ChatListAdapter chatAdapter;
    private List<ChatItem> chatList;
    String imageUrl = "https://static-00.iconduck.com/assets.00/mercedes-benz-alt-icon-2048x2048-ps1d95md.png";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ChatListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChatListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChatListFragment newInstance(String param1, String param2) {
        ChatListFragment fragment = new ChatListFragment();
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
        View view =  inflater.inflate(R.layout.fragment_chat_list, container, false);
        recyclerViewChat = view.findViewById(R.id.recyclerViewChat);
        recyclerViewChat.setLayoutManager(new LinearLayoutManager(requireContext()));

        chatList = new ArrayList<>();
        chatList.add(new ChatItem("John", "Hello!", imageUrl));
        chatList.add(new ChatItem("Jane", "How are you?", imageUrl));
        chatList.add(new ChatItem("Alex", "Good Morning!", imageUrl));
        chatList.add(new ChatItem("nhan", "Hello!", imageUrl));
        chatList.add(new ChatItem("nguyen", "How are you?", imageUrl));
        chatList.add(new ChatItem("nghia", "Good Morning!", imageUrl));

        chatAdapter = new ChatListAdapter(chatList, new ChatListAdapter.OnChatClickListener() {
            @Override
            public void onCallClick(ChatItem chartItem) {
                Intent intentSend = new Intent(requireActivity(), ChatActivity.class);
                startActivity(intentSend);
            }
        });
        recyclerViewChat.setAdapter(chatAdapter);

        return view;

    }
}