package com.example.payme20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.payme20.views.GroupPageView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*
        * You probably shouldn't open a new page like this, but this works for now*/
        Button button = findViewById(R.id.groupPageButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(MainActivity.this, GroupPageView.class);
                MainActivity.this.startActivity(myIntent);
            }
        });

    }
}