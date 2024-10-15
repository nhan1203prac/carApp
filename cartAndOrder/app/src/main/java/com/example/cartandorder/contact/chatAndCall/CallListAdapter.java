package com.example.cartandorder.contact.chatAndCall;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cartandorder.R;


import java.time.format.DateTimeFormatter;
import java.util.List;

public class CallListAdapter extends RecyclerView.Adapter<CallListAdapter.CallViewHolder> {
    private List<CallItem> callList;
    private OnChatClickListener onChatClickListener;

    public interface OnChatClickListener {
        void onCallClick(CallItem callItem);

    }
    public CallListAdapter(List<CallItem> callList,OnChatClickListener onChatClickListener) {
        this.callList = callList;
        this.onChatClickListener = onChatClickListener;
    }

    @NonNull
    @Override
    public CallViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_call, parent, false);
        return new CallViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CallViewHolder holder, int position) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        CallItem callItem = callList.get(position);
        holder.callName.setText(callItem.getName());
        holder.time.setText(callItem.isSend() ? "Out going" : "InComming" + " | " + callItem.getTime().format(formatter));

        Glide.with(holder.itemView.getContext())
                .load(callItem.getImage())
                .into(holder.callImage);

        holder.btnCall.setOnClickListener(v -> {
            if (onChatClickListener != null) {
                onChatClickListener.onCallClick(callItem);
            }
        });
    }


    @Override
    public int getItemCount() {
        return callList.size();
    }

    static class CallViewHolder extends RecyclerView.ViewHolder {
        ImageView callImage;
        TextView callName,time;
        Button btnCall;

        public CallViewHolder(@NonNull View itemView) {
            super(itemView);
            callImage = itemView.findViewById(R.id.imageChat);
            callName = itemView.findViewById(R.id.chatName);
            time = itemView.findViewById(R.id.chatMessage);
            btnCall = itemView.findViewById(R.id.btnMessage);
        }
    }
}
