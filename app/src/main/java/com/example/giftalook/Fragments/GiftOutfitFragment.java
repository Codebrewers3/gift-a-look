package com.example.giftalook.Fragments;

import android.content.Intent;
import android.media.Session2Command;
import android.os.Bundle;

import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.speech.RecognizerIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.giftalook.MainActivity;
import com.example.giftalook.R;
import com.example.giftalook.databinding.FragmentGiftOutfitBinding;
import com.example.giftalook.databinding.FragmentGiftingOutfitBoardBinding;
import com.example.giftalook.databinding.FragmentOutfitBoardBinding;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class GiftOutfitFragment extends Fragment {

    private FragmentGiftOutfitBinding giftOutfitBinding;
    private String title;
    private String brandName;
    private String imageUri;
    private String cost;
    private String description;
    private static final int REQUEST_CODE_SPEECH_INPUT = 1;

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
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Gift a Look");
        giftOutfitBinding.introTextView.setText("Make someone's day, gift this look today!");
        giftOutfitBinding.productTitleTextview.setText(title);
        giftOutfitBinding.productBrandTextview.setText(brandName);
        giftOutfitBinding.productImageView.setImageResource(Integer.parseInt(imageUri));
        giftOutfitBinding.productCostTextview.setText("Rs. " + cost);
        giftOutfitBinding.productDescriptionTextview.setText(description);

        giftOutfitBinding.recordANote.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent
                        = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                        Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speech to Text");

                try {
                    startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);
                } catch (Exception e) {
                    Toast.makeText(getContext(), " " + e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SPEECH_INPUT) {
            if (data != null) {
                ArrayList<String> result = data.getStringArrayListExtra(
                        RecognizerIntent.EXTRA_RESULTS);
                giftOutfitBinding.noteEdittext.setText(Objects.requireNonNull(result).get(0));
            }
        }
    }
}