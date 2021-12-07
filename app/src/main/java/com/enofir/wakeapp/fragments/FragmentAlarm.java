package com.enofir.wakeapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.enofir.wakeapp.R;
import com.enofir.wakeapp.interfaces.ItemClickListener;
import com.enofir.wakeapp.objects.TimeEvent;
import com.enofir.wakeapp.recyclers.AlarmItemAdapter;

public class FragmentAlarm extends Fragment
{
    private RecyclerView recyclerView;
    private AlarmItemAdapter alarmItemAdapter;
    private RecyclerView.LayoutManager manager;
    private ItemClickListener itemClickListener;

    public FragmentAlarm()
    {
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        itemClickListener = new ItemClickListener()
        {
            @Override
            public void onClick(int position)
            {
            }
        };
        alarmItemAdapter = new AlarmItemAdapter(itemClickListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view =  inflater.inflate(R.layout.fragment_alarm, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewAlarm);

        manager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(alarmItemAdapter);

        return view;
    }

    public void addItem(TimeEvent timeEvent)
    {
        alarmItemAdapter.addItem(timeEvent);
        recyclerView.setAdapter(alarmItemAdapter);
    }
}