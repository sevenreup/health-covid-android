package com.skybox.seven.covid.ui.bottomsheets;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.skybox.seven.covid.GlideApp;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.model.Advice;
import com.skybox.seven.covid.viewmodels.AdviceViewModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AdviceBottomSheetFragment extends BottomSheetDialogFragment {
    Bitmap currentImage;
    public AdviceBottomSheetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_advice_bottom_sheet, container, false);
        AdviceViewModel viewModel = new ViewModelProvider(getParentFragment().getViewModelStore(), new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(AdviceViewModel.class);

        viewModel.activeAdvice.observe(getViewLifecycleOwner(), advice -> {
            (v.findViewById(R.id.advice_container)).setVisibility(View.VISIBLE);
            (v.findViewById(R.id.info_graphic)).setVisibility(View.GONE);
            ((TextView) v.findViewById(R.id.bt_title)).setText(advice.getTitle());
            ((TextView) v.findViewById(R.id.bt_answer)).setText(advice.getAdvice());
        });

        viewModel.activeInfoGraphic.observe(getViewLifecycleOwner(), s -> {
            (v.findViewById(R.id.advice_container)).setVisibility(View.GONE);
            (v.findViewById(R.id.info_graphic)).setVisibility(View.VISIBLE);
            GlideApp.with(getContext())
                    .asBitmap()
                    .load(s)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .error(R.drawable.ic_error)
                    .into(new BitmapImageViewTarget( (ImageView) v.findViewById(R.id.info_image)) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            ((ImageView) v.findViewById(R.id.info_image)).setImageBitmap(resource);
                            currentImage = resource;
                        }
                    });
        });

        v.findViewById(R.id.share_tip).setOnClickListener(v12 -> {
           Advice.CurrentChip chip = viewModel.activeChip.getValue();
           if (chip == Advice.CurrentChip.advice) {
               Intent sendIntent = new Intent();
               sendIntent.setAction(Intent.ACTION_SEND);
               sendIntent.putExtra(Intent.EXTRA_TEXT, buildSharableString(viewModel.activeAdvice.getValue()));
               sendIntent.setType("text/plain");

               Intent shareIntent = Intent.createChooser(sendIntent, null);
               startActivity(shareIntent);
           } else {
               Uri uri = getLocalBitmapUri(currentImage);
               Log.e("TAG", "onCreateView: " + uri );
               Intent sendIntent = new Intent();
               sendIntent.setAction(Intent.ACTION_SEND);
               sendIntent.putExtra(Intent.EXTRA_STREAM, uri);
               sendIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
               sendIntent.setType("image/jpeg");

               startActivity(Intent.createChooser(sendIntent, "Health tip Image"));
           }

        });
        v.findViewById(R.id.close_tip).setOnClickListener(v1 -> dismiss());
        return v;
    }

    private String buildSharableString(Advice advice) {
        StringBuilder builder = new StringBuilder();
        builder.append("Health Tip: \n\n");
        builder.append(advice.getTitle());
        builder.append("\n\nWhy: \n\n");
        builder.append(advice.getAdvice());
        return builder.toString();
    }

    private Uri getLocalBitmapUri(Bitmap bmp) {
        Uri bmpUri = null;
        File file = new File(getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES), "share_image_" + System.currentTimeMillis() + ".png");
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            bmpUri = Uri.fromFile(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }
}
