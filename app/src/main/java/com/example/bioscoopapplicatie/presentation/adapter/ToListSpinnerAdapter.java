package com.example.bioscoopapplicatie.presentation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bioscoopapplicatie.R;
import com.example.bioscoopapplicatie.domain.MediaList;

import java.util.List;

public class ToListSpinnerAdapter extends BaseAdapter {

    private Context context;
    private List<MediaList> mediaList;

    public ToListSpinnerAdapter(Context context, List<MediaList> mediaList) {
        this.context = context;
        this.mediaList = mediaList;
    }

    @Override
    public int getCount() {
        return mediaList != null ? mediaList.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return mediaList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.item_list, viewGroup, false);

        TextView txtName = rootView.findViewById(R.id.name_list);
        txtName.setText(mediaList.get(i).getName());

        return txtName;
    }
}
