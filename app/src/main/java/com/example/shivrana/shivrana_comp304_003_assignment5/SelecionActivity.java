package com.example.shivrana.shivrana_comp304_003_assignment5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SelecionActivity extends AppCompatActivity {
    ListView selectionList;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecion);

        declaration();

        //Populating the ListView
        populatingListView();

        //ListView events
        ListViewEvents();
    }

    public void populatingListView(){
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        adp.addAll(getResources().getStringArray(R.array.Brand));
        selectionList.setAdapter(adp);
    }

    public void ListViewEvents(){
        selectionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String brand = parent.getItemAtPosition(position).toString();
                intent.putExtra("Brand",brand);
                SharedPreferences.Editor editor = getSharedPreferences("myData", MODE_PRIVATE).edit();
                editor.putString("Brand",brand);
                editor.commit();
                startActivity(intent);
            }
        });
    }

    public void declaration(){
        intent = new Intent(this,ShowRoomSelectionActivity.class);
        selectionList = findViewById(R.id.brandSelection);
    }
}
