package com.example.bioscoopapplicatie.presentation;

import static org.junit.Assert.*;

import android.content.res.Configuration;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bioscoopapplicatie.R;
import com.example.bioscoopapplicatie.domain.DataOrder;
import com.example.bioscoopapplicatie.domain.Genre;
import com.example.bioscoopapplicatie.presentation.adapter.GenreSpinnerAdapter;
import com.example.bioscoopapplicatie.presentation.adapter.HomescreenAdapter;
import com.example.bioscoopapplicatie.presentation.viewmodel.MediaViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class HomescreenTest {
        private Homescreen homescreen = new Homescreen();
        @Test
        public void testGenreSpinnerInitialState() {
                assertNotNull(homescreen.getSpinner_genre());
                assertNull(homescreen.getSpinner_genre().getAdapter());
                assertNull(homescreen.getSpinner_genre().getSelectedItem());
                assertFalse(homescreen.getSpinner_genre().isEnabled());
        }

        @Test
        public void testGenreSpinnerWithData() {
                List<Genre> genres = new ArrayList<>();
                genres.add(new Genre(1, "Action"));
                genres.add(new Genre(2, "Comedy"));
                homescreen.runOnUiThread(() -> {
                        GenreSpinnerAdapter genreAdapter = new GenreSpinnerAdapter(homescreen, genres);
                        homescreen.getSpinner_genre().setAdapter(genreAdapter);
                });
                assertNotNull(homescreen.getSpinner_genre().getAdapter());
                assertEquals(2, homescreen.getSpinner_genre().getAdapter().getCount());
                assertEquals(1, homescreen.getSpinner_genre().getSelectedItem()); // Assuming position 1 is selected
                assertTrue(homescreen.getSpinner_genre().isEnabled());
        }

        @Test
        public void testOrderSpinnerInitialState() {
                assertNotNull(homescreen.getSpinner_order());
                assertNotNull(homescreen.getAdapter());
                assertEquals(DataOrder.getOrderList().size(), homescreen.getSpinner_order().getAdapter().getCount());
                assertEquals("0", homescreen.getSpinner_order().getSelectedItem().toString()); // Assuming the default order is "0"
                assertTrue(homescreen.getSpinner_order().isEnabled());
        }

        @Test
        public void testOrderSpinnerDefaultSelection() {
                homescreen.runOnUiThread(() -> {
                        homescreen.getSpinner_order().setSelection(0); // Select "0"
                });
                assertNotNull(homescreen.getMediaViewModel().getAllMedia().getValue());
                assertEquals(homescreen.getMediaViewModel().getAllMedia().getValue().size(), homescreen.getAdapter().getItemCount());
        }

        @Test
        public void testSetViews() {
                Homescreen homescreen = new Homescreen();
                homescreen.setViews();

                // Check that search button was set correctly
                Button searchButton = homescreen.findViewById(R.id.search);
                assertNotNull(searchButton);
                assertEquals(homescreen, searchButton.getContext());
                assertEquals(View.OnClickListener.class, searchButton.getKeyListener().getClass());
        }

        @Test
        public void testSetRecyclerView() {
                Homescreen homescreen = new Homescreen();
                homescreen.setRecyclerView();

                // Check that recycler view was set correctly
                RecyclerView recyclerView = homescreen.findViewById(R.id.homescreen_recycler);
                assertNotNull(recyclerView);
                assertEquals(homescreen, recyclerView.getContext());
                assertTrue(recyclerView.getAdapter() instanceof HomescreenAdapter);
                assertTrue(recyclerView.getLayoutManager() instanceof GridLayoutManager);
        }

        @Test
        public void testSetViewModel() {
                Homescreen homescreen = new Homescreen();
                homescreen.setViewModel();

                // Check that view model was set correctly
                MediaViewModel mediaViewModel = homescreen.getMediaViewModel();
                assertNotNull(mediaViewModel);
                assertTrue(mediaViewModel.getAllMedia().hasObservers());
        }

        @Test
        public void testSetLayoutBasedOnOrientationPortrait() {
                Homescreen homescreen = new Homescreen();
                homescreen.setLayoutBasedOnOrientation();

                // Check that layout was set correctly for portrait mode
                assertEquals(R.layout.homescreen_vertical, homescreen.findViewById(android.R.id.content).getRootView().getId());
                assertEquals(1, homescreen.columnCount);
                assertEquals(Configuration.ORIENTATION_PORTRAIT, homescreen.orientation);
        }

        @Test
        public void testSetComponents() {
                Homescreen homescreen = new Homescreen();
                homescreen.setComponents();
                // Check that footer buttons and FAB were set correctly
                Button homeScreenButton = homescreen.findViewById(R.id.homeScreenButton);
                Button listAddButton = homescreen.findViewById(R.id.listAddButton);
                Button listViewButton = homescreen.findViewById(R.id.listViewButton);
                FloatingActionButton floatingActionButton = homescreen.findViewById(R.id.floatingActionButton);
                assertNotNull(homeScreenButton);
                assertNotNull(listAddButton);
                assertNotNull(listViewButton);
                assertNotNull(floatingActionButton);
                assertEquals(homescreen, homeScreenButton.getContext());
                assertEquals(homescreen, listAddButton.getContext());
                assertEquals(homescreen, listViewButton.getContext());
                assertEquals(homescreen, floatingActionButton.getContext());
                assertEquals(View.OnClickListener.class, homeScreenButton.getKeyListener().getClass());
                assertEquals(View.OnClickListener.class, listAddButton.getKeyListener().getClass());
                assertEquals(View.OnClickListener.class, listViewButton.getKeyListener().getClass());
        }
}