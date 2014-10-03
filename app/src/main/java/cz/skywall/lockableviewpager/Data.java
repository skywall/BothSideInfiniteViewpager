package cz.skywall.lockableviewpager;

import java.util.ArrayList;

/**
 * Created by lukas on 3.10.14.
 * LockableViewPager
 */
// Dummy class which provides some data
public class Data {

    ArrayList<String> strings = new ArrayList<String>();

    public Data() {
        initStrings();
    }

    public void initStrings() {
        strings.add("A");
        strings.add("B");
        strings.add("C");
        strings.add("D");
        strings.add("E");
        strings.add("F");
        strings.add("G");
        strings.add("H");
        strings.add("CH");
        strings.add("I");
        strings.add("J");
        strings.add("K");
        strings.add("L");
        strings.add("M");
        strings.add("A");
        strings.add("B");
        strings.add("C");
        strings.add("D");
        strings.add("E");
        strings.add("F");
        strings.add("G");
        strings.add("H");
        strings.add("CH");
        strings.add("I");
        strings.add("J");
        strings.add("K");
        strings.add("L");
        strings.add("M");
        strings.add("A");
        strings.add("B");
        strings.add("C");
        strings.add("D");
        strings.add("E");
        strings.add("F");
        strings.add("G");
        strings.add("H");
        strings.add("CH");
        strings.add("I");
        strings.add("J");
        strings.add("K");
        strings.add("L");
        strings.add("M");
    }

    public String getMyString(int pos) {
        if (pos < 0 || pos >= strings.size()) {
            return null;
        }
        return strings.get(pos);
    }
}