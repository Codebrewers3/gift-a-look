package com.example.giftalook.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.giftalook.Adapters.OutfitBoardAdapter;
import com.example.giftalook.Adapters.ProductAdapter;
import com.example.giftalook.Model.OutfitBoardProduct;
import com.example.giftalook.Model.Product;
import com.example.giftalook.R;
import com.example.giftalook.ViewModel.PersonalOutfitBoardViewModel;
import com.example.giftalook.ViewModel.ProductViewModel;
import com.example.giftalook.databinding.FragmentBrowseBinding;
import com.example.giftalook.databinding.FragmentOutfitBoardBinding;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class OutfitBoardFragment extends Fragment {


    private FragmentOutfitBoardBinding outfitBoardBinding;
    private RecyclerView mRecyclerView;
    private ArrayList<OutfitBoardProduct> mProducts = new ArrayList<>();
    private PersonalOutfitBoardViewModel productViewModel;
    private OutfitBoardAdapter outfitBoardAdapter;
    private ProductViewModel browseProductViewModel;
    private DatabaseReference mRootRef;
    private NavController navController;
    private boolean swipeStatus = false;

    public OutfitBoardFragment() {
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
        outfitBoardBinding = FragmentOutfitBoardBinding.inflate(inflater, container, false);
        mRecyclerView = outfitBoardBinding.recyclerView;
        return outfitBoardBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Outfit Board");

        browseProductViewModel = new ViewModelProvider(getActivity()).get(ProductViewModel.class);
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        mRootRef = FirebaseDatabase.getInstance().getReference();
        productViewModel = new ViewModelProvider(getActivity()).get(PersonalOutfitBoardViewModel.class);
        productViewModel.getAllProducts().observe(getViewLifecycleOwner(), new Observer<List<OutfitBoardProduct>>() {
            @Override
            public void onChanged(List<OutfitBoardProduct> products) {
                mProducts = (ArrayList<OutfitBoardProduct>) products;
                mRootRef.child("Users").child(FirebaseAuth.getInstance().getUid()).child("myProducts").setValue(mProducts);
                outfitBoardAdapter = new OutfitBoardAdapter(mProducts);
                mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                mRecyclerView.setAdapter(outfitBoardAdapter);
            }
        });

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                swipeStatus = true;
                int position = viewHolder.getAdapterPosition();
                OutfitBoardProduct product = outfitBoardAdapter.getProductAt(position);

                /** Adding swiped product to personal outfit board of individual */
                Product product1 = new Product();
                product1.setProductName(product.getProductName());
                product1.setProductBrand(product.getProductBrand());
                product1.setProductCost(product.getProductCost());
                product1.setProductDescription(product.getProductDescription());
                product1.setProductImageUri(product.getProductImageUri());
                browseProductViewModel.addProduct(product1);

                productViewModel.deleteProduct(product);
                outfitBoardAdapter.notifyItemRemoved(position);

                Snackbar.make(mRecyclerView, "Deleted from your Outfit Board", Snackbar.LENGTH_SHORT)
                        .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE)
                        .setTextColor(getResources().getColor(R.color.bottom_nav_color))
                        .show();
            }
        };
        ItemTouchHelper touchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        touchHelper.attachToRecyclerView(mRecyclerView);

        ItemTouchHelper.Callback callback = new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                if(!swipeStatus) {
                    int position = viewHolder.getAdapterPosition();
                    OutfitBoardProduct product = outfitBoardAdapter.getProductAt(position);
                    GiftOutfitFragment giftOutfitFragment = new GiftOutfitFragment();
                    Bundle outfitInfo = new Bundle();
                    outfitInfo.putString("Title", product.getProductName());
                    outfitInfo.putString("Brand", product.getProductBrand());
                    outfitInfo.putString("Image", product.getProductImageUri());
                    outfitInfo.putString("Cost", product.getProductCost() + "");
                    outfitInfo.putString("Description", product.getProductDescription());
                    giftOutfitFragment.setArguments(outfitInfo);

                    getFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new OutfitBoardFragment(), "backStack");
                    mRecyclerView.setVisibility(View.INVISIBLE);
                    getFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, giftOutfitFragment, null).addToBackStack("backStack").commit();
                }
                swipeStatus = false; 
                return 0;
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            }
        };
        ItemTouchHelper callbackTouchHelper = new ItemTouchHelper(callback);
        callbackTouchHelper.attachToRecyclerView(mRecyclerView);

    }
}