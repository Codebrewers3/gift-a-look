package com.example.giftalook;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.giftalook.Fragments.BrowseFragment;
import com.example.giftalook.Fragments.DashboardFragment;
import com.example.giftalook.Fragments.HistoryFragment;
import com.example.giftalook.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    private int destinationId;
    private FirebaseAuth mAuth;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance(); // Initialize Firebase Auth
        //Setting the home screen (MainActivity), using ViewBinding
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View homeView = activityMainBinding.getRoot();
        setContentView(homeView);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(activityMainBinding.bottomNav, navController);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller,
                                             @NonNull NavDestination destination, @Nullable Bundle arguments) {
                destinationId = destination.getId();
                if (destination.getId() == R.id.dashboardFragment || destination.getId() == R.id.browseFragment
                        || destination.getId() == R.id.historyFragment) {
                    activityMainBinding.bottomNav.setVisibility(View.VISIBLE);
                } else {
                    activityMainBinding.bottomNav.setVisibility(View.GONE);
                }

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){

        } else {
            navController.navigate(R.id.action_dashboardFragment_to_loginFragment);
        }
    }
}