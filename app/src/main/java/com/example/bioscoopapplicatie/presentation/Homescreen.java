//package com.example.bioscoopapplicatie.presentation;
//
//import android.content.res.Configuration;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageView;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.lifecycle.Observer;
//import androidx.lifecycle.ViewModelProvider;
//import androidx.recyclerview.widget.GridLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.bioscoopapplicatie.R;
//import com.google.android.material.snackbar.Snackbar;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Homescreen extends AppCompatActivity implements View.OnClickListener {
//    private final String TAG = this.getClass().getSimpleName();
//    //private MealViewModel mealViewModel;
//    private RecyclerView recyclerView;
//    //private HomescreenAdapter adapter;
//    private GridLayoutManager mLayoutManager;
//    private Button filterVegetarian_btn;
//    private Button filterVegan_btn;
//    private Button filterTakeHome_btn;
//    private Button filterClear_btn;
//    private ImageView addMeal_img;
//    private int filter = 4;
//    private int orientation;
//    private int columnCount;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        Log.d(TAG, "onCreate");
//        super.onCreate(savedInstanceState);
//        setLayoutBasedOnOrientation();
//        setViews();
//        setRecyclerView();
//        setViewModel(filter);
//    }
//
//    private void setViews() {
//        Log.i(TAG, "setViews");
////        filterVegetarian_btn = findViewById(R.id.meal_main_vega_btn);
////        filterVegan_btn = findViewById(R.id.meal_main_vegan_btn);
////        filterTakeHome_btn = findViewById(R.id.meal_main_take_home_btn);
////        filterClear_btn = findViewById(R.id.meal_main_clear_btn);
////        addMeal_img = findViewById(R.id.meal_main_add_btn);
//
//        filterVegetarian_btn.setOnClickListener(this);
//        filterVegan_btn.setOnClickListener(this);
//        filterTakeHome_btn.setOnClickListener(this);
//        filterClear_btn.setOnClickListener(this);
//        addMeal_img.setOnClickListener(this);
//    }
//
//    private void setRecyclerView() {
//        Log.i(TAG, "setRecyclerView");
//        recyclerView = findViewById(R.id.meal_main_recycler_view);
//        adapter = new HomescreenAdapter(this);
//        mLayoutManager = new GridLayoutManager(this, columnCount);
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setAdapter(adapter);
//    }
//    private void setViewModel(int filter) {
//        Log.i(TAG, "setViewModel");
//        mealViewModel = new ViewModelProvider(this).get(MealViewModel.class);
//        switch (filter) {
//            case 1:
//                mealViewModel.getVegetarianMeals().observe(this, new Observer<List<Meal>>() {
//                    @Override
//                    public void onChanged(@Nullable final List<Meal> meals) {
//                        adapter.setData((ArrayList<Meal>) meals);
//                        Snackbar.make(recyclerView, String.valueOf(meals.size() + " Meals read"), Snackbar.LENGTH_LONG)
//                                .setAction("Action", null).show();
//                    }
//                });
//                break;
//            case 2:
//                mealViewModel.getVeganMeals().observe(this, new Observer<List<Meal>>() {
//                    @Override
//                    public void onChanged(@Nullable final List<Meal> meals) {
//                        adapter.setData((ArrayList<Meal>) meals);
//                        Snackbar.make(recyclerView, String.valueOf(meals.size() + " Meals read"), Snackbar.LENGTH_LONG)
//                                .setAction("Action", null).show();
//                    }
//                });
//                break;
//            case 3:
//                mealViewModel.getTakeHomeMeals().observe(this, new Observer<List<Meal>>() {
//                    @Override
//                    public void onChanged(@Nullable final List<Meal> meals) {
//                        adapter.setData((ArrayList<Meal>) meals);
//                        Snackbar.make(recyclerView, String.valueOf(meals.size() + " Meals read"), Snackbar.LENGTH_LONG)
//                                .setAction("Action", null).show();
//                    }
//                });
//                break;
//            case 4:
//                mealViewModel.getAllMeals().observe(this, new Observer<List<Meal>>() {
//                    @Override
//                    public void onChanged(@Nullable final List<Meal> meals) {
//                        adapter.setData((ArrayList<Meal>) meals);
//                        Snackbar.make(recyclerView, String.valueOf(meals.size() + " Meals read"), Snackbar.LENGTH_LONG)
//                                .setAction("Action", null).show();
//                    }
//                });
//                break;
//        }
//    }
//
//    private void setLayoutBasedOnOrientation() {
//        Log.i(TAG, "setLayoutBasedOnOrientation");
//        this.orientation = getResources().getConfiguration().orientation;
//        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            this.columnCount = 2;
//            setContentView(R.layout.meal_main_horizontal);
//        } else {
//            this.columnCount = 1;
//            setContentView(R.layout.meal_main_vertical);
//        }
//    }
//
//    @Override
//    public void onClick(View v) {
//
//    }
//
//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        // Check the orientation and update the number of columns in the layout manager accordingly
//        setLayoutBasedOnOrientation();
//        setViews();
//        setRecyclerView();
//        setViewModel(filter);
//    }
//}