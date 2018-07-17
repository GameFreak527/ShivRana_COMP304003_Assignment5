package com.example.shivrana.shivrana_comp304_003_assignment5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ShowRoomSelectionActivity extends AppCompatActivity {
    ListView showRoomList;
    String selectedBrand;
    Intent intent;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_room_selection);

        declaration();
        AdapterSelection();


      ListEvents();
    }

    public void ListEvents(){
        showRoomList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent.putExtra("ShowRoom",parent.getItemAtPosition(position).toString());
                startActivity(intent);
            }
        });
    }

    public void populateListView(ArrayAdapter adp){
        showRoomList.setAdapter(adp);
    }

    public void AdapterSelection(){
        selectedBrand = getIntent().getStringExtra("Brand");

        switch (selectedBrand){
            case "Ford":
                arrayAdapter.addAll(getResources().getStringArray(R.array.Ford));
                populateListView(arrayAdapter);
                break;
            case "Toyota":
                arrayAdapter.addAll(getResources().getStringArray(R.array.Toyota));
                populateListView(arrayAdapter);
                break;
            case "Honda":
                arrayAdapter.addAll(getResources().getStringArray(R.array.Honda));
                populateListView(arrayAdapter);
                break;
            case "Chevrolet":
                arrayAdapter.addAll(getResources().getStringArray(R.array.Chevrolet));
                populateListView(arrayAdapter);
                break;
            case "Nissan":
                arrayAdapter.addAll(getResources().getStringArray(R.array.Nissan));
                populateListView(arrayAdapter);
                break;
                default:

        }

    }

    public void declaration(){

        showRoomList = findViewById(R.id.showRoomList);
        intent = new Intent(this,MapsActivity.class);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

    }
}
