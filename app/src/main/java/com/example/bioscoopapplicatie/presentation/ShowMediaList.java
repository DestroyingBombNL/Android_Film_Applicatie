package com.example.bioscoopapplicatie.presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bioscoopapplicatie.R;
import com.example.bioscoopapplicatie.domain.Media;
import com.example.bioscoopapplicatie.domain.MediaList;
import com.example.bioscoopapplicatie.domain.User;
import com.example.bioscoopapplicatie.presentation.adapter.HomescreenAdapter;
import com.example.bioscoopapplicatie.presentation.adapter.ShowMediaListIdAdapter;
import com.example.bioscoopapplicatie.presentation.viewmodel.MediaListViewModel;
import com.example.bioscoopapplicatie.presentation.viewmodel.MediaViewModel;
import com.example.bioscoopapplicatie.presentation.viewmodel.UserViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class ShowMediaList extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = this.getClass().getSimpleName();
    private ImageView logo_img;
    private EditText search_fld;

    private RecyclerView recyclerViewId;
    private RecyclerView recyclerViewMedia;
    private ShowMediaListIdAdapter adapter;
    private HomescreenAdapter homescreenAdapter;
    private GridLayoutManager mLayoutManager;
    private MediaListViewModel mediaListViewModel;

    /*private Button bar_home_btn;
    private Button bar_create_media_list_btn;
    private Button bar_show_media_list_btn;*/

    private Spinner filter_spn;
    private Button share_btn;
    private int orientation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setLayoutBasedOnOrientation();
        setComponents();
        setViewModel();
        setrecyclerViewId();
    }

    private void setComponents() {
        Log.i(TAG, "setComponents");
        recyclerViewId = findViewById(R.id.show_media_list_recycler_horizontal);
        recyclerViewMedia = findViewById(R.id.show_media_list_recycler_vertical);
        /*
        this.logo_img = findViewById(R.id.logo_img);
        this.logo_img.setOnClickListener(this);
        this.search_fld = findViewById(R.id.search_fld);
        this.search_fld.setOnClickListener(this);
        this.share_btn = findViewById(R.id.show_media_list_share_btn);
        this.share_btn.setOnClickListener(this);
        this.bar_home_btn = findViewById(R.id.create_media_list_bar_home_btn);
        this.bar_home_btn.setOnClickListener(this);
        this.bar_create_media_list_btn = findViewById(R.id.create_media_list_bar_btn);
        this.bar_create_media_list_btn.setOnClickListener(this);
        this.bar_show_media_list_btn = findViewById(R.id.create_media_list_bar_show_media_list_btn);
        */
    }

    private void setViewModel() {
        Log.i(TAG, "setViewModel");
        this.mediaListViewModel = new ViewModelProvider(this).get(MediaListViewModel.class);
        mediaListViewModel.getAllMediaLists().observe(this, new Observer<List<MediaList>>() {
            @Override
            public void onChanged(List<MediaList> mediaLists) {
                adapter.setData((ArrayList<MediaList>) mediaLists);
                Snackbar.make(recyclerViewId, String.valueOf(mediaLists.size() + " MediaLists read"), Snackbar.LENGTH_LONG);
            }
        });
    }

    private void setrecyclerViewId() {
        Log.i(TAG, "setrecyclerViewId");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);

        homescreenAdapter = new HomescreenAdapter(this);
        recyclerViewMedia.setLayoutManager(mLayoutManager);
        recyclerViewMedia.setAdapter(homescreenAdapter);

        adapter = new ShowMediaListIdAdapter(getApplicationContext(), homescreenAdapter, this, this);
        recyclerViewId.setLayoutManager(linearLayoutManager);
        recyclerViewId.setAdapter(adapter);
    }

    private void setLayoutBasedOnOrientation() {
        Log.i(TAG, "setLayoutBasedOnOrientation");
        this.orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //setContentView(R.layout.show_media_list_horizontal);
        } else {
            setContentView(R.layout.show_media_list_vertical);
        }
    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, "onClick");
        switch (view.getId()) {
            case R.id.logo_img:
                /*Intent intent = new Intent(this, Homescreen.class);
                startActivity(intent);*/
                break;
            case R.id.search_fld:
                /*
                Intent intent = new Intent(this, SearchMedia.class);
                startActivity(intent);*/
                break;
            case R.id.show_media_list_share_btn:
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "Jelle & Co's latest development for the hit cinema is a new app, click here to download!");
                startActivity(Intent.createChooser(intent, "Choose one"));
                break;
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setLayoutBasedOnOrientation();
    }
}