package com.example.glowhub.ui.services;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.glowhub.R;

import java.util.ArrayList;
import java.util.List;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ServiceViewHolder> {

    private List<ServiceModel> serviceList = new ArrayList<>();
    private OnItemClickListener itemClickListener;

    public interface OnItemClickListener {
        void onItemClick(ServiceModel service);
        void onEditClick(ServiceModel service);
        void onDeleteClick(ServiceModel service);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }

    public ServicesAdapter(List<ServiceModel> serviceList) {
        this.serviceList = serviceList;
    }


    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_service, parent, false);
        return new ServiceViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {
        ServiceModel currentService = serviceList.get(position);
        holder.textViewServiceName.setText(currentService.getServiceName());
        holder.textViewServicePrice.setText(String.valueOf(currentService.getServicePrice()));
        holder.itemView.setOnClickListener(view -> {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(currentService);
            }
        });
        // Handle edit button click
        holder.buttonEditService.setOnClickListener(v -> {
            if (itemClickListener != null) {
                itemClickListener.onEditClick(currentService);
            }
        });

        // Handle delete button click
        holder.buttonDeleteService.setOnClickListener(v -> {
            if (itemClickListener != null) {
                itemClickListener.onDeleteClick(currentService);
            }
        });
    }

    @Override
    public int getItemCount() {
        return serviceList.size();
    }

    public void setServices(List<ServiceModel> services) {
        this.serviceList = services;
        notifyDataSetChanged();
    }

    static class ServiceViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewServiceName;
        private TextView textViewServicePrice;
        ImageButton buttonEditService;
        ImageButton buttonDeleteService;

        public ServiceViewHolder(View itemView) {
            super(itemView);
            textViewServiceName = itemView.findViewById(R.id.text_view_service_name);
            textViewServicePrice = itemView.findViewById(R.id.text_view_service_price);
            buttonEditService = itemView.findViewById(R.id.button_edit_service);
            buttonDeleteService = itemView.findViewById(R.id.button_delete_service);
        }
        public interface OnItemClickListener {
            void onEditClick(ServiceModel service);

            void onDeleteClick(ServiceModel service);
        }
    }
}
