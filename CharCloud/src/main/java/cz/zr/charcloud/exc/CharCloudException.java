/*
 * Released under the MIT/X11 License.
 *
 * Copyright (c) 2017 Zbynek Ruzicka
 *
 */

package cz.zr.charcloud.exc;

/**
 * General CharCloud exception.
 * 
 * @author ZRuzicka
 */
public class CharCloudException extends Exception {
    
    private static final long serialVersionUID = 1L;

    public CharCloudException(Throwable t) {
        super(t);
    }

}
