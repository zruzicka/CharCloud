/*
 * Released under the MIT/X11 License.
 *
 * Copyright (c) 2017 Zbynek Ruzicka
 *
 */

package cz.zr.charcloud.utils;

import cz.zr.charcloud.CharMetrics;

public class Factory {

    public static CharMetrics createMetrics(char character) {
        return new CharMetrics(character);
    }

    public static CharMetrics createMetrics(char character, int charCounter) {
        return new CharMetrics(character, charCounter);
    }

    public static CharMetrics createMetrics(char character, int charCounter, float percentage) {
        return new CharMetrics(character, charCounter, percentage);
    }

}
