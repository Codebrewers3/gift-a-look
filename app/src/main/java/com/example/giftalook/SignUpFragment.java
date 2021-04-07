package com.example.giftalook;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.giftalook.Model.User;
import com.example.giftalook.databinding.FragmentBrowseBinding;
import com.example.giftalook.databinding.FragmentLoginBinding;
import com.example.giftalook.databinding.FragmentSignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignUpFragment extends Fragment {

    private FragmentSignUpBinding signUpBinding;
    private EditText mUsername;
    private EditText mEmailId;
    private EditText mPassword;
    private DatabaseReference mRootRef;
    private FirebaseAuth mAuth;
    private NavController navController;

    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRootRef = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
    }

    private void registerUser(String txtUsername, String txtEmail, String txtPassword) {

        mAuth.createUserWithEmailAndPassword(txtEmail, txtPassword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                User newUser = new User(txtUsername, txtEmail, mAuth.getCurrentUser().getUid(), "", "default");

                mRootRef.child("Users").child(mAuth.getCurrentUser().getUid()).setValue(newUser).addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("SignUpFragment.java", "Sign-up is successful");
                            Snackbar.make(signUpBinding.signupLinearLayout, "Sign-up successful!", Snackbar.LENGTH_SHORT)
                                    .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE)
                                    .setTextColor(getResources().getColor(R.color.bottom_nav_color))
                                    .show();
                            navController.navigate(R.id.action_signUpFragment_to_dashboardFragment);
                        }
                    }
                });

            }
        }).addOnFailureListener(e -> Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment, using ViewBinding
        signUpBinding = FragmentSignUpBinding.inflate(inflater, container, false);
        return signUpBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        signUpBinding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean flag = true;
                try {
                    mUsername = signUpBinding.signupUsernameEdittext;
                    mEmailId = signUpBinding.signupEmailidEdittext;
                    mPassword = signUpBinding.signupPasswordEdittext;
                } catch (Exception e) {
                    flag = false;
                    Toast.makeText(getContext(), "Invalid credentials entered", Toast.LENGTH_SHORT).show();
                }
                if (flag) {
                    String txtUsername = mUsername.getText().toString();
                    String txtEmail = mEmailId.getText().toString();
                    String txtPassword = mPassword.getText().toString();

                    if (TextUtils.isEmpty(txtUsername) || TextUtils.isEmpty(txtEmail) ||
                            TextUtils.isEmpty(txtPassword)) {
                        Toast.makeText(getContext(), "Empty field(s)", Toast.LENGTH_SHORT).show();
                    } else if (txtPassword.length() < 6) {
                        Toast.makeText(getContext(), "Password should be 8 characters or longer", Toast.LENGTH_SHORT).show();
                    } else {
                        registerUser(txtUsername, txtEmail, txtPassword);
                    }
                }
            }
        });
    }
}