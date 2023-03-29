package com.example.bioscoopapplicatie.presentation;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shareameal.R;
import com.example.shareameal.domain.Meal;

import java.util.ArrayList;
import java.util.List;

public class HomescreenAdapter extends RecyclerView.Adapter<HomescreenAdapter.MediaViewHolder> {
    private static final String TAG = HomescreenAdapter.class.getSimpleName();
    private List<Meal> meals;
    private LayoutInflater inflater;
    private Context context;

    public HomescreenAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MediaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        // Inflate an item view.
        View mItemView = inflater.inflate(R.layout.meal_item_menu, parent, false);
        return new MediaViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MediaViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder nr " + position);
        Meal meal = meals.get(position);
        Glide.with(holder.itemView).load(meal.getImageUrl()).into(holder.image);
        holder.name.setText(meal.getName());
        holder.cityDate.setText("Zwijndrecht / " + meal.getDate());
        holder.price.setText("€" + String.valueOf(meal.getPrice()));
    }

    @Override
    public int getItemCount() {
        if(this.meals == null)
            return 0;
        else return meals.size();
    }

    public void setData(ArrayList<Meal> meals) {
        Log.d(TAG, "setData(meals)");
        this.meals = meals;
        notifyDataSetChanged();
    }

    class MediaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final String TAG = MediaViewHolder.class.getSimpleName();
        public ImageView image;
        public TextView name;
        public TextView cityDate;
        public TextView price;

        public MediaViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.meal_item_menu_img);
            name = itemView.findViewById(R.id.meal_item_menu_name_txt);
            cityDate = itemView.findViewById(R.id.meal_item_menu_city_date_txt);
            price = itemView.findViewById(R.id.meal_item_menu_price_txt);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            Log.d(TAG, "ViewHolder onClick - listitem nr " + getAdapterPosition());
            Meal currentMeal = meals.get(getAdapterPosition());
            Intent detailsIntent = new Intent(context, DetailsMedia.class);
            detailsIntent.putExtra("id", currentMeal.getId());
            detailsIntent.putExtra("name", currentMeal.getName());
            detailsIntent.putExtra("description", currentMeal.getDescription());
            detailsIntent.putExtra("isActive", currentMeal.getActive());
            detailsIntent.putExtra("isVega", currentMeal.getVega());
            detailsIntent.putExtra("isVegan", currentMeal.getVegan());
            detailsIntent.putExtra("isToTakeHome", currentMeal.getToTakeHome());
            detailsIntent.putExtra("dateTime", currentMeal.getDateTime());
            detailsIntent.putExtra("maxAmountOfParticipants", currentMeal.getMaxAmountOfParticipants());
            detailsIntent.putExtra("price", currentMeal.getPrice());
            detailsIntent.putExtra("imageUrl", currentMeal.getImageUrl());
            detailsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(detailsIntent);
        }
    }
}