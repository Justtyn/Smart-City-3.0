package com.example.smartcity30.fragment.News.viewPagerFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartcity30.R;

public class TechnologicalInnovationFragment extends Fragment {

    public View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_technological_innovation, container, false);
        return view;
    }
}