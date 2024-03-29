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

public class ShowMediaListTest {
    @Test
    public void testSetRecyclerView() {
        ShowMediaList showMediaList = new ShowMediaList();
        showMediaList.setRecyclerView();

        // Check that recycler view was set correctly
        RecyclerView recyclerView = showMediaList.findViewById(R.id.search_recycler_view);
        assertNotNull(recyclerView);
        assertEquals(showMediaList, recyclerView.getContext());
        assertTrue(recyclerView.getAdapter() instanceof HomescreenAdapter);
        assertTrue(recyclerView.getLayoutManager() instanceof GridLayoutManager);
    }

    @Test
    public void testSetComponents() {
        ShowMediaList showMediaList = new ShowMediaList();
        showMediaList.setComponents();
        // Check that footer buttons and FAB were set correctly
        Button homeScreenButton = showMediaList.findViewById(R.id.homeScreenButton);
        Button listAddButton = showMediaList.findViewById(R.id.listAddButton);
        Button listViewButton = showMediaList.findViewById(R.id.listViewButton);
        FloatingActionButton floatingActionButton = showMediaList.findViewById(R.id.floatingActionButton);
        assertNotNull(homeScreenButton);
        assertNotNull(listAddButton);
        assertNotNull(listViewButton);
        assertNotNull(floatingActionButton);
        assertEquals(showMediaList, homeScreenButton.getContext());
        assertEquals(showMediaList, listAddButton.getContext());
        assertEquals(showMediaList, listViewButton.getContext());
        assertEquals(showMediaList, floatingActionButton.getContext());
        assertEquals(View.OnClickListener.class, homeScreenButton.getKeyListener().getClass());
        assertEquals(View.OnClickListener.class, listAddButton.getKeyListener().getClass());
        assertEquals(View.OnClickListener.class, listViewButton.getKeyListener().getClass());
    }
}