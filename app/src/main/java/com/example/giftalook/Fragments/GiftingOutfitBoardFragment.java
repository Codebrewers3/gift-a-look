package com.example.giftalook.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.giftalook.R;
import com.example.giftalook.databinding.FragmentBrowseBinding;
import com.example.giftalook.databinding.FragmentGiftingOutfitBoardBinding;

public class GiftingOutfitBoardFragment extends Fragment {

    private FragmentGiftingOutfitBoardBinding outfitBoardBinding;

    public GiftingOutfitBoardFragment() {
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
        outfitBoardBinding = FragmentGiftingOutfitBoardBinding.inflate(inflater, container, false);
        return outfitBoardBinding.getRoot();
    }
}