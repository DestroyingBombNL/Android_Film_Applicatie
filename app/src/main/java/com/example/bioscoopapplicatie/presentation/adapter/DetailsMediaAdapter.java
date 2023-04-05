package com.example.bioscoopapplicatie.presentation.adapter;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bioscoopapplicatie.R;
import com.example.bioscoopapplicatie.domain.AuthorDetail;
import com.example.bioscoopapplicatie.domain.Review;
import com.example.bioscoopapplicatie.presentation.viewmodel.AuthorDetailViewModel;

import java.util.ArrayList;
import java.util.List;

public class DetailsMediaAdapter extends RecyclerView.Adapter<DetailsMediaAdapter.ReviewViewHolder> {
    private static final String TAG = DetailsMediaAdapter.class.getSimpleName();
    private List<Review> review;
    private ArrayList<AuthorDetail> allAuthorDetails;
    private LayoutInflater inflater;
    private Context context;
    private AuthorDetailViewModel authorDetailViewModel;

    public DetailsMediaAdapter(Context context, Application application) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        // Inflate an item view.
        View mItemView = inflater.inflate(R.layout.details_media_item, parent, false);
        return new ReviewViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder nr " + position);
        Review reviewItem = this.review.get(position);
        if (!(reviewItem == null)) {
            holder.author_name_txt.setText(reviewItem.getAuthor());
            holder.created_date_txt.setText(reviewItem.getCreatedAt().split("T")[0]);
            holder.description_txt.setText(reviewItem.getDescription());
            if (!(allAuthorDetails == null)) {
                for (AuthorDetail authorDetail : allAuthorDetails) {
                    if (authorDetail.getName().contains(reviewItem.getAuthor())) {
                        double rating = authorDetail.getRating();
                        holder.rating_bar.setRating((float) rating / 2);
                        break;
                    }
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        if(this.review == null)
            return 0;
        else return review.size();
    }

    public void setReviewData(ArrayList<Review> review) {
        Log.d(TAG, "setData(reviewList)");
        this.review = review;
        notifyDataSetChanged();
    }

    public void setAuthorDetailData(ArrayList<AuthorDetail> authorDetails) {
        Log.d(TAG, "setData (allAuthorDetails)");
        this.allAuthorDetails = authorDetails;
        notifyDataSetChanged();
    }

    class ReviewViewHolder extends RecyclerView.ViewHolder {

        private final String TAG = ReviewViewHolder.class.getSimpleName();
        public TextView author_name_txt;
        public TextView created_date_txt;
        public TextView description_txt;
        public RatingBar rating_bar;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            rating_bar = itemView.findViewById(R.id.details_media_item_rating_bar);
            this.author_name_txt = itemView.findViewById(R.id.details_media_item_name_txt);
            this.created_date_txt = itemView.findViewById(R.id.details_media_item_reviewed_at_txt);
            this.description_txt = itemView.findViewById(R.id.details_media_item_description_txt);
        }
    }
}