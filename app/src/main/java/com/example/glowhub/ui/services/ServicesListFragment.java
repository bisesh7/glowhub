package com.example.glowhub.ui.services;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.glowhub.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ServicesListFragment extends Fragment {

    private ServiceViewModel serviceViewModel;
    private ServicesAdapter servicesAdapter;
    private List<ServiceModel> servicesList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_services_list, container, false);
        servicesList = getDummyServiceData();

        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view_services);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        servicesAdapter = new ServicesAdapter(servicesList);
        recyclerView.setAdapter(servicesAdapter);

        FloatingActionButton fabAddService = rootView.findViewById(R.id.fab_add_service);
        fabAddService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                try {
//                    rootView.setVisibility(View.GONE);
//                    // Replace ServicesListFragment with AddEditServiceFragment
//                    Fragment addEditFragment = new AddEditServiceFragment();
//                    FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
//                    transaction.replace(R.id.fragment_container, addEditFragment);
//                    transaction.addToBackStack(null); // Optional: Add transaction to back stack
//                    transaction.commit();
//                } catch (Exception e) {
//                    // Log the exception or show a message to indicate an error occurred
//                    e.printStackTrace();
//                }
                Navigation.findNavController(v).navigate(R.id.nav_create_edit_services);
            }
        });

        servicesAdapter.setOnItemClickListener(new ServicesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ServiceModel service, View v) {
                Bundle bundle = new Bundle();
//                bundle.putSerializable("SERVICE", service);
                bundle.putString("service_name", service.getServiceName());
                bundle.putString("price", service.getServicePrice()+"");

                Navigation.findNavController(v).navigate(R.id.nav_create_edit_services, bundle);
            }

            @Override
            public void onEditClick(ServiceModel service, View v) {
//                Fragment addEditFragment = AddEditServiceFragment.newInstanceForEdit(service);
//                getChildFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.fragment_container, addEditFragment)
//                        .addToBackStack(null)
//                        .commit();
//                try {
//                    rootView.setVisibility(View.GONE);
//                    // Handle item click - navigate to AddEditServiceFragment for editing
//                    Fragment addEditFragment = AddEditServiceFragment.newInstanceForEdit(service);
////                    Fragment addEditFragment = new AddEditServiceFragment();
//                    FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
//                    transaction.replace(R.id.fragment_container, addEditFragment);
//                    transaction.addToBackStack(null); // Optional: Add transaction to back stack
//                    transaction.commit();
//                } catch (Exception e) {
//                    // Log the exception or show a message to indicate an error occurred
//                    e.printStackTrace();
//                }
                // Prepare the data to pass
                Bundle bundle = new Bundle();
//                bundle.putSerializable("SERVICE", service);
                bundle.putString("service_name", service.getServiceName());
                bundle.putString("price", service.getServicePrice()+"");// Replace "key" with your key and "value" with your data
                Navigation.findNavController(v).navigate(R.id.nav_create_edit_services, bundle);
            }

            @Override
            public void onDeleteClick(ServiceModel service) {
                // Handle the delete click action here
                serviceViewModel.delete(service); // Delete the service using your ViewModel
            }
        });

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        serviceViewModel = new ViewModelProvider(this).get(ServiceViewModel.class);
        serviceViewModel.getAllServices().observe(getViewLifecycleOwner(), new Observer<List<ServiceModel>>() {
            @Override
            public void onChanged(List<ServiceModel> serviceModels) {
                servicesAdapter.setServices(serviceModels);
            }
        });
    }
    private List<ServiceModel> getDummyServiceData() {
        List<ServiceModel> dummyList = new ArrayList<>();
        // Add dummy service data for testing
        dummyList.add(new ServiceModel("Service 1", 21.5));
        dummyList.add(new ServiceModel("Service 2", 25.5));
        dummyList.add(new ServiceModel("Service 3", 35.5));
        // Add more services as needed
        return dummyList;
    }



}
