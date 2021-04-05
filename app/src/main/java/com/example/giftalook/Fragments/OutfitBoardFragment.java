package com.example.giftalook.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
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

import java.util.ArrayList;
import java.util.List;

public class OutfitBoardFragment extends Fragment {


    private FragmentOutfitBoardBinding outfitBoardBinding;
    private RecyclerView mRecyclerView;
    private ArrayList<OutfitBoardProduct> mProducts = new ArrayList<>();
    private PersonalOutfitBoardViewModel productViewModel;
    private OutfitBoardAdapter outfitBoardAdapter;
    private ProductViewModel browseProductViewModel;

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
        browseProductViewModel = new ViewModelProvider(getActivity()).get(ProductViewModel.class);
        productViewModel = new ViewModelProvider(getActivity()).get(PersonalOutfitBoardViewModel.class);
        productViewModel.getAllProducts().observe(getViewLifecycleOwner(), new Observer<List<OutfitBoardProduct>>() {
            @Override
            public void onChanged(List<OutfitBoardProduct> products) {
                mProducts = (ArrayList<OutfitBoardProduct>) products;
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

    }
}