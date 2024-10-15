package com.example.cartandorder.comment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cartandorder.R;

import java.util.ArrayList;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ReviewViewHolder> {

    private List<Comment> originalReviewList;
    private List<Comment> filteredReviewList;
    private Context context;

    public CommentAdapter(List<Comment> reviewList, Context context) {
        this.originalReviewList = reviewList;
        this.filteredReviewList = new ArrayList<>(reviewList);
        this.context = context; // Nhận context từ activity
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        Comment review = filteredReviewList.get(position);
        holder.tvName.setText(review.getName());
        holder.tvReview.setText(review.getReviewText());
        holder.ratingBar.setRating(review.getRating());
        holder.tvLikeCount.setText(String.valueOf(review.getLikeCount()));
        holder.tvDislikeCount.setText(String.valueOf(review.getDislikeCount()));
        Glide.with(holder.itemView.getContext())
                .load(review.getImageProfile())
                .into(holder.image);


        holder.btnLike.setOnClickListener(v -> {
            if (holder.isLikeToggled) {
                holder.btnLike.setBackgroundTintList(ContextCompat.getColorStateList(context, R.color.grey));
                holder.tvLikeCount.setText(String.valueOf(review.getLikeCount() - 1));
            } else {
                holder.btnLike.setBackgroundTintList(ContextCompat.getColorStateList(context, R.color.blue));
                holder.tvLikeCount.setText(String.valueOf(review.getLikeCount() + 1));
            }
            holder.isLikeToggled = !holder.isLikeToggled; // Toggle trạng thái
        });

        // Nút Dislike
        holder.btnDislike.setOnClickListener(v -> {
            if (holder.isDislikeToggled) {
                holder.btnDislike.setBackgroundTintList(ContextCompat.getColorStateList(context, R.color.grey));
                holder.tvDislikeCount.setText(String.valueOf(review.getDislikeCount() - 1));
            } else {
                holder.btnDislike.setBackgroundTintList(ContextCompat.getColorStateList(context, R.color.blue));
                holder.tvDislikeCount.setText(String.valueOf(review.getDislikeCount() + 1));
            }
            holder.isDislikeToggled = !holder.isDislikeToggled; // Toggle trạng thái
        });
    }

    @NonNull
    @Override
    public CommentAdapter.ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return filteredReviewList.size();
    }

    public void filterByStars(int stars) {
        filteredReviewList.clear();
        for (Comment review : originalReviewList) {
            if (review.getRating() == stars) {
                filteredReviewList.add(review);
            }
        }
        notifyDataSetChanged();
    }

    static class ReviewViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvReview, tvLikeCount, tvDislikeCount;
        RatingBar ratingBar;
        ImageView image;
        Button btnLike, btnDislike;
        boolean isLikeToggled = false;
        boolean isDislikeToggled = false;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvReview = itemView.findViewById(R.id.tvReview);
            tvLikeCount = itemView.findViewById(R.id.tvLikeCount);
            tvDislikeCount = itemView.findViewById(R.id.tvDislikeCount);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            image = itemView.findViewById(R.id.profile);
            btnLike = itemView.findViewById(R.id.btnLike);
            btnDislike = itemView.findViewById(R.id.btnDislike);
        }
    }
}
