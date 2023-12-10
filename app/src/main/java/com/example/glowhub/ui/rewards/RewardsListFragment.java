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
import androidx.navigation.Navigation;
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
                Navigation.findNavController(v).navigate(R.id.nav_create_edit_rewards);
            }
        });

        rewardsAdapter.setOnItemClickListener(new RewardsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, RewardModel reward) {
                Bundle bundle = new Bundle();
                bundle.putString("reward", reward.getRewardName());
                bundle.putString("price", reward.getRewardPoint()+"");

                Navigation.findNavController(v).navigate(R.id.nav_create_edit_services, bundle);
            }

            @Override
            public void onEditClick(View v, RewardModel reward) {
                Bundle bundle = new Bundle();
                bundle.putString("reward", reward.getRewardName());
                bundle.putString("price", reward.getRewardPoint()+"");

                Navigation.findNavController(v).navigate(R.id.nav_create_edit_services, bundle);
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
