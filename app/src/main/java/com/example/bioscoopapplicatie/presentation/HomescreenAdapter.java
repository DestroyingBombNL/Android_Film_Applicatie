package com.example.bioscoopapplicatie.presentation;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bioscoopapplicatie.R;
import com.example.bioscoopapplicatie.domain.Media;

import java.util.ArrayList;
import java.util.List;

public class HomescreenAdapter extends RecyclerView.Adapter<HomescreenAdapter.MediaViewHolder> {
    private static final String TAG = HomescreenAdapter.class.getSimpleName();
    private List<Media> media;
    private LayoutInflater inflater;
    private Context context;

    public HomescreenAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MediaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        // Inflate an item view.
        View mItemView = inflater.inflate(R.layout.show_media_list_item, parent, false);
        return new MediaViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MediaViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder nr " + position);
        Media mediaItem = this.media.get(position);
        holder.title.setText(mediaItem.getTitle());
        holder.voteAverage.setText("Rating: " + String.valueOf(mediaItem.getVoteAverage()));
        holder.popularity.setText("Popularity: " + String.valueOf((int) (mediaItem.getPopularity() / 100)) + "%");
        Glide.with(holder.itemView).load("https://image.tmdb.org/t/p/original" + mediaItem.getPosterPath()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        if(this.media == null)
            return 0;
        else return media.size();
    }

    public void setData(ArrayList<Media> media) {
        Log.d(TAG, "setData(mediaList)");
        this.media = media;
        notifyDataSetChanged();
    }

    class MediaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final String TAG = MediaViewHolder.class.getSimpleName();
        public ImageView image;
        public TextView title;
        public TextView voteAverage;
        public TextView popularity;


        public MediaViewHolder(@NonNull View itemView) {
            super(itemView);
            //image = itemView.findViewById(R.id.show_media_list_item_img);
            title = itemView.findViewById(R.id.homescreen_item_txt);
            voteAverage = itemView.findViewById(R.id.show_media_list_item_vote_average_txt);
            popularity = itemView.findViewById(R.id.show_media_list_item_popularity_txt);
            image = itemView.findViewById(R.id.show_media_list_item_img);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            Log.d(TAG, "ViewHolder onClick - listitem nr " + getAdapterPosition());
            Media currentMedia = media.get(getAdapterPosition());
            Intent detailsIntent = new Intent(context, DetailsMedia.class);
            detailsIntent.putExtra("id", currentMedia.getId());
            detailsIntent.putExtra("title", currentMedia.getTitle());
            detailsIntent.putExtra("language", currentMedia.getOriginalLanguage());
            detailsIntent.putExtra("overview", currentMedia.getOverview());
            detailsIntent.putExtra("popularity", currentMedia.getPopularity());
            detailsIntent.putExtra("releaseDate", currentMedia.getReleaseDate());
            detailsIntent.putExtra("adult", currentMedia.isAdult());
            detailsIntent.putExtra("backdropPath", currentMedia.getBackdropPath());
            detailsIntent.putExtra("posterPath", currentMedia.getPosterPath());
            detailsIntent.putExtra("video", currentMedia.isVideo());
            detailsIntent.putExtra("voteAverage", currentMedia.getVoteAverage());
            detailsIntent.putExtra("voteCount", currentMedia.getVoteCount());
            detailsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(detailsIntent);
        }
    }
}