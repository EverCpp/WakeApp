package com.enofir.wakeapp.dialogs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.enofir.wakeapp.R;
import com.enofir.wakeapp.objects.GemaTime;
import com.enofir.wakeapp.objects.TimeEvent;

public class DialogAddAlarm extends AppCompatActivity
{
    Button butAdd;
    EditText editHour, editMin;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_alarm);

        // Widgets
        butAdd = findViewById(R.id.buttonAddAlarmOk);
        editHour = findViewById(R.id.editAlarmHour);
        editMin = findViewById(R.id.editAlarmMin);
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        butAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int hour = Integer.parseInt(editHour.getText().toString());
                int min = Integer.parseInt(editMin.getText().toString());
                TimeEvent event = new TimeEvent("Despertador", new GemaTime(hour, min), TimeEvent.TimeEventFrequency.DIARY);

                Intent intent = new Intent();
                intent.putExtra("event", event);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}