package com.enofir.wakeapp.interfaces;

public interface ItemClickListener
{
    void onClick(int position);
    default void onLongClick(int position){};
}
