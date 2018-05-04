/*
 * Released under the MIT/X11 License.
 *
 * Copyright (c) 2017 Zbynek Ruzicka
 *
 */

package cz.zr.charcloud.utils;

/**
 * Constants
 *
 * @author ZRuzicka
 */
public class Consts {

    /** Processed content encoding. */
    public static final String ENCODING = "UTF-8";

    /** Character minimal guaranteed size. */
    public static final int CHAR_MINIMAL_SIZE = 10;

    /** Additional size added based on character occurrence ratio. */
    public static final int CHAR_ADDITIONAL_SIZE_RANGE = 20;

    /**
     * Enumeration with generated file names.
     */
    public enum GeneratedFileName  {

        /** Generated HTML content. */
        HTML("output.html"),

        /** Related CSS. */
        CSS("fontStyle.css"),

        /** Data file with characters metrics and eventual statistics. */
        DATA("metrics.data");

        private final String name;

        /**
         * @param name
         */
        private GeneratedFileName(String name) {
            this.name = name;
        }

        /**
         * @return the name
         */
        public String getName() {
            return name;
        }
    }

	private Consts() {
		// Intentionally left empty.
	}
}
