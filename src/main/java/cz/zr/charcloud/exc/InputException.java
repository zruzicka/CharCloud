/*
 * Released under the MIT/X11 License.
 *
 * Copyright (c) 2017 Zbynek Ruzicka
 *
 */

package cz.zr.charcloud.exc;

import java.io.IOException;

/**
 * CharCloud IO Exception.
 * 
 * @author ZRuzicka
 */
public class InputException extends IOException {

    private static final long serialVersionUID = 1L;

    public InputException(Throwable t) {
        super(t);
    }

}
