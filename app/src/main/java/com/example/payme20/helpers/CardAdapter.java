package com.example.payme20.helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.payme20.R;
import com.example.payme20.model.Group;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<Group> groupList;


    public CardAdapter(Context context, ArrayList<Group> groupList) {
        this.context = context;
        this.groupList = groupList;
    }

    @NonNull
    @Override
    public CardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new ViewHolder(view, groupList.get(viewType));
    }

    public void onBindViewHolder(@NonNull CardAdapter.ViewHolder holder, int position) {
        // to set data to textview and imageview of each card layout
        Group group = groupList.get(position);
        holder.groupName.setText(group.getGroupName());
    }

    @Override
    public int getItemCount() {
        // this method is used for showing number of card items in recycler view
        return groupList.size();
    }



    // View holder class for initializing of your views such as TextView and Imageview
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView groupName;
        private final Group group;
        public ViewHolder(@NonNull View itemView, Group group) {
            super(itemView);
            this.group = group;
            this.groupName = itemView.findViewById(R.id.cardTestName);
        }
    }
}
