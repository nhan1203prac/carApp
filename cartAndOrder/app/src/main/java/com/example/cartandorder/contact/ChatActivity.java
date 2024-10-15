package com.example.cartandorder.contact;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cartandorder.R;
import com.example.cartandorder.contact.chatAndCall.ChatListFragment;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    private RecyclerView recyclerViewChat;
    private ChatAdapter chatAdapter;
    private List<ChatMessage> messages;
    EditText editTextMessage;
    Button send,btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        recyclerViewChat = findViewById(R.id.recyclerViewChat);
        editTextMessage = (EditText) findViewById(R.id.editTextMessage);
        send = (Button) findViewById(R.id.send);
        btnBack = findViewById(R.id.btnBack);
        // Dữ liệu tin nhắn demo
        messages = new ArrayList<>();
        messages.add(new ChatMessage("Welcome to Mercedes-Benz! How can I assist you today?", false));
        messages.add(new ChatMessage("I'm interested in learning more about the Mercedes-Benz EQE Sedan.", true));
        messages.add(new ChatMessage("Sure! The Mercedes-Benz EQE Sedan offers a combination of luxury and performance...", false));

        chatAdapter = new ChatAdapter(messages);
        recyclerViewChat.setAdapter(chatAdapter);
        recyclerViewChat.setLayoutManager(new LinearLayoutManager(this));

        send.setOnClickListener(v->{
            String info = editTextMessage.getText().toString();
            if(!info.isEmpty()){
                messages.add(new ChatMessage(info,true));
                chatAdapter.notifyItemInserted(messages.size() - 1);
                recyclerViewChat.scrollToPosition(messages.size() - 1);
                editTextMessage.setText("");
            }
        });
        btnBack.setOnClickListener(v-> {
            finish();
        });
    }
}