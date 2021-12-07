package com.enofir.wakeapp.recyclers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.enofir.wakeapp.R;
import com.enofir.wakeapp.interfaces.ItemClickListener;
import com.enofir.wakeapp.objects.TimeEvent;
import java.util.ArrayList;

@SuppressWarnings({"UnusedReturnValue", "unused"})
public class AlarmItemAdapter extends RecyclerView.Adapter<AlarmItemAdapter.AlarmItemViewHolder>
{
    private static final int SHOWED_ITEMS = 20;

    private final ArrayList<TimeEvent> items;
    private final ItemClickListener itemClickListener;

    public AlarmItemAdapter(ItemClickListener itemClickListener)
    {
        this.itemClickListener = itemClickListener;
        this.items = new ArrayList<>();
    }

    @Override
    public int getItemCount()
    {
        return items.size();
    }

    public AlarmItemAdapter setFilter(String filter)
    {
        return this;
    }

    public ArrayList<TimeEvent> getItems()
    {
        return items;
    }

    public TimeEvent getItemAt(int position)
    {
        if((position < 0) || (position >= items.size()))
        {
            return null;
        }
        return items.get(position);
    }

    public void addItem(TimeEvent timeEvent)
    {
        items.add(timeEvent);
    }

    /**
     * Provide a reference to the type of views that you are using (custom GridItemViewHolder).
     */
    public static class AlarmItemViewHolder extends RecyclerView.ViewHolder
    {
        public TextView textTime;
        public TextView textName;
        public TextView textRepeat;
        public SwitchCompat switchAlarm;
        public ConstraintLayout layout;
        public AlarmItemViewHolder(View viewItem)
        {
            super(viewItem);
            textTime = viewItem.findViewById(R.id.textAlarmTime);
            textName = viewItem.findViewById(R.id.textAlarmName);
            textRepeat = viewItem.findViewById(R.id.textAlarmRepeat);
            switchAlarm = viewItem.findViewById(R.id.switchAlarm);
            layout = viewItem.findViewById(R.id.layoutItemAlarm);
        }
    }

    @NonNull
    @Override
    public AlarmItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_alarm, parent, false);
        return new AlarmItemViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull AlarmItemViewHolder holder, int position)
    {
        // Producto y contexto
        TimeEvent timeEvent = items.get(position);
        Context context = holder.layout.getContext();

        // Datos del elemento
        holder.textName.setText(timeEvent.getName());
        holder.textTime.setText(timeEvent.getTime().toString());
        holder.textRepeat.setText(timeEvent.getFrequency().name());
        holder.layout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int position = holder.getAdapterPosition();
                if(itemClickListener != null)
                {
                    itemClickListener.onClick(position);
                }
            }
        });
        holder.layout.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View v)
            {
                int position = holder.getAdapterPosition();
                if(itemClickListener != null)
                {
                    itemClickListener.onLongClick(position);
                }
                return false;
            }
        });
    }
}
