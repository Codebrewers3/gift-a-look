package com.example.giftalook.Fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.example.giftalook.Adapters.ProductAdapter;
import com.example.giftalook.MainActivity;
import com.example.giftalook.Model.Product;
import com.example.giftalook.R;
import com.example.giftalook.ViewModel.ProductViewModel;
import com.example.giftalook.databinding.FragmentBrowseBinding;
import com.example.giftalook.databinding.FragmentDashboardBinding;

import java.util.ArrayList;
import java.util.List;

public class BrowseFragment extends Fragment {

    private ArrayList<Product> mProducts = new ArrayList<>();
    private FragmentBrowseBinding browseBinding;
    private ProductViewModel productViewModel;
    private RecyclerView mRecyclerView;
    private ProductAdapter productAdapter;

    public BrowseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment, using ViewBinding
        browseBinding = FragmentBrowseBinding.inflate(inflater, container, false);
        mRecyclerView = browseBinding.recyclerView;
        return browseBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        productViewModel = new ViewModelProvider(getActivity()).get(ProductViewModel.class);
        productViewModel.getAllProducts().observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                mProducts = (ArrayList<Product>) products;
                productAdapter = new ProductAdapter(mProducts);
                mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                mRecyclerView.setAdapter(productAdapter);
            }
        });

        if(mProducts.isEmpty()) {
            for(int i=0; i<20; i++) {
                Product product = new Product();

                int remainder = i%6;
                String title = "";
                String brand = "";
                float cost = 0f;
                String description = "";
                String currentImageURI = "";

                switch(remainder) {
                    case 5:
                        title = "Yellow Skater Dress";
                        brand = "Sunshine Designs";
                        cost = 800.50f;
                        description = "A simple cotton dress to look stylish while you beat the heat!";
                        currentImageURI = R.drawable.ic_dress + "";
                        break;
                    case 4:
                        title = "Slim-fit Blue Jeans";
                        brand = "The Jeans Company";
                        cost = 2000f;
                        description = "Comfortable, stylish, and oh-so-smart, let your jeans do the talking...";
                        currentImageURI = R.drawable.ic_jeans + "";
                        break;
                    case 3:
                        title = "Red Sports Jacket";
                        brand = "All Sports";
                        cost = 1400f;
                        description = "With special moisture-wicking technology, stay cool while you " +
                                "push yourself to new heights";
                        currentImageURI = R.drawable.ic_red_jacket + "";
                        break;
                    case 2:
                        title = "Pearl Jewellery Set";
                        brand = "Indian Pearl Company";
                        cost = 6999f;
                        description = "Be the light of every party with this necklace and earrings set";
                        currentImageURI = R.drawable.ic_jewellery_set + "";
                        break;
                    case 1:
                        title = "Sky Blue Trench Cost";
                        brand = "Sunshine Designs";
                        cost = 900.50f;
                        description = "Keep warm in this non-bulky trench coat made with the world's " +
                                "best Merino wool.";
                        currentImageURI = R.drawable.ic_trench_coat + "";
                        break;
                    case 0:
                        title = "Striped T-shirt Dress";
                        brand = "Sunshine Designs";
                        cost = 600f;
                        description = "This trendy dress is bound to define summer fashion for 2021!";
                        currentImageURI = R.drawable.ic_tshirt_dress + "";
                        break;

                }

                product.setProductName(title);
                product.setProductBrand(brand);
                product.setProductCost(cost);
                product.setProductDescription(description);
                product.setProductImageUri(currentImageURI);
                productViewModel.addProduct(product);
            }
        }
    }
}