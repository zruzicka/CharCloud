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
    private float charSize;

    public CharMetrics(char value) {
        super();
        this.key = "c" + (int) value;
        this.value = value;
    }

    public CharMetrics(char value, int charCounter) {
        this(value);
        this.charCounter = charCounter;
    }

    public CharMetrics(char value, int charCounter, float percentage) {
        this(value);
        this.charCounter = charCounter;
        this.percentage = percentage;
    }

    void increment() {
        charCounter++;
    }

    public int getCounterValue() {
        return charCounter;
    }

    public char getCharValue() {
        return value;
    }

    public String getKey() {
        return key;
    }

    void calculatePercentage(int totalCharsCounter) {
        percentage = charCounter / (float) totalCharsCounter;
    }

    public float getPercentage() {
        return percentage;
    }

    public void calculateCharSize(int minimalSize, int additionalSizeRange) {
        charSize = minimalSize + (additionalSizeRange * (int) ((1 - getPercentage()) * 100)) / 100f;
    }

    public float getCharSize() {
        return charSize;
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
