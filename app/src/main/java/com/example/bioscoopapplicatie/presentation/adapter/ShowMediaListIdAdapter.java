package com.example.bioscoopapplicatie.presentation.adapter;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bioscoopapplicatie.R;
import com.example.bioscoopapplicatie.domain.MediaList;
import com.example.bioscoopapplicatie.presentation.ShowMediaListDetails;
import com.example.bioscoopapplicatie.presentation.viewmodel.MediaListViewModel;

import java.util.ArrayList;
import java.util.List;

public class ShowMediaListIdAdapter extends RecyclerView.Adapter<ShowMediaListIdAdapter.MediaViewHolder> {
    private static final String TAG = ShowMediaListIdAdapter.class.getSimpleName();
    private List<MediaList> mediaLists;
    private LayoutInflater inflater;
    private Context context;
    private MediaListViewModel mediaListViewModel;
    public ShowMediaListIdAdapter(Context context, MediaListViewModel mediaListViewModel) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.mediaListViewModel = mediaListViewModel;
    }

    @NonNull
    @Override
    public MediaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        // Inflate an item view.
        View mItemView = inflater.inflate(R.layout.show_media_list_number_item, parent, false);
        return new MediaViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MediaViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder nr " + position);
        holder.number_txt.setText("List: " + position);
        holder.name_txt.setText("Created By: " + this.mediaLists.get(position).getName());
        holder.favorite_count_txt.setText("Favorite Count: " + this.mediaLists.get(position).getFavoriteCount());
        holder.description_txt.setText(this.mediaLists.get(position).getDescription());
        holder.itemView.setOnClickListener(view -> {
            Log.d(TAG, "ViewHolder onClick - listitem nr " + position);
            MediaList currentMediaList = mediaLists.get(position);
            Intent showMediaListDetailsIntent = new Intent(context, ShowMediaListDetails.class);
            showMediaListDetailsIntent.putExtra("id", currentMediaList.getId());
            showMediaListDetailsIntent.putExtra("name", currentMediaList.getName());
            showMediaListDetailsIntent.putExtra("description", currentMediaList.getDescription());
            showMediaListDetailsIntent.putExtra("favoriteCount", currentMediaList.getFavoriteCount());
            showMediaListDetailsIntent.putExtra("listNumber", position);
            showMediaListDetailsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(showMediaListDetailsIntent);
        });
        holder.delete_btn.setOnClickListener(view -> {
            MediaList currentMediaList = mediaLists.get(position);
            Log.d(TAG, "ViewHolder onClick - delete listitem nr " + currentMediaList.getId());
            mediaListViewModel.deleteList(currentMediaList, currentMediaList.getId());
        });
    }

    @Override
    public int getItemCount() {
        if(this.mediaLists == null)
            return 0;
        else return mediaLists.size();
    }

    public void setData(ArrayList<MediaList> mediaLists) {
        Log.d(TAG, "setData(mediaLists)");
        this.mediaLists = mediaLists;
        notifyDataSetChanged();
    }

    static class MediaViewHolder extends RecyclerView.ViewHolder {
        private final String TAG = MediaViewHolder.class.getSimpleName();
        public TextView number_txt;
        public TextView name_txt;
        public TextView favorite_count_txt;
        public TextView description_txt;
        public Button delete_btn;

        public MediaViewHolder(@NonNull View itemView) {
            super(itemView);
            number_txt = itemView.findViewById(R.id.show_media_list_number_item_txt);
            name_txt = itemView.findViewById(R.id.show_media_list_number_item_name_txt);
            favorite_count_txt = itemView.findViewById(R.id.show_media_list_number_item_favorite_count_txt);
            description_txt = itemView.findViewById(R.id.show_media_list_number_item_description_txt);
            delete_btn = itemView.findViewById(R.id.deleteListButton);
        }
    }
}