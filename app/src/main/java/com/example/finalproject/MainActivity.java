package com.example.finalproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText itemNameEditText;
    private EditText itemQuantityEditText;
    private Button addItemButton;
    private ListView inventoryListView;

    private Database databaseHelper;
    private InventoryAdapter inventoryAdapter;
    private List<InventoryItem> inventoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        addItemButton = findViewById(R.id.addItemButton);
        inventoryListView = findViewById(R.id.inventoryListView);

        databaseHelper = new Database(this);

        // Load inventory from database
        inventoryList = databaseHelper.getAllItems();
        inventoryAdapter = new InventoryAdapter(this, inventoryList);
        inventoryListView.setAdapter(inventoryAdapter);

        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = itemNameEditText.getText().toString();
                String quantityStr = itemQuantityEditText.getText().toString();

                if (name.isEmpty() || quantityStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter item name and quantity", Toast.LENGTH_SHORT).show();
                    return;
                }

                int quantity = Integer.parseInt(quantityStr);

                // Add item to database
                InventoryItem newItem = new InventoryItem(name, quantity);
                databaseHelper.addItem(newItem);

                // Update UI
                inventoryList.add(newItem);
                inventoryAdapter.notifyDataSetChanged();

                // Clear input fields
                itemNameEditText.setText("");
                itemQuantityEditText.setText("");

                Toast.makeText(MainActivity.this, "Item added", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

