package com.example.farkas.laundryapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.farkas.laundryapp.model.DataItem;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Farko on 11/15/2017.
 */

public class DataItemAdapterListView extends ArrayAdapter<DataItem> {

    List<DataItem> mDataItems;
    LayoutInflater mInflater;

    public DataItemAdapterListView(@NonNull Context context, @NonNull List<DataItem> objects) {
        super(context, R.layout.list_item, objects);

        mDataItems = objects;
        mInflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, parent, false);
        }
        TextView tvName = (TextView) convertView.findViewById(R.id.itemName);
        TextView tvCount = (TextView) convertView.findViewById(R.id.currentCountEntry);

        DataItem item = mDataItems.get(position);
        tvName.setText(item.getItemName());
        tvCount.setText(Integer.toString(item.getCurrentCount()));



        return convertView;
    }

}
