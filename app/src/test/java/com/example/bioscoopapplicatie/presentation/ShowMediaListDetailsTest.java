package com.example.bioscoopapplicatie.presentation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import android.content.res.Configuration;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bioscoopapplicatie.R;
import com.example.bioscoopapplicatie.domain.Media;
import com.example.bioscoopapplicatie.presentation.adapter.HomescreenAdapter;
import com.example.bioscoopapplicatie.presentation.viewmodel.MediaListMediaViewModel;
import com.example.bioscoopapplicatie.presentation.viewmodel.MediaViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.junit.Before;
import org.junit.Test;

public class ShowMediaListDetailsTest {

    @Test
    public void testSetViews() {
        ShowMediaListDetails showMediaListDetails = new ShowMediaListDetails();
        showMediaListDetails.setViewModel();

        // Check that search button was set correctly
        Button searchButton = showMediaListDetails.findViewById(R.id.search);
        assertNotNull(searchButton);
        assertEquals(showMediaListDetails, searchButton.getContext());
        assertEquals(View.OnClickListener.class, searchButton.getKeyListener().getClass());
    }

    @Test
    public void testSetRecyclerView() {
        ShowMediaListDetails showMediaListDetails = new ShowMediaListDetails();
        showMediaListDetails.setRecyclerView();

        // Check that recycler view was set correctly
        RecyclerView recyclerView = showMediaListDetails.findViewById(R.id.search_recycler_view);
        assertNotNull(recyclerView);
        assertEquals(showMediaListDetails, recyclerView.getContext());
        assertTrue(recyclerView.getAdapter() instanceof HomescreenAdapter);
        assertTrue(recyclerView.getLayoutManager() instanceof GridLayoutManager);
    }

    @Test
    public void testSetLayoutBasedOnOrientationPortrait() {
        ShowMediaListDetails showMediaListDetails = new ShowMediaListDetails();
        showMediaListDetails.setLayoutBasedOnOrientation();

        // Check that layout was set correctly for portrait mode
        assertEquals(R.layout.search_vertical, showMediaListDetails.findViewById(android.R.id.content).getRootView().getId());
        assertEquals(1, showMediaListDetails.getColumnCount());
        assertEquals(Configuration.ORIENTATION_PORTRAIT, showMediaListDetails.getOrientation());
    }

    @Test
    public void testSetComponents() {
        ShowMediaListDetails showMediaListDetails = new ShowMediaListDetails();
        showMediaListDetails.setComponents();
        // Check that footer buttons and FAB were set correctly
        Button homeScreenButton = showMediaListDetails.findViewById(R.id.homeScreenButton);
        Button listAddButton = showMediaListDetails.findViewById(R.id.listAddButton);
        Button listViewButton = showMediaListDetails.findViewById(R.id.listViewButton);
        FloatingActionButton floatingActionButton = showMediaListDetails.findViewById(R.id.floatingActionButton);
        assertNotNull(homeScreenButton);
        assertNotNull(listAddButton);
        assertNotNull(listViewButton);
        assertNotNull(floatingActionButton);
        assertEquals(showMediaListDetails, homeScreenButton.getContext());
        assertEquals(showMediaListDetails, listAddButton.getContext());
        assertEquals(showMediaListDetails, listViewButton.getContext());
        assertEquals(showMediaListDetails, floatingActionButton.getContext());
        assertEquals(View.OnClickListener.class, homeScreenButton.getKeyListener().getClass());
        assertEquals(View.OnClickListener.class, listAddButton.getKeyListener().getClass());
        assertEquals(View.OnClickListener.class, listViewButton.getKeyListener().getClass());
    }
}