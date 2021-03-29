package com.example.app1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import static android.widget.Toast.*;

public class MainActivity extends AppCompatActivity {


    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView listView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                addItem(view);
            }
        });

        items = new ArrayList<>();
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(itemsAdapter);  // sets data behind the listView
        setUpListViewListener();

    }

    private void setUpListViewListener(){
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Context context = getApplicationContext();
                Toast.makeText(context, "Item removed", LENGTH_LONG).show();

                items.remove(i);
                itemsAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    // Fügt neuen Eintrag ein
    private void addItem(View view){
        EditText input = findViewById(R.id.editText); // initialisiert input mit editText Textfeld
        String itemText = input.getText().toString(); // nimmt den Text von input wandelt in String um und speichert in itemText

        if(!(itemText.equals(""))){
            itemsAdapter.add(itemText);
            input.setText("");
        }
        else{
            Toast.makeText(getApplicationContext(), "Please enter text...", LENGTH_LONG).show();
        }
    }
}