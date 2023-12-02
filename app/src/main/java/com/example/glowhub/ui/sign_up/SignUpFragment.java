package com.example.glowhub.ui.sign_up;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.glowhub.R;

public class SignUpFragment extends Fragment {

    private EditText fullNameView, emailView, passwordView;
    private Button registerButtonView;
    private UserDatabaseHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        fullNameView = view.findViewById(R.id.fullNameEditText);
        emailView = view.findViewById(R.id.emailEditText);
        passwordView = view.findViewById(R.id.passwordEditText);
        registerButtonView = view.findViewById(R.id.registerButton);

        dbHelper = new UserDatabaseHelper(requireContext());

        registerButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle user registration
                registerUser();
            }
        });

        return view;
    }

    private void registerUser() {
        // Retrieve user input
        String fullName = fullNameView.getText().toString().trim();
        String email = emailView.getText().toString().trim();
        String password = passwordView.getText().toString().trim();

        // Validate input (add your validation logic here)
        Log.d("SignUpFragment", "registerUser: Registration button clicked");

        // Rest of your registration logic...

        Log.d("SignUpFragment", "registerUser: Registration process completed");

        // Create a new user object
        User newUser = new User();
        newUser.setFullName(fullName);
        newUser.setEmail(email);
        newUser.setPassword(password);

        // Add the user to the database
        long userId = dbHelper.addUser(newUser);

        if (userId != -1) {
            // Registration successful
            Toast.makeText(requireContext(), "Registration successful", Toast.LENGTH_SHORT).show();

            // You can navigate to the login screen or any other screen
            Navigation.findNavController(requireView()).navigate(R.id.nav_login);
        } else {
            // Registration failed
            Toast.makeText(requireContext(), "User has already registered. Please login.", Toast.LENGTH_SHORT).show();
        }
    }
}
