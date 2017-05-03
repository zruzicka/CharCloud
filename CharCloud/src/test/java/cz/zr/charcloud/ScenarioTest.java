/*
 * Released under the MIT/X11 License.
 *
 * Copyright (c) 2017 Zbynek Ruzicka
 *
 */

package cz.zr.charcloud;

import java.io.File;

import junit.framework.TestCase;

public class ScenarioTest extends TestCase {

    public void testExecute__att_example() throws Exception {
        new Scenario(new File("input_att_wiki.txt")).execute();
    }

}
