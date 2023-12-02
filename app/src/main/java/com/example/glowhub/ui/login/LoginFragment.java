package com.example.glowhub.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.navigation.Navigation;

import com.example.glowhub.R;

public class LoginFragment extends Fragment {

    private EditText emailTextView, passwordTextView;
    private Button loginButton;
    private TextView signupTextView, forgotPasswordTextView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        emailTextView = view.findViewById(R.id.loginEmailTextView);
        passwordTextView = view.findViewById(R.id.loginPasswordTextView);
        loginButton = view.findViewById(R.id.loginBtn);
        signupTextView = view.findViewById(R.id.signUpTextView);
        forgotPasswordTextView = view.findViewById(R.id.forgotPasswordTextView);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle user login
                loginUser();
            }
        });

        signupTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to SignUpFragment
                Navigation.findNavController(requireView()).navigate(R.id.nav_sign_up);
            }
        });

        forgotPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to the ForgetPasswordFragment when the button is clicked
                Navigation.findNavController(view).navigate(R.id.nav_forget_password);
            }
        });

        return view;
    }

    private void loginUser() {
        // Retrieve user input
        String email = emailTextView.getText().toString().trim();
        String password = passwordTextView.getText().toString().trim();

        // For demonstration, just show a toast with entered details
        String message = "Email: " + email + "\nPassword: " + password;
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();

        Navigation.findNavController(requireView()).navigate(R.id.nav_home);
    }
}

