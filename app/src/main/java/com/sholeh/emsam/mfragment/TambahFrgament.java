/*
 * Copyright (c) Muhammad Solehudin
 */

package com.sholeh.emsam.mfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sholeh.emsam.R;


public class TambahFrgament extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_update_karyawan,container,false);

        return rootView;
    }
}

