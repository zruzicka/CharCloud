/*
 * Released under the MIT/X11 License.
 *
 * Copyright (c) 2017 Zbynek Ruzicka
 *
 */

package cz.zr.charcloud.gen;

import java.io.OutputStream;
import java.util.Collection;

import cz.zr.charcloud.CharMetrics;
import cz.zr.charcloud.exc.InputException;

public class MetricsSerializer extends AbstractGenerator {

    public MetricsSerializer(OutputStream output) {
        super(output);
    }

    public void serialize(Collection<CharMetrics> metrics) throws InputException {
        for (CharMetrics charMetrics : metrics) {
            write(charMetrics.toShortString() + "\n");
        }
    }

}
