package com.martin.lolli;

import android.test.AndroidTestCase;
import android.util.Log;

import com.martin.common.PositionList;
import com.martin.common.Unicator;

public class CommonTests extends AndroidTestCase {

    @Override
    public void testAndroidTestCaseSetupProperly() {
        super.testAndroidTestCaseSetupProperly();
        unicatorTest();
        addRemoveTest();
        PositionListTest();
    }

    public void unicatorTest() {
        int first = Unicator.addItem(5);
        int second = Unicator.addItem(2);
        assertEquals(5, Unicator.getPosition(first));
        assertEquals(2, Unicator.getPosition(second));
        Log.d("FFF", "First key: " + first);
        Log.d("FFF", "Second key: " + second);
    }

    public void addRemoveTest() {
        int first = Unicator.addItem(5);
        int second = Unicator.addItem(2);
        Unicator.removeItem(first);
        Unicator.removeItem(second);
    }

    public void PositionListTest() {
        int first = PositionList.addItem(5);
        int second = PositionList.addItem(1);
        assertEquals(first, 0);
        assertEquals(second, 1);
        assertEquals(PositionList.getPosition(first), 5);
        assertEquals(PositionList.getPosition(second), 1);
    }

}
