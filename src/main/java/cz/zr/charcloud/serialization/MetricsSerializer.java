/*
 * Released under the MIT/X11 License.
 *
 * Copyright (c) 2017 Zbynek Ruzicka
 *
 */

package cz.zr.charcloud.serialization;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;

import cz.zr.charcloud.CharMetrics;

/**
 * {@link MetricsSerializer} serializes individual calculated {@link CharMetrics}.
 *
 * @author ZRuzicka
 */
public class MetricsSerializer extends AbstractSerializer {

    public MetricsSerializer(OutputStream output) {
        super(output);
    }

    public void serialize(Collection<CharMetrics> metrics) throws IOException {
        for (CharMetrics charMetrics : metrics) {
            write(charMetrics.toShortString() + "\n");
        }
    }

}
