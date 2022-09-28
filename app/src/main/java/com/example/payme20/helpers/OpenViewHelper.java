package com.example.payme20.helpers;

import android.content.Context;
import android.content.Intent;

public class OpenViewHelper  {

    public void openView(Class<?> newView, Context currentView){
        Intent newIntent = new Intent(currentView, newView);
        currentView.startActivity(newIntent);
    }
}
