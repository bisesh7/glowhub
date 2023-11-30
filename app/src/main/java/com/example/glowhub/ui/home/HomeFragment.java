package com.example.glowhub.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.glowhub.R;
import com.example.glowhub.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextView servicesTextView = root.findViewById(R.id.services_btn);
        TextView stylistsTextView = root.findViewById(R.id.stylists_btn);

        // Set OnClickListener for Services TextView
        servicesTextView.setOnClickListener(v -> {
            Log.d("Services", "Clicked");
//            navigateToServices();
        });

        // Set OnClickListener for Stylists TextView
        stylistsTextView.setOnClickListener(v -> {
            navigateToStylists();
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void navigateToServices() {
        // Navigate to Services Fragment
//        Navigation.findNavController(requireView()).navigate(R.id);

    }

    private void navigateToStylists() {
        Navigation.findNavController(requireView()).navigate(R.id.stylists);
    }
}