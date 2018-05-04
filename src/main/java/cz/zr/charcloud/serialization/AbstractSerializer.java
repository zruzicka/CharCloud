/*
 * Released under the MIT/X11 License.
 *
 * Copyright (c) 2017 Zbynek Ruzicka
 *
 */

package cz.zr.charcloud.serialization;

import java.io.IOException;
import java.io.OutputStream;

import cz.zr.charcloud.utils.Consts;

/**
 * Common {@link AbstractSerializer} for output stream manipulation.
 *
 * @author ZRuzicka
 */
public abstract class AbstractSerializer implements Serializer {

    private final OutputStream output;

    public AbstractSerializer(OutputStream output) {
        super();
        this.output = output;
    }

    @Override
    public void write(String string) throws IOException {
        output.write(string.getBytes(Consts.ENCODING));
    }

    @Override
    public void finish() throws IOException {
        output.flush();
        output.close();
    }
}
