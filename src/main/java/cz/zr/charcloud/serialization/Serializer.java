/*
 * Released under the MIT/X11 License.
 *
 * Copyright (c) 2017 Zbynek Ruzicka
 */

package cz.zr.charcloud.serialization;

import java.io.IOException;

/**
 * Common interface for serialization.
 *
 * @author ZRuzicka
 */
public interface Serializer {

    /**
     * Writes given input into related output.
     *
     * @param input
     *            Will be written into related output.
     * @throws IOException
     *             in case if operation fails.
     */
    public void write(String input) throws IOException;

    /**
     * Request to finish serialization and to close related output.
     *
     * @throws IOException
     *             in case if operation fails.
     */
    public void finish() throws IOException;

}
