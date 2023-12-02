package com.example.glowhub.ui.user_management;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.glowhub.R;
import com.example.glowhub.ui.sign_up.User;
import com.example.glowhub.ui.sign_up.UserDatabaseHelper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserManagementFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserManagementFragment extends Fragment {

    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private UserDatabaseHelper dbHelper;

    public UserManagementFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_management, container, false);

        dbHelper = new UserDatabaseHelper(requireContext());

        recyclerView = view.findViewById(R.id.userRecyclerView);
        userAdapter = new UserAdapter(dbHelper.getAllUsers());
        recyclerView.setAdapter(userAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        userAdapter.setOnItemClickListener(new UserAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(User user) {
                // Handle click on user item
                // You might navigate to user details fragment/activity
                Toast.makeText(requireContext(), "Clicked: " + user.getUsername(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDeleteClick(User user) {
                // Handle click on delete icon
                dbHelper.deleteUser(user.getEmail());
                userAdapter.setUserList(dbHelper.getAllUsers());
                userAdapter.notifyDataSetChanged();
                Toast.makeText(requireContext(), "Deleted: " + user.getUsername(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    // Methods like showUserDetails() and deleteUser() can be implemented here
    // based on your specific requirements.
}
