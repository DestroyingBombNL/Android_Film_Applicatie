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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class ShowMediaList extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = this.getClass().getSimpleName();
    private RecyclerView recyclerView;
    private ShowMediaListIdAdapter adapter;
    private GridLayoutManager mLayoutManager;
    private MediaListViewModel mediaListViewModel;
    private int orientation;
    private ImageButton homeScreenButton, listAddButton, listViewButton;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setLayoutBasedOnOrientation();
        setComponents();
        setViewModel();
        setRecyclerView();
    }

    private void setComponents() {
        Log.i(TAG, "setComponents");
        recyclerView = findViewById(R.id.show_media_list_recycler_vertical);

        //Homepage footer
        this.homeScreenButton = findViewById(R.id.homeScreenButton);
        this.homeScreenButton.setOnClickListener(this);

        //List_add footer
        this.listAddButton = findViewById(R.id.listAddButton);
        this.listAddButton.setOnClickListener(this);

        //List button footer
        this.listViewButton = findViewById(R.id.listViewButton);
        this.listViewButton.setOnClickListener(this);

        //Fab
        this.floatingActionButton = findViewById(R.id.floatingActionButton);
        this.floatingActionButton.setOnClickListener(this);
    }

    private void setViewModel() {
        Log.i(TAG, "setViewModel");
        this.mediaListViewModel = new ViewModelProvider(this).get(MediaListViewModel.class);
        mediaListViewModel.getAllMediaLists().observe(this, mediaLists -> {
            adapter.setData((ArrayList<MediaList>) mediaLists);
            Snackbar.make(recyclerView, mediaLists.size() + " MediaLists read", Snackbar.LENGTH_LONG);
        });
    }

    private void setRecyclerView() {
        Log.i(TAG, "setRecyclerViewId");
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        adapter = new ShowMediaListIdAdapter(getApplicationContext());
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
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
            case R.id.homeScreenButton:
                Intent intent = new Intent(this, Homescreen.class);
                startActivity(intent);
                break;
            case R.id.listAddButton:
                Intent intentAdd = new Intent(this, CreateMediaList.class);
                startActivity(intentAdd);
                break;
            case R.id.listViewButton:
                Intent intentListView = new Intent(this, ShowMediaList.class);
                startActivity(intentListView);
                break;
                /*
            case R.id.floatingActionButton:
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                String title = "Sharing media info!";
                String text = "The list is called: " + mediaList.getName() + "\n" +
                        "This is what it is about: " + mediaList.getDescription();
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, title);
                shareIntent.putExtra(Intent.EXTRA_TEXT, text);
                startActivity(Intent.createChooser(shareIntent, "Share via"));
                break;*/
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setLayoutBasedOnOrientation();
    }
}