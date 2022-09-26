package com.example.payme20.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.payme20.MainActivity;
import com.example.payme20.R;

public class GroupListView extends AppCompatActivity {

    ImageButton currentGroupsReturnButton;
    LinearLayout groupCardHolder;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_groups);
        initiateClickable();
        setOpenViewListener(this.currentGroupsReturnButton, MainActivity.class);
    }

    private void initiateClickable() {
        this.currentGroupsReturnButton = findViewById(R.id.currentGorupsReturnButton);
        this.groupCardHolder = findViewById(R.id.groupListLinearLayout);
    }

    private void setOpenViewListener(ImageView openButton, Class<?> viewToOpen){
        openButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(GroupListView.this, viewToOpen);
                GroupListView.this.startActivity(myIntent);
            }
        });
    }

}
