package com.example.glowhub.ui.services;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.glowhub.R;
import com.example.glowhub.ui.rewards.RewardModel;
import com.example.glowhub.ui.rewards.RewardViewModel;

import java.util.List;

public class AddEditServiceFragment extends Fragment {

    private EditText editTextServiceName;
    private EditText editTextServicePrice;
    private ServiceViewModel serviceViewModel;

    private RewardViewModel rewardViewModel;

    private Boolean update = Boolean.FALSE;

    public static AddEditServiceFragment newInstanceForEdit(ServiceModel service) {
        AddEditServiceFragment fragment = new AddEditServiceFragment();
        Bundle args = new Bundle();
        args.putSerializable("SERVICE", service); // Pass ServiceModel as Parcelable
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_edit_service, container, false);

        editTextServiceName = rootView.findViewById(R.id.edit_text_service_name);
        editTextServicePrice = rootView.findViewById(R.id.edit_text_service_price);
        Button buttonSaveService = rootView.findViewById(R.id.button_save_service);
        Button buttonServiceList = rootView.findViewById(R.id.button_service_list);
        final ServiceModel service = new ServiceModel("", 0);
        // Retrieve ServiceModel from arguments and pre-fill the EditText fields
        Bundle args = getArguments();
        System.out.println("**************************args"+args);
        if(args.getString("service_name") != null) {
            service.setServiceName(args.getString("service_name"));
            service.setServicePrice(Double.parseDouble(args.getString("price")));
            update=Boolean.TRUE;
            System.out.println("**************************service"+ service.getServiceName());
        }

        if (!service.getServiceName().equals("")) {
            editTextServiceName.setText(service.getServiceName());
            editTextServicePrice.setText(String.valueOf(service.getServicePrice()));
            buttonSaveService.setText("Update Service");
        }

        buttonSaveService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    saveService(v, service);
            }
        });

        buttonServiceList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
//                fragmentManager.popBackStackImmediate(); // Remove this fragment from the back stack
//
//                ServicesListFragment servicesListFragment = new ServicesListFragment();
//                fragmentManager.beginTransaction()
//                        .replace(R.id.fragment_container, servicesListFragment)
//                        .commit();
                Navigation.findNavController(v).navigate(R.id.nav_services);
            }
        });

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        serviceViewModel = new ViewModelProvider(this).get(ServiceViewModel.class);
        rewardViewModel = new ViewModelProvider(this).get(RewardViewModel.class);

    }

//    private void saveService() {
//        String serviceName = editTextServiceName.getText().toString().trim();
//        String servicePriceString = editTextServicePrice.getText().toString().trim();
//
//        if (!serviceName.isEmpty() && !servicePriceString.isEmpty()) {
//            double servicePrice = Double.parseDouble(servicePriceString);
//            if (servicePrice > 0) {
//                ServiceModel service = new ServiceModel(serviceName, servicePrice);
//                serviceViewModel.insert(service);
//                RewardModel reward = new RewardModel(serviceName, servicePrice*1000);
//                rewardViewModel.insert(reward);
//
//                // Navigate back to ServicesListFragment after saving
//                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
//                fragmentManager.popBackStackImmediate(); // Remove this fragment from the back stack
//
//                ServicesListFragment servicesListFragment = new ServicesListFragment();
//                fragmentManager.beginTransaction()
//                        .replace(R.id.fragment_container, servicesListFragment)
//                        .commit();
//            } else {
//                // Handle negative or zero service price
//                editTextServicePrice.setError("Enter a valid price");
//            }
//        } else {
//            // Handle empty service name or price
//            if (serviceName.isEmpty()) {
//                editTextServiceName.setError("Service name is required");
//            }
//            if (servicePriceString.isEmpty()) {
//                editTextServicePrice.setError("Service price is required");
//            }
//        }
//    }
    private void saveService(View v, ServiceModel service) {
        String serviceName = editTextServiceName.getText().toString().trim();
        String servicePriceString = editTextServicePrice.getText().toString().trim();

        if (!serviceName.isEmpty() && !servicePriceString.isEmpty()) {
            double servicePrice = Double.parseDouble(servicePriceString);
            if (servicePrice > 0) {
                service.setServiceName(serviceName);
                service.setServicePrice(servicePrice);
                if(update) {
                    System.out.println("***********************update*" + service.getServiceName() + service.getServicePrice());
                    serviceViewModel.update(service);
                }
                else {
                    serviceViewModel.insert(service);
                }

                Navigation.findNavController(v).navigate(R.id.nav_services);
            } else {
                // Handle negative or zero service price
                editTextServicePrice.setError("Enter a valid price");
            }
        } else {
            // Handle empty service name or price
            if (serviceName.isEmpty()) {
                editTextServiceName.setError("Service name is required");
            }
            if (servicePriceString.isEmpty()) {
                editTextServicePrice.setError("Service price is required");
            }
        }
    }

}
