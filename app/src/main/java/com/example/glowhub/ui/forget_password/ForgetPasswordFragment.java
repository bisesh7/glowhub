package com.example.glowhub.ui.forget_password;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.glowhub.R;

public class ForgetPasswordFragment extends Fragment {

    private EditText emailTextView;
    private Button resetPasswordBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forget_password, container, false);

        emailTextView = view.findViewById(R.id.forgetEmailTextView);
        resetPasswordBtn = view.findViewById(R.id.resetPasswordBtn);

        resetPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Password reset email sent.", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
