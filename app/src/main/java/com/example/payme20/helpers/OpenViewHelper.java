package com.example.payme20.helpers;

import android.content.Context;
import android.content.Intent;
import androidx.fragment.app.FragmentActivity;
import com.example.payme20.model.Event;
import com.example.payme20.model.Group;
import com.example.payme20.model.Member;
import com.example.payme20.views.GroupCreateView;
import com.example.payme20.views.GroupListView;

/**
 * This class is used to reuse code for opening a new view in the application
 */
public class OpenViewHelper  {

    public static void openView(Class<?> newView, Context currentView){
        Intent newIntent = new Intent(currentView, newView);
        currentView.startActivity(newIntent);
    }
    public static void openViewPutExtra(Class<?> newView, FragmentActivity currentFragment, Member member, Group group){
        Intent newIntent = new Intent(currentFragment, newView);
        newIntent.putExtra("GROUP_KEY", group);
        newIntent.putExtra("MEMBER_KEY", member);
        currentFragment.startActivity(newIntent);
    }
    public static void openViewPutExtra(Class<?> newView, FragmentActivity currentFragment, Event event, Group group){
        Intent newIntent = new Intent(currentFragment, newView);
        newIntent.putExtra("GROUP_KEY", group);
        newIntent.putExtra("EVENT_KEY", event);
        currentFragment.startActivity(newIntent);
    }
    public static void openViewPutExtra(Class<?> newView, FragmentActivity currentFragment, Group group){
        Intent newIntent = new Intent(currentFragment, newView);
        newIntent.putExtra("GROUP_KEY", group);
        currentFragment.startActivity(newIntent);
    }
    public static void openViewPutExtra(Class<?> newView, Context currentView, String groupName){
        Intent newIntent = new Intent(currentView, newView);
        newIntent.putExtra("GROUP_NAME_KEY", groupName);
        currentView.startActivity(newIntent);
    }

    public static void openViewPutExtra(Class<?> newView, Context currentView, String groupName, int id){
        Intent newIntent = new Intent(currentView, newView);
        newIntent.putExtra("GROUP_NAME_KEY", groupName);
        newIntent.putExtra("FRAGMENT_ID", id);
        currentView.startActivity(newIntent);
    }
    public static void openViewPutExtra(Class<?> newView, Context currentView, Event event){
        Intent newIntent = new Intent(currentView, newView);
        newIntent.putExtra("EVENT_KEY", event);
        currentView.startActivity(newIntent);
    }

    public static void openViewPutExtra(Class<?> newView, Context currentView) {
        Intent newIntent = new Intent(currentView, newView);
        currentView.startActivity(newIntent);
    }
}
