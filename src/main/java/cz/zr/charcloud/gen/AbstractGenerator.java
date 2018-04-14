/*
 * Released under the MIT/X11 License.
 *
 * Copyright (c) 2017 Zbynek Ruzicka
 *
 */

package cz.zr.charcloud.gen;

import java.io.IOException;
import java.io.OutputStream;

import cz.zr.charcloud.exc.InputException;
import cz.zr.charcloud.utils.Consts;

/**
 * Common {@link AbstractGenerator} for output stream manipulation.
 * 
 * @author ZRuzicka
 */
public abstract class AbstractGenerator implements Generator {

    private final OutputStream output;

    public AbstractGenerator(OutputStream output) {
        super();
        this.output = output;
    }

    protected void write(String string) throws InputException {
        try {
            output.write(string.getBytes(Consts.ENCODING));
        } catch (IOException e) {
            throw new InputException(e);
        }
    }

    public void finish() throws IOException {
        output.flush();
        output.close();
    }
}
