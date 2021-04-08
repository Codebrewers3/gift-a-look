package com.example.giftalook.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.giftalook.Model.OutfitBoardProduct;
import com.example.giftalook.Model.Product;
import com.example.giftalook.Model.User;
import com.example.giftalook.R;
import com.example.giftalook.databinding.FragmentCurrentGroupBinding;
import com.example.giftalook.databinding.FragmentDashboardBinding;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CurrentGroupFragment extends Fragment {

    private FragmentCurrentGroupBinding currentGroupBinding;
    private DatabaseReference mDataRef;
    private RecyclerView mRecyclerView;
    private FirebaseRecyclerOptions<User> options;
    private FirebaseRecyclerAdapter firebaseRecyclerAdapter;
    private NavController navController;

    public CurrentGroupFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment, using ViewBinding
        currentGroupBinding = FragmentCurrentGroupBinding.inflate(inflater, container, false);
        return currentGroupBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("My Group");
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        mDataRef = FirebaseDatabase.getInstance().getReference().child("Users");
        mRecyclerView = currentGroupBinding.recyclerView;
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        currentGroupBinding.coordinatorLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_currentGroupFragment_to_outfitBoardFragment);
            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();

        options =
                new FirebaseRecyclerOptions.Builder<User>()
                        .setQuery(mDataRef, User.class)
                        .build();


        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<User, UsersViewHolder>(options) {

            @Override
            public UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.group_member_item, parent, false);
                return new UsersViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(UsersViewHolder usersViewHolder, int i, User users) {
                usersViewHolder.setName(users.getUsername());
            }
        };
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);

        firebaseRecyclerAdapter.startListening();

        ItemTouchHelper.Callback itemTouchCallback = new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                navController.navigate(R.id.action_currentGroupFragment_to_outfitBoardFragment);
                Log.v("In CurrentGroupFragment", "Viewing someone's outfit board at current position");
                return 0;
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                navController.navigate(R.id.action_currentGroupFragment_to_outfitBoardFragment);
                Log.v("In CurrentGroupFragment", "Viewing someone's outfit board at position " + position);
            }
        };
        ItemTouchHelper touchHelper = new ItemTouchHelper(itemTouchCallback);
        touchHelper.attachToRecyclerView(mRecyclerView);
    }


    public static class UsersViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public UsersViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setName (String name){
            TextView userNameView = (TextView) mView.findViewById(R.id.member_username);
            userNameView.setText(name);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        firebaseRecyclerAdapter.stopListening();
    }
}
