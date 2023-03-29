package com.example.bioscoopapplicatie.presentation;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.shareameal.R;
import com.example.shareameal.domain.Meal;

public class DetailsMedia extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = this.getClass().getSimpleName();
    private Meal meal;
    private ImageView meal_img;
    private TextView cookName_txt;
    private TextView cityName_txt;
    private ImageView isVeganCheckmark_img;
    private ImageView isVegaCheckmark_img;
    private TextView mealName_txt;
    private TextView mealPrice_txt;
    private TextView mealDescription_txt;
    private TextView mealDateServed_txt;
    private Button reserve_btn;
    private int orientation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setLayoutBasedOnOrientation();
        readData();
        setComponents();
        putDataInComponents();
    }

    private void readData() {
        Log.i(TAG, "readData");
        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            this.meal = new Meal(extra.getInt("id"),
                    extra.getString("name"),
                    extra.getString("description"),
                    extra.getBoolean("isActive"),
                    extra.getBoolean("isVega"),
                    extra.getBoolean("isVegan"),
                    extra.getBoolean("isToTakeHome"),
                    extra.getString("dateTime"),
                    extra.getInt("maxAmountOfParticipants"),
                    extra.getDouble("price"),
                    extra.getString("imageUrl"));
        }
    }

    private void setComponents() {
        Log.i(TAG, "setComponents");
        this.meal_img = findViewById(R.id.meal_details_url_imageView);
        this.cookName_txt = findViewById(R.id.meal_details_cook_txt);
        this.cityName_txt = findViewById(R.id.meal_details_city_txt);
        this.isVeganCheckmark_img = findViewById(R.id.meal_details_vegan_checkmark_img);
        this.isVegaCheckmark_img = findViewById(R.id.meal_details_vega_checkmark_img);
        this.mealName_txt = findViewById(R.id.meal_details_name_txt);
        this.mealPrice_txt = findViewById(R.id.meal_details_price_txt);
        this.mealDescription_txt = findViewById(R.id.meal_details_description_txt);
        this.mealDateServed_txt = findViewById(R.id.meal_details_date_served_txt);
        this.reserve_btn = findViewById(R.id.meal_details_reserve_btn);

    }

    private void putDataInComponents() {
        Log.i(TAG, "putDataInComponents");
        Glide.with(this).load(meal.getImageUrl()).into(meal_img);
        this.cookName_txt.setText("Klen");
        this.cityName_txt.setText("Zwijndrecht");
        if (this.meal.getVegan()) {
            this.isVeganCheckmark_img.setImageResource(R.drawable.baseline_check_24);
        } else {
            this.isVeganCheckmark_img.setImageResource(R.drawable.baseline_close_24);
        }
        if (this.meal.getVega()) {
            this.isVegaCheckmark_img.setImageResource(R.drawable.baseline_check_24);
        } else {
            this.isVegaCheckmark_img.setImageResource(R.drawable.baseline_close_24);
        }
        this.mealName_txt.setText(this.meal.getName());
        this.mealPrice_txt.setText(String.valueOf(this.meal.getPrice()));
        this.mealDescription_txt.setText(this.meal.getDescription());
        this.mealDateServed_txt.setText("Served on: " + this.meal.getDate());
        this.reserve_btn.setOnClickListener(this);
    }

    private void setLayoutBasedOnOrientation() {
        Log.i(TAG, "setLayoutBasedOnOrientation");
        this.orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.meal_details_horizontal);
        } else {
            setContentView(R.layout.meal_details_vertical);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.d(TAG, "onConfigurationChanged");
        super.onConfigurationChanged(newConfig);
        setLayoutBasedOnOrientation();
        putDataInComponents();
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick");
        finish();
    }
}