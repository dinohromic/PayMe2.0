package com.example.payme20.helpers;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.payme20.MainActivity;

/**
 *Tanken bakom denna klassen är att den ska hjälpa
 * views att öppna nya views genom att skapa intent åt dem.
 * Detta kan skilja sig så detta skulle kunna innebära flera
 * overloadade metoder
 */

public class OpenViewHelper  {

    public void openView(Class<?> newView, Context currentView){
        Intent newIntent = new Intent();
    }
}
