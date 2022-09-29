package com.example.payme20.helpers;

import android.content.Context;
import android.content.Intent;

import com.example.payme20.model.Event;
import com.example.payme20.model.Group;
import com.example.payme20.model.Member;


public class OpenViewHelper  {

    public static void openView(Class<?> newView, Context currentView){
        Intent newIntent = new Intent(currentView, newView);
        currentView.startActivity(newIntent);
    }
    public static void openViewPutExtra(Class<?> newView, Context currentView, Group group){
        Intent newIntent = new Intent(currentView, newView);
        newIntent.putExtra("GROUP_KEY", group);
        currentView.startActivity(newIntent);
    }
    public static void openViewPutExtra(Class<?> newView, Context currentView, Event event){
        Intent newIntent = new Intent(currentView, newView);
        newIntent.putExtra("EVENT_KEY", event);
        currentView.startActivity(newIntent);
    }
    public static void openViewPutExtra(Class<?> newView, Context currentView, Member member){
        Intent newIntent = new Intent(currentView, newView);
        newIntent.putExtra("MEMBER_KEY", member);
        currentView.startActivity(newIntent);
    }


}
