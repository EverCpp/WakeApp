package com.enofir.wakeapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.enofir.wakeapp.dialogs.DialogAddAlarm;
import com.enofir.wakeapp.fragments.FragmentAlarm;
import com.enofir.wakeapp.fragments.FragmentTimer;
import com.enofir.wakeapp.objects.TimeEvent;

@SuppressWarnings({"UnusedReturnValue", "unused"})
public class MainActivity extends AppCompatActivity
{
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private FragmentAlarm fragmentAlarm;
    private FragmentTimer fragmentTimer;

    private FrameLayout frameAlarm;

    private Button buttonAdd;

    private ActivityResultLauncher<Intent> startInputTest;          // Launcher de input test de número

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Frames
        frameAlarm = findViewById(R.id.frameLayoutMain);

        fragmentAlarm = new FragmentAlarm();
        fragmentTimer = new FragmentTimer();

        // Create new fragment and transaction
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        // Replace whatever is in the fragment_container view with this fragment, and add the transaction to the back stack
        transaction.replace(R.id.frameLayoutMain, fragmentAlarm);
        //transaction.replace(R.id.frameProducts, fragmentTimer);
        //transaction.addToBackStack(null);
        // Commit the transaction
        transaction.commit();

        // Diálogos
        dialogsInit();
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        buttonAdd = frameAlarm.findViewById(R.id.buttonAddAlarm);
        buttonAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                launchTest();
            }
        });
    }

    private void dialogsInit()
    {
        startInputTest = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>()
        {
            @Override
            public void onActivityResult(ActivityResult result)
            {
                if(result.getResultCode() == AppCompatActivity.RESULT_OK)
                {
                    Intent intent = result.getData();
                    if(intent != null)
                    {
                        TimeEvent event = intent.getParcelableExtra("event");
                        Log.e("LogApp", "event: " + event);
                        fragmentAlarm.addItem(event);
                    }
                }
            }
        });
    }

    private void launchTest()
    {
        Intent intent = new Intent(this, DialogAddAlarm.class);
        startInputTest.launch(intent);
    }
}