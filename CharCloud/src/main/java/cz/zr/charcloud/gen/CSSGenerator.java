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

public class CSSGenerator extends AbstractGenerator {

    public CSSGenerator(OutputStream output) {
        super(output);
    }

    public void generate(Collection<CharMetrics> metrics) throws InputException {
        for (CharMetrics charMetrics : metrics) {
            add(charMetrics);
        }
    }

    public void add(CharMetrics metrics) throws InputException {
        StringBuilder sb = new StringBuilder();
        sb.append("span." + metrics.getKey() + " {font-size: ");
        sb.append(metrics.getCharSize());
        sb.append("px;}\n");
        write(sb.toString());
    }

}