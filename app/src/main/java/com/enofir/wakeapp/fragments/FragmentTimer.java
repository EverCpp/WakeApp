package com.enofir.wakeapp.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.enofir.wakeapp.R;

public class FragmentTimer extends Fragment
{
    public FragmentTimer()
    {
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view =  inflater.inflate(R.layout.fragment_alarm, container, false);
        Log.e("LogApp", "FragmentAlarm - onCreateView");
        return view;
    }
}