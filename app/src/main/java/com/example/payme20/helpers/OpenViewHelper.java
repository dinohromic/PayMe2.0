package com.example.payme20.helpers;

import android.content.Context;
import android.content.Intent;

import com.example.payme20.model.Group;
import com.example.payme20.views.GroupPageView;

public class OpenViewHelper  {

    public static void openView(Class<?> newView, Context currentView){
        Intent newIntent = new Intent(currentView, newView);
        currentView.startActivity(newIntent);
    }
    public static void openView(Class<?> newView, Context currentView, Group group){
        Intent newIntent = new Intent(currentView, newView);
        newIntent.putExtra("GROUP", group);
        currentView.startActivity(newIntent);
    }

}
