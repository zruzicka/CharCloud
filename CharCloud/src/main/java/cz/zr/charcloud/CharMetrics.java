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
    private int charCounter;
    private float percentage;

    public CharMetrics(char value) {
        super();
        this.key = "c" + (int) value;
        this.value = value;
    }

    void increment() {
        charCounter++;
    }

    public int getCounterValue() {
        return charCounter;
    }

    void updatePercentage(int totalCharsCounter) {
        percentage = charCounter / (float) totalCharsCounter;
    }

    @Override
    public String toString() {
        return "CharMetrics [key=" + key + ", value=" + value + ", counter=" + charCounter + ", percentage="
                + percentage + "]";
    }

    public String toShortString() {
        return key + "; " + value + "; " + charCounter + "; " + percentage + ";";
    }

}
