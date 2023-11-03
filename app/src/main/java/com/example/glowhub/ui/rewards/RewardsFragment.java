package com.example.glowhub.ui.rewards;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.glowhub.databinding.FragmentRewardsBinding;
import com.example.glowhub.ui.rewards.RewardsViewModel;

public class RewardsFragment extends Fragment {

    private FragmentRewardsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RewardsViewModel rewardsViewModel =
                new ViewModelProvider(this).get(RewardsViewModel.class);

        binding = FragmentRewardsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textRewards;
        rewardsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}