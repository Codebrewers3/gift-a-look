package com.example.giftalook.Fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.giftalook.R;
import com.example.giftalook.databinding.FragmentGiftOutfitBinding;
import com.example.giftalook.databinding.FragmentGiftingOutfitBoardBinding;
import com.example.giftalook.databinding.FragmentOutfitBoardBinding;

public class GiftOutfitFragment extends Fragment {

    private FragmentGiftOutfitBinding giftOutfitBinding;
    private String title;
    private String brandName;
    private String imageUri;
    private String cost;
    private String description;

    public GiftOutfitFragment() {
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
        giftOutfitBinding = FragmentGiftOutfitBinding.inflate(inflater, container, false);
        title = getArguments().getString("Title");
        brandName = getArguments().getString("Brand");
        imageUri = getArguments().getString("Image");
        cost = getArguments().getString("Cost");
        description = getArguments().getString("Description");
        return giftOutfitBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Gift a Look");
        giftOutfitBinding.introTextView.setText("Make someone's day, gift this look today!");
        giftOutfitBinding.productTitleTextview.setText(title);
        giftOutfitBinding.productBrandTextview.setText(brandName);
        giftOutfitBinding.productImageView.setImageResource(Integer.parseInt(imageUri));
        giftOutfitBinding.productCostTextview.setText("Rs. " + cost);
        giftOutfitBinding.productDescriptionTextview.setText(description);
    }

}