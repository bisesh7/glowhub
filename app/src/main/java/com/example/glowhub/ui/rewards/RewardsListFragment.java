package com.example.glowhub.ui.rewards;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.glowhub.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class RewardsListFragment extends Fragment {

    private RewardViewModel rewardViewModel;
    private RewardsAdapter rewardsAdapter;
    private List<RewardModel> rewardsList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_rewards_list, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view_rewards);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        rewardsAdapter = new RewardsAdapter(rewardsList);
        recyclerView.setAdapter(rewardsAdapter);

        FloatingActionButton fabAddReward = rootView.findViewById(R.id.fab_add_reward);
        fabAddReward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    rootView.setVisibility(View.GONE);
                    // Replace RewardsListFragment with AddEditRewardFragment
                    Fragment addEditFragment = new AddEditRewardFragment();
                    FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, addEditFragment);
                    transaction.addToBackStack(null); // Optional: Add transaction to back stack
                    transaction.commit();
                } catch (Exception e) {
                    // Log the exception or show a message to indicate an error occurred
                    e.printStackTrace();
                }
            }
        });

        rewardsAdapter.setOnItemClickListener(new RewardsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RewardModel reward) {
                try {
//                rootView.setVisibility(View.GONE);
                // Handle item click - navigate to AddEditRewardFragment for editing
//                Fragment addEditFragment = AddEditRewardFragment.newInstanceForEdit(reward);
                Fragment addEditFragment = new AddEditRewardFragment();
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, addEditFragment);
                transaction.addToBackStack(null); // Optional: Add transaction to back stack
                transaction.commit();
                } catch (Exception e) {
                    // Log the exception or show a message to indicate an error occurred
                    e.printStackTrace();
                }
            }

            @Override
            public void onEditClick(RewardModel reward) {
//                Fragment addEditFragment = AddEditRewardFragment.newInstanceForEdit(reward);
//                getChildFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.fragment_container, addEditFragment)
//                        .addToBackStack(null)
//                        .commit();
                try {
                    rootView.setVisibility(View.GONE);
                    // Handle item click - navigate to AddEditRewardFragment for editing
                    Fragment addEditFragment = AddEditRewardFragment.newInstanceForEdit(reward);
//                    Fragment addEditFragment = new AddEditRewardFragment();
                    FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, addEditFragment);
                    transaction.addToBackStack(null); // Optional: Add transaction to back stack
                    transaction.commit();
                } catch (Exception e) {
                    // Log the exception or show a message to indicate an error occurred
                    e.printStackTrace();
                }
            }

            @Override
            public void onDeleteClick(RewardModel reward) {
                // Handle the delete click action here
                rewardViewModel.delete(reward); // Delete the reward using your ViewModel
            }
        });

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rewardViewModel = new ViewModelProvider(this).get(RewardViewModel.class);
        rewardViewModel.getAllRewards().observe(getViewLifecycleOwner(), new Observer<List<RewardModel>>() {
            @Override
            public void onChanged(List<RewardModel> rewardModels) {
                rewardsAdapter.setRewards(rewardModels);
            }
        });
    }



}
