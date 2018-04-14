/*
 * Released under the MIT/X11 License.
 *
 * Copyright (c) 2017 Zbynek Ruzicka
 *
 */

package cz.zr.charcloud;

/**
 * Individual character metrics.
 * 
 * @author ZRuzicka
 */
public class CharMetrics {

    private final char value;
    private final String key;
    private int charCounter;
    private float occurrenceRatio;
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
        this.occurrenceRatio = percentage;
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

    /**
     * Calculates occurrence ratio with rounding.
     * 
     * @param totalCharsCounter
     */
    void calculatePercentage(int totalCharsCounter) {
        occurrenceRatio = (int) ((charCounter / (float) totalCharsCounter) * 100) / 100f;
    }

    public float getOccurrenceRatio() {
        return occurrenceRatio;
    }

    /**
     * Character size is calculated as inversely proportional to character occurrence ratio.
     * 
     * @param minimalSize
     *            Minimal guaranteed size.
     * @param additionalSizeRange
     *            Additional size added based on occurrence ratio.
     */
    public void calculateCharSize(int minimalSize, int additionalSizeRange) {
        charSize = minimalSize + (additionalSizeRange * (int) ((1 - getOccurrenceRatio()) * 100)) / 100f;
    }

    public float getCharSize() {
        return charSize;
    }

    @Override
    public String toString() {
        return "CharMetrics [key=" + key + ", value=" + value + ", counter=" + charCounter + ", percentage="
                + occurrenceRatio + "]";
    }

    public String toShortString() {
        return key + "; " + value + "; " + charCounter + "; " + occurrenceRatio + ";";
    }

}
