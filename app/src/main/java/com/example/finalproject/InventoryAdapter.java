package com.example.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ArrayAdapter;

import java.util.List;

public class InventoryAdapter extends ArrayAdapter<InventoryItem> {

    public InventoryAdapter(Context context, List<InventoryItem> inventoryList) {
        super(context, 0, inventoryList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        }

        InventoryItem item = getItem(position);

        TextView nameTextView = convertView.findViewById(android.R.id.text1);
        TextView quantityTextView = convertView.findViewById(android.R.id.text2);

        nameTextView.setText(item.getName());
        quantityTextView.setText("Quantity: " + item.getQuantity());

        return convertView;
    }
}

