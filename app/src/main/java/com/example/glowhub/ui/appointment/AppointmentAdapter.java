package com.example.glowhub.ui.appointment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.glowhub.R;

import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder> {

    private List<Appointment> appointmentList;

    public AppointmentAdapter(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    // ViewHolder class
    public static class AppointmentViewHolder extends RecyclerView.ViewHolder {
        TextView appointmentTitleTextView;
        TextView appointmentDateTimeTextView;

        public AppointmentViewHolder(@NonNull View itemView) {
            super(itemView);
            appointmentTitleTextView = itemView.findViewById(R.id.appointmentTitleTextView);
            appointmentDateTimeTextView = itemView.findViewById(R.id.appointmentDateTimeTextView);
        }
    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_appointment, parent, false);
        return new AppointmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {
        Appointment appointment = appointmentList.get(position);

        // Bind appointment data to views
        holder.appointmentTitleTextView.setText(appointment.getTitle());
        holder.appointmentDateTimeTextView.setText(appointment.getDateTime());
        // Bind other appointment details if needed
    }

    @Override
    public int getItemCount() {
        return appointmentList.size();
    }
}
