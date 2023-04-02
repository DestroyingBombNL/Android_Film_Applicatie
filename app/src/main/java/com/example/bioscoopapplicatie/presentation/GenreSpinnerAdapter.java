package com.example.bioscoopapplicatie.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bioscoopapplicatie.R;
import com.example.bioscoopapplicatie.domain.Genre;

import java.util.List;

public class GenreSpinnerAdapter extends BaseAdapter {
    private Context context;
    private List<Genre> genreList;

    public GenreSpinnerAdapter(Context context, List<Genre> genreList) {
        this.context = context;
        this.genreList = genreList;
    }

    @Override
    public int getCount() {
        return genreList != null ? genreList.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.item_genre, viewGroup, false);

        TextView txtName = rootView.findViewById(R.id.name_genre);
        txtName.setText(genreList.get(i).getName());

        return rootView;
    }
}
