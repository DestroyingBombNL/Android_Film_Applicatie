package com.example.bioscoopapplicatie.presentation;

import static org.junit.Assert.*;

import android.content.res.Configuration;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bioscoopapplicatie.R;
import com.example.bioscoopapplicatie.presentation.adapter.HomescreenAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.junit.Test;

public class CreateMediaListTest {
    @Test
    public void testSetViews() {
        CreateMediaList CreateMediaList = new CreateMediaList();
        CreateMediaList.setViewModel();

        // Check that search button was set correctly
        Button searchButton = CreateMediaList.findViewById(R.id.search);
        assertNotNull(searchButton);
        assertEquals(CreateMediaList, searchButton.getContext());
        assertEquals(View.OnClickListener.class, searchButton.getKeyListener().getClass());
    }

    @Test
    public void testSetComponents() {
        CreateMediaList CreateMediaList = new CreateMediaList();
        CreateMediaList.setComponents();
        // Check that footer buttons and FAB were set correctly
        Button homeScreenButton = CreateMediaList.findViewById(R.id.homeScreenButton);
        Button listAddButton = CreateMediaList.findViewById(R.id.listAddButton);
        Button listViewButton = CreateMediaList.findViewById(R.id.listViewButton);
        FloatingActionButton floatingActionButton = CreateMediaList.findViewById(R.id.floatingActionButton);
        assertNotNull(homeScreenButton);
        assertNotNull(listAddButton);
        assertNotNull(listViewButton);
        assertNotNull(floatingActionButton);
        assertEquals(CreateMediaList, homeScreenButton.getContext());
        assertEquals(CreateMediaList, listAddButton.getContext());
        assertEquals(CreateMediaList, listViewButton.getContext());
        assertEquals(CreateMediaList, floatingActionButton.getContext());
        assertEquals(View.OnClickListener.class, homeScreenButton.getKeyListener().getClass());
        assertEquals(View.OnClickListener.class, listAddButton.getKeyListener().getClass());
        assertEquals(View.OnClickListener.class, listViewButton.getKeyListener().getClass());
    }
}