package com.example.shivrana.shivrana_comp304_003_assignment5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button start;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Declaration of all controls and other components
        declaration();

        //Events
        BtnClickEvents();
    }

    public void BtnClickEvents(){
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);

            }
        });
    }

    public void declaration(){
        start = findViewById(R.id.EnterBtn);
        intent = new Intent(this,SelecionActivity.class);

    }
}
