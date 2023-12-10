package com.example.glowhub.ui.rewards;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.glowhub.R;

import java.util.ArrayList;
import java.util.List;

public class RewardsAdapter extends RecyclerView.Adapter<RewardsAdapter.RewardViewHolder> {

    private List<RewardModel> rewardList = new ArrayList<>();
    private OnItemClickListener itemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View v, RewardModel reward);
        void onEditClick(View v, RewardModel reward);
        void onDeleteClick(RewardModel reward);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }

    public RewardsAdapter(List<RewardModel> rewardList) {
        this.rewardList = rewardList;
    }


    @NonNull
    @Override
    public RewardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_reward, parent, false);
        return new RewardViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull RewardViewHolder holder, int position) {
        RewardModel currentReward = rewardList.get(position);
        holder.textViewRewardName.setText(currentReward.getRewardName());
        holder.textViewRewardPrice.setText(String.valueOf(currentReward.getRewardPoint()));
        holder.itemView.setOnClickListener(view -> {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(view, currentReward);
            }
        });
        // Handle edit button click
        holder.buttonEditReward.setOnClickListener(v -> {
            if (itemClickListener != null) {
                itemClickListener.onEditClick(v, currentReward);
            }
        });

        // Handle delete button click
        holder.buttonDeleteReward.setOnClickListener(v -> {
            if (itemClickListener != null) {
                itemClickListener.onDeleteClick(currentReward);
            }
        });
    }

    @Override
    public int getItemCount() {
        return rewardList.size();
    }

    public void setRewards(List<RewardModel> rewards) {
        this.rewardList = rewards;
        notifyDataSetChanged();
    }

    static class RewardViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewRewardName;
        private TextView textViewRewardPrice;
        ImageButton buttonEditReward;
        ImageButton buttonDeleteReward;

        public RewardViewHolder(View itemView) {
            super(itemView);
            textViewRewardName = itemView.findViewById(R.id.text_view_reward_name);
            textViewRewardPrice = itemView.findViewById(R.id.text_view_reward_price);
            buttonEditReward = itemView.findViewById(R.id.button_edit_reward);
            buttonDeleteReward = itemView.findViewById(R.id.button_delete_reward);
        }
        public interface OnItemClickListener {
            void onEditClick(RewardModel reward);

            void onDeleteClick(RewardModel reward);
        }
    }
}
