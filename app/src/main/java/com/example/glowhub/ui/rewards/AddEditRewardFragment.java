package com.example.glowhub.ui.rewards;

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
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.glowhub.R;

public class AddEditRewardFragment extends Fragment {

    private EditText editTextRewardName;
    private EditText editTextRewardPrice;
    private RewardViewModel rewardViewModel;

    private Boolean update = Boolean.FALSE;

    public static AddEditRewardFragment newInstanceForEdit(RewardModel reward) {
        AddEditRewardFragment fragment = new AddEditRewardFragment();
        Bundle args = new Bundle();
        args.putSerializable("SERVICE", reward); // Pass RewardModel as Parcelable
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_edit_reward, container, false);

        editTextRewardName = rootView.findViewById(R.id.edit_text_reward_name);
        editTextRewardPrice = rootView.findViewById(R.id.edit_text_reward_point);
        Button buttonSaveReward = rootView.findViewById(R.id.button_save_reward);
        Button buttonRewardList = rootView.findViewById(R.id.button_reward_list);
        RewardModel reward = new RewardModel("", 0);
        Bundle args = getArguments();
        if(args.getString("reward") != null) {
            reward.setRewardName(args.getString("reward"));
            reward.setRewardPoint(Double.parseDouble(args.getString("point")));
            update=Boolean.TRUE;
        }

        if (!reward.getRewardName().equals("")) {
            editTextRewardName.setText(reward.getRewardName());
            editTextRewardPrice.setText(String.valueOf(reward.getRewardPoint()));
            buttonSaveReward.setText("Update Reward");
        }

        buttonSaveReward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveReward(v, reward);
            }
        });

        buttonRewardList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                fragmentManager.popBackStackImmediate(); // Remove this fragment from the back stack

                RewardsListFragment rewardsListFragment = new RewardsListFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, rewardsListFragment)
                        .commit();
            }
        });

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rewardViewModel = new ViewModelProvider(this).get(RewardViewModel.class);
    }

    private void saveReward(View v, RewardModel reward) {
        String rewardName = editTextRewardName.getText().toString().trim();
        String rewardPriceString = editTextRewardPrice.getText().toString().trim();

        if (!rewardName.isEmpty() && !rewardPriceString.isEmpty()) {
            double rewardPrice = Double.parseDouble(rewardPriceString);
            if (rewardPrice > 0) {
                if(update) {
                    rewardViewModel.update(reward);
                }
                else {
                    rewardViewModel.insert(reward);
                }

                Navigation.findNavController(v).navigate(R.id.nav_rewards);
            } else {
                // Handle negative or zero reward price
                editTextRewardPrice.setError("Enter a valid price");
            }
        } else {
            // Handle empty reward name or price
            if (rewardName.isEmpty()) {
                editTextRewardName.setError("Reward name is required");
            }
            if (rewardPriceString.isEmpty()) {
                editTextRewardPrice.setError("Reward price is required");
            }
        }
    }
    private void updateReward(RewardModel reward) {
        String rewardName = editTextRewardName.getText().toString().trim();
        String rewardPointString = editTextRewardPrice.getText().toString().trim();

        if (!rewardName.isEmpty() && !rewardPointString.isEmpty()) {
            double rewardPoint = Double.parseDouble(rewardPointString);
            if (rewardPoint > 0) {
                reward.setRewardName(rewardName);
                reward.setRewardPoint(rewardPoint);
                rewardViewModel.update(reward);

                // Navigate back to RewardsListFragment after saving
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                fragmentManager.popBackStackImmediate(); // Remove this fragment from the back stack

                RewardsListFragment rewardsListFragment = new RewardsListFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, rewardsListFragment)
                        .commit();
            } else {
                // Handle negative or zero reward price
                editTextRewardPrice.setError("Enter a valid point");
            }
        } else {
            // Handle empty reward name or price
            if (rewardName.isEmpty()) {
                editTextRewardName.setError("Reward name is required");
            }
            if (rewardPointString.isEmpty()) {
                editTextRewardPrice.setError("Reward point is required");
            }
        }
    }

}
