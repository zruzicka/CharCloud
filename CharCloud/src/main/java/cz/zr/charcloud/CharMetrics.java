/*
 * Released under the MIT/X11 License.
 *
 * Copyright (c) 2017 Zbynek Ruzicka
 *
 */

package cz.zr.charcloud;

public class CharMetrics {

    private final char value;
    private final String key;
    private int counter;

    public CharMetrics(char value) {
        super();
        this.key = "c" + (int) value;
        this.value = value;
    }

    void increment() {
        counter++;
    }

    public int getCounterValue() {
        return counter;
    }

    @Override
    public String toString() {
        return "CharMetrics [key=" + key + ", value=" + value + ", counter=" + counter + "]";
    }

}
