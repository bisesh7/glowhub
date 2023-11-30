package com.example.glowhub.ui.stylist_management;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.glowhub.R;

import java.util.List;

public class StylistAdapter extends RecyclerView.Adapter<StylistAdapter.StylistViewHolder> {
    private List<Stylist> stylistList;

    public class StylistViewHolder extends RecyclerView.ViewHolder {
        TextView stylistNameTextView;
        TextView stylistSpecializationTextView;

        public StylistViewHolder(@NonNull View itemView) {
            super(itemView);

            // Initialize views for each item in the list
            stylistNameTextView = itemView.findViewById(R.id.stylist_name_text_view);
            stylistSpecializationTextView = itemView.findViewById(R.id.stylist_specialization_text_view);
        }
    }

    public StylistAdapter(List<Stylist> stylistList) {
        this.stylistList = stylistList;
    }

    @NonNull
    @Override
    public StylistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each item in the list
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.stylist_item_layout, parent, false);
        return new StylistViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StylistViewHolder holder, int position) {
        // Bind data to the views in each item
        Stylist stylist = stylistList.get(position);
        // Set data to views in the ViewHolder
        holder.stylistNameTextView.setText(stylist.getName());
        holder.stylistSpecializationTextView.setText(stylist.getSpecialization());
    }

    @Override
    public int getItemCount() {
        return stylistList.size();
    }
}
