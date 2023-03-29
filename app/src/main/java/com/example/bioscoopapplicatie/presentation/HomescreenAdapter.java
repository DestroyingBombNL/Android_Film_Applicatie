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
import com.example.shareameal.R;
import com.example.shareameal.domain.Meal;

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
        View mItemView = inflater.inflate(R.layout., parent, false);
        return new MediaViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MediaViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder nr " + position);

    }

    @Override
    public int getItemCount() {
        if(this.media == null)
            return 0;
        else return media.size();
    }

    public void setData(ArrayList<Media> media) {
        Log.d(TAG, "setData(meals)");
        this.media = media;
        notifyDataSetChanged();
    }

    class MediaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final String TAG = MediaViewHolder.class.getSimpleName();
        public ImageView image;
        public TextView name;
        public TextView cityDate;
        public TextView price;

        public MediaViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.d(TAG, "ViewHolder onClick - listitem nr " + getAdapterPosition());
            Media currentMedia = media.get(getAdapterPosition());

            detailsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(detailsIntent);
        }
    }
}