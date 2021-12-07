package com.enofir.wakeapp.objects;

import android.os.Parcel;
import android.os.Parcelable;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

@SuppressWarnings({"UnusedReturnValue", "unused"})
public class TimeEvent implements Comparable<TimeEvent>, Parcelable
{
    public enum TimeEventFrequency{ DIARY, WEEKLY, MONTHLY }

    private String name;
    private GemaTime time;
    private TimeEventFrequency frequency;
    private boolean enabled;

    public TimeEvent()
    {
        this("Alarma", new GemaTime(), TimeEventFrequency.WEEKLY);
    }

    public TimeEvent(String name, GemaTime time, TimeEventFrequency frequency)
    {
       this.name = name;
       this.time = time;
       this.frequency = frequency;
    }

    public TimeEvent(JSONObject obj)
    {
        this();
        fromJSON(obj);
    }

    protected TimeEvent(Parcel in)
    {
        name = in.readString();
        time = in.readParcelable(GemaTime.class.getClassLoader());
        frequency = TimeEventFrequency.valueOf(in.readString());
    }

    public static final Creator<TimeEvent> CREATOR = new Creator<TimeEvent>()
    {
        @Override
        public TimeEvent createFromParcel(Parcel in)
        {
            return new TimeEvent(in);
        }
        @Override
        public TimeEvent[] newArray(int size)
        {
            return new TimeEvent[size];
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
        dest.writeString(name);
        dest.writeParcelable(time, flags);
        dest.writeString(frequency.name());
    }

    @Override
    public int compareTo(TimeEvent o)
    {
        return time.compareTo(o.time);
    }

    @NotNull
    @Override
    public String toString()
    {
        return toJSON().toString();
    }

    public JSONObject toJSON()
    {
        JSONObject obj = new JSONObject();
        try
        {
            obj.putOpt("name", name);
            obj.putOpt("time", time.toJSON());
            obj.putOpt("frequency", frequency.name());
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return obj;
    }

    public TimeEvent fromJSON(JSONObject obj)
    {
        if(obj != null)
        {
            name = obj.optString("name");
            time.fromJSON(obj.optJSONObject("time"));
            frequency = TimeEventFrequency.valueOf(obj.optString("frequency", TimeEventFrequency.WEEKLY.name()));
        }
        return this;
    }

    private String SelNotEmpty(String current, String newval)
    {
        return (newval == null || newval.trim().isEmpty()) ? current : newval.trim();
    }

    public TimeEvent setName(String name)
    {
        if(name != null)
        {
            this.name = name.trim();
        }
        return this;
    }

    public String getName()
    {
        return name;
    }

    public TimeEvent setTime(GemaTime time)
    {
        this.time = time;
        return this;
    }

    public GemaTime getTime()
    {
        return time;
    }

    public TimeEvent setFrequency(TimeEventFrequency frequency)
    {
        this.frequency = frequency;
        return this;
    }

    public TimeEventFrequency getFrequency()
    {
        return frequency;
    }
}
