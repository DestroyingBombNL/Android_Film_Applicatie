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

public class DetailsMediaTest {

    @Test
    public void testSetRecyclerView() {
        DetailsMedia detailsMedia = new DetailsMedia();
        detailsMedia.setRecyclerView();

        // Check that recycler view was set correctly
        RecyclerView recyclerView = detailsMedia.findViewById(R.id.search_recycler_view);
        assertNotNull(recyclerView);
        assertEquals(detailsMedia, recyclerView.getContext());
        assertTrue(recyclerView.getAdapter() instanceof HomescreenAdapter);
        assertTrue(recyclerView.getLayoutManager() instanceof GridLayoutManager);
    }

    @Test
    public void testSetComponents() {
        DetailsMedia detailsMedia = new DetailsMedia();
        detailsMedia.setComponents();
        // Check that footer buttons and FAB were set correctly
        Button homeScreenButton = detailsMedia.findViewById(R.id.homeScreenButton);
        Button listAddButton = detailsMedia.findViewById(R.id.listAddButton);
        Button listViewButton = detailsMedia.findViewById(R.id.listViewButton);
        FloatingActionButton floatingActionButton = detailsMedia.findViewById(R.id.floatingActionButton);
        assertNotNull(homeScreenButton);
        assertNotNull(listAddButton);
        assertNotNull(listViewButton);
        assertNotNull(floatingActionButton);
        assertEquals(detailsMedia, homeScreenButton.getContext());
        assertEquals(detailsMedia, listAddButton.getContext());
        assertEquals(detailsMedia, listViewButton.getContext());
        assertEquals(detailsMedia, floatingActionButton.getContext());
        assertEquals(View.OnClickListener.class, homeScreenButton.getKeyListener().getClass());
        assertEquals(View.OnClickListener.class, listAddButton.getKeyListener().getClass());
        assertEquals(View.OnClickListener.class, listViewButton.getKeyListener().getClass());
    }
}