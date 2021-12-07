package com.enofir.wakeapp.objects;

import android.os.Parcel;
import android.os.Parcelable;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.jetbrains.annotations.NotNull;
import java.util.Calendar;
import java.util.Locale;

@SuppressWarnings({"UnusedReturnValue", "unused"})
public class GemaTime implements Comparable<GemaTime>, Parcelable
{
    private int hour = 0, min = 0;

    public GemaTime()
    {
        Calendar calendar = Calendar.getInstance();
        setTime(calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE));
    }

    public GemaTime(int hour, int min)
    {
        setTime(hour, min);
    }

    public GemaTime(JSONObject obj)
    {
        fromJSON(obj);
    }

    protected GemaTime(Parcel in)
    {
        setHour(in.readInt());
        setMin(in.readInt());
    }

    public static final Creator<GemaTime> CREATOR = new Creator<GemaTime>()
    {
        @Override
        public GemaTime createFromParcel(Parcel in)
        {
            return new GemaTime(in);
        }
        @Override
        public GemaTime[] newArray(int size)
        {
            return new GemaTime[size];
        }
    };

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(hour);
        dest.writeInt(min);
    }

    @Override
    public int compareTo(GemaTime o)
    {
        // Las horas son iguales, retorno la diferencia en minuto
        if((hour - o.hour) == 0)
        {
            return min - o.min;
        }
        return hour - o.hour;
    }

    @NotNull
    @Override
    public String toString()
    {
        return String.format(Locale.US, "%02d:%02d", hour, min);
    }

    public JSONObject toJSON()
    {
        JSONObject obj = new JSONObject();
        try
        {
            obj.put("hour", hour);
            obj.put("min", min);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return obj;
    }

    public void fromJSON(JSONObject obj)
    {
        if(obj != null)
        {
            setHour(obj.optInt("hour", 0));
            setMin(obj.optInt("min", 0));
        }
    }

    public GemaTime setTimeToNow()
    {
        Calendar calendar = Calendar.getInstance();
        setTime(calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE));
        return this;
    }

    public void setTime(int hour, int min)
    {
        setHour(hour);
        setMin(min);
    }

    public void setHour(int hour)
    {
        if((hour < 0) || (hour >23))
        {
            return;
        }
        this.hour = hour;
    }

    public int getHour()
    {
        return hour;
    }

    public void setMin(int min)
    {
        if((min < 0) || (min > 59))
        {
            return;
        }
        this.min = min;
    }

    public int getMin()
    {
        return min;
    }

}