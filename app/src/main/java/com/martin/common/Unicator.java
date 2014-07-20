package com.martin.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Unicator {

    private static Map<Integer, Integer> mNumbers = new HashMap<Integer, Integer>();
    private static int mNext = 0;

    public static int addItem(int index) {
        mNumbers.put(mNext, index);
        return mNext++;
    }

    public static int getPosition(int key) {
        return mNumbers.get(key);
    }

    public static void removeItem(int key) {
        mNumbers.remove(key);
    }
}
