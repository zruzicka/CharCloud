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
 * Generates CSS output.
 *
 * @author ZRuzicka
 */
public class CSSSerializer extends AbstractSerializer {

    public CSSSerializer(OutputStream output) {
        super(output);
    }

    public void serialize(Collection<CharMetrics> metrics) throws IOException {
        for (CharMetrics charMetrics : metrics) {
            add(charMetrics);
        }
    }

    public void add(CharMetrics metrics) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("span." + metrics.getKey() + " {font-size: ");
        sb.append(metrics.getCharSize());
        sb.append("px;}\n");
        write(sb.toString());
    }

}