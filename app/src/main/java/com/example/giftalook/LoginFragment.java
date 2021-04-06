package com.example.giftalook;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.giftalook.databinding.FragmentDashboardBinding;
import com.example.giftalook.databinding.FragmentLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding loginBinding;
    private FirebaseAuth mAuth;
    private NavController navController;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment, using ViewBinding
        loginBinding = FragmentLoginBinding.inflate(inflater, container, false);
        return loginBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loginBinding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = "";
                String password = "";
                try {
                    email = loginBinding.loginUsernameEdittext.getText().toString();
                    password = loginBinding.loginPasswordEdittext.getText().toString();
                } catch(Exception e) {
                    Toast.makeText(getContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
                if(email.equals("")) {
                    Toast.makeText(getContext(), "Enter valid username", Toast.LENGTH_SHORT).show();
                } else if(password.equals("")) {
                    Toast.makeText(getContext(), "Enter valid password", Toast.LENGTH_SHORT).show();
                } else {
                    email = loginBinding.loginUsernameEdittext.getText().toString();
                    password = loginBinding.loginPasswordEdittext.getText().toString();

                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Toast.makeText(getContext(), "Log-in Successful", Toast.LENGTH_SHORT).show();
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                                        navController.navigate(R.id.action_loginFragment_to_dashboardFragment);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(getContext(), "Authentication failed", Toast.LENGTH_SHORT).show();
                                        loginBinding.loginUsernameEdittext.setText("");
                                        loginBinding.loginPasswordEdittext.setText("");
                                    }
                                }
                            });
                }
            }
        });

        loginBinding.loginToCreateAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_loginFragment_to_signUpFragment);
            }
        });
    }
}