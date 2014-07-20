package com.martin.common;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PositionList {

    private ArrayList<Integer> mValues;
    private int mNext = 0;

    public PositionList(int size) {
        mValues = new ArrayList<Integer>(size);
    }

    public int addItem(int position) {
        mValues.add(position, mNext);
        return mNext++;
    }

    public int getPosition(int key) {
        return mValues.indexOf(key);
    }

    public void removeItem(int key) {
        int position = mValues.indexOf(key);
        mValues.remove(position);
    }
}
