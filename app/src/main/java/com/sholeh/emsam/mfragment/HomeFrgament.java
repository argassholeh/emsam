/*
 * Copyright (c) Muhammad Solehudin
 */

package com.sholeh.emsam.mfragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sholeh.emsam.R;


public class HomeFrgament extends Fragment {

    TextView tvx_title;
    ImageView imgtoolbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home,container,false);

        return rootView;
    }
}

