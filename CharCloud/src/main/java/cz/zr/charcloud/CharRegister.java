/*
 * Released under the MIT/X11 License.
 *
 * Copyright (c) 2017 Zbynek Ruzicka
 *
 */

package cz.zr.charcloud;

import java.util.ArrayList;
import java.util.Collection;

import cz.zr.charcloud.utils.Factory;

/**
 * {@link CharRegister} carries metrics for processed characters.
 * 
 * @author ZRuzicka
 */
public class CharRegister {

    static int CHAR_KEY_INDEX = 0;
    static int METRICS_INDEX = 1;
    static int REGISTER_LENGTH = 255;

    private final Object[][] register;

    public CharRegister() {
        register = new Object[REGISTER_LENGTH][2];
    }

    public CharMetrics add(char character) {
        CharMetrics metrics = loadMetricsAt(character);
        if (metrics != null) {
            metrics.increment();
        } else {
            metrics = Factory.createMetrics(character);
            metrics.increment(); // The first character occurrence must be counted too.
            register[character][METRICS_INDEX] = metrics;
        }
        return metrics;
    }

    public Collection<CharMetrics> getMetrics() {
        Collection<CharMetrics> result = new ArrayList();
        for (int i = 0; i < REGISTER_LENGTH; i++) {
            CharMetrics metrics = loadMetricsAt(i);
            if (metrics != null && metrics.getCounterValue() > 0) {
                result.add(metrics);
            }
        }
        return result;
    }

    private CharMetrics loadMetricsAt(int index) {
        Object record = register[index][METRICS_INDEX];
        CharMetrics metrics;
        if (record != null && record instanceof CharMetrics) {
            metrics = (CharMetrics) record;
        } else {
            metrics = null;
        }
        return metrics;
    }

}
