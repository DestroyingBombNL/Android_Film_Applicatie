package com.example.bioscoopapplicatie.presentation;

import static org.junit.Assert.*;

import android.content.res.Configuration;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bioscoopapplicatie.R;
import com.example.bioscoopapplicatie.presentation.adapter.HomescreenAdapter;
import com.example.bioscoopapplicatie.presentation.viewmodel.MediaViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.junit.Test;

public class SearchMediaTest {

    @Test
    public void testSetViews() {
        SearchMedia searchMedia = new SearchMedia();
        searchMedia.setViews();

        // Check that search button was set correctly
        Button searchButton = searchMedia.findViewById(R.id.search);
        assertNotNull(searchButton);
        assertEquals(searchMedia, searchButton.getContext());
        assertEquals(View.OnClickListener.class, searchButton.getKeyListener().getClass());
    }

    @Test
    public void testSetRecyclerView() {
        SearchMedia searchMedia = new SearchMedia();
        searchMedia.setRecyclerView();

        // Check that recycler view was set correctly
        RecyclerView recyclerView = searchMedia.findViewById(R.id.search_recycler_view);
        assertNotNull(recyclerView);
        assertEquals(searchMedia, recyclerView.getContext());
        assertTrue(recyclerView.getAdapter() instanceof HomescreenAdapter);
        assertTrue(recyclerView.getLayoutManager() instanceof GridLayoutManager);
    }

    @Test
    public void testSetViewModel() {
        SearchMedia searchMedia = new SearchMedia();
        searchMedia.setViewModel();

        // Check that view model was set correctly
        MediaViewModel mediaViewModel = searchMedia.getMediaViewModel();
        assertNotNull(mediaViewModel);
        assertTrue(mediaViewModel.getAllMedia().hasObservers());
    }

    @Test
    public void testSetLayoutBasedOnOrientationPortrait() {
        SearchMedia searchMedia = new SearchMedia();
        searchMedia.setLayoutBasedOnOrientation();

        // Check that layout was set correctly for portrait mode
        assertEquals(R.layout.search_vertical, searchMedia.findViewById(android.R.id.content).getRootView().getId());
        assertEquals(1, searchMedia.getColumnCount());
        assertEquals(Configuration.ORIENTATION_PORTRAIT, searchMedia.getOrientation());
    }

    @Test
    public void testSetComponents() {
        SearchMedia searchMedia = new SearchMedia();
        searchMedia.setComponents();
        // Check that footer buttons and FAB were set correctly
        Button homeScreenButton = searchMedia.findViewById(R.id.homeScreenButton);
        Button listAddButton = searchMedia.findViewById(R.id.listAddButton);
        Button listViewButton = searchMedia.findViewById(R.id.listViewButton);
        FloatingActionButton floatingActionButton = searchMedia.findViewById(R.id.floatingActionButton);
        assertNotNull(homeScreenButton);
        assertNotNull(listAddButton);
        assertNotNull(listViewButton);
        assertNotNull(floatingActionButton);
        assertEquals(searchMedia, homeScreenButton.getContext());
        assertEquals(searchMedia, listAddButton.getContext());
        assertEquals(searchMedia, listViewButton.getContext());
        assertEquals(searchMedia, floatingActionButton.getContext());
        assertEquals(View.OnClickListener.class, homeScreenButton.getKeyListener().getClass());
        assertEquals(View.OnClickListener.class, listAddButton.getKeyListener().getClass());
        assertEquals(View.OnClickListener.class, listViewButton.getKeyListener().getClass());
    }
}