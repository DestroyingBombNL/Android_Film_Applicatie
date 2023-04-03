package com.example.bioscoopapplicatie.presentation.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bioscoopapplicatie.R;
import com.example.bioscoopapplicatie.domain.Media;
import com.example.bioscoopapplicatie.domain.MediaList;
import com.example.bioscoopapplicatie.presentation.DetailsMedia;
import com.example.bioscoopapplicatie.presentation.viewmodel.MediaListMediaViewModel;
import com.example.bioscoopapplicatie.presentation.viewmodel.MediaViewModel;
import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ShowMediaListIdAdapter extends RecyclerView.Adapter<ShowMediaListIdAdapter.MediaViewHolder> {
    private static final String TAG = ShowMediaListIdAdapter.class.getSimpleName();
    private List<MediaList> mediaLists;
    private LayoutInflater inflater;
    private Context context;
    private HomescreenAdapter homescreenAdapter;
    private ViewModelStoreOwner viewModelStoreOwner;
    private MediaListMediaViewModel mediaListMediaViewModel;
    private LifecycleOwner lifecycleOwner;
    public ShowMediaListIdAdapter(Context context, HomescreenAdapter homescreenAdapter, ViewModelStoreOwner viewModelStoreOwner, LifecycleOwner lifecycleOwner) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.viewModelStoreOwner = viewModelStoreOwner;
        this.lifecycleOwner = lifecycleOwner;
        this.homescreenAdapter = new HomescreenAdapter(context);
        setViewModel();
        setVerticalRecyclerView(1);
    }

    private void setViewModel() {
        Log.i(TAG, "setViewModel");
        this.mediaListMediaViewModel = new ViewModelProvider(viewModelStoreOwner).get(MediaListMediaViewModel.class);
        setVerticalRecyclerView(1);
    }

    private void setVerticalRecyclerView(int currentMediaListId) {
        mediaListMediaViewModel.getAllMediaInList(String.valueOf(currentMediaListId)).observe(lifecycleOwner, new Observer<List<Media>>() {
            @Override
            public void onChanged(List<Media> media) {
                homescreenAdapter.setData((ArrayList<Media>) media);
            }
        });
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

    class MediaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final String TAG = MediaViewHolder.class.getSimpleName();
        public TextView number_txt;
        public MediaViewHolder(@NonNull View itemView) {
            super(itemView);
            number_txt = itemView.findViewById(R.id.show_media_list_number_item_txt);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.d(TAG, "ViewHolder onClick - list-item nr " + getAdapterPosition());
            MediaList currentMediaList = mediaLists.get(getAdapterPosition());
            setVerticalRecyclerView(currentMediaList.getId());
        }
    }
}