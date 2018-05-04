/*
 * Released under the MIT/X11 License.
 *
 * Copyright (c) 2017 Zbynek Ruzicka
 *
 */

package cz.zr.charcloud;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import cz.zr.charcloud.exc.CharCloudException;
import cz.zr.charcloud.utils.Consts.GeneratedFileName;
import junit.framework.TestCase;

/**
 * {@link Scenario} related test cases.
 *
 * @author zruzicka
 */
@RunWith(JUnit4.class)
// TODO Use JUnit 5.
public class ScenarioTest extends TestCase {

    private static final String VALID_INPUT = "input_att_wiki.txt";

    /**
     * Test for standard and valid scenario.
     *
     * @throws CharCloudException
     * @throws IOException
     */
    @Test
    public void testExecute__validInput__success() throws CharCloudException, IOException {
        new Scenario(new File(VALID_INPUT)).execute();
    }

    /**
     * Non existing input file is specified. Defined exception is expected during scenario execution.
     *
     * @throws CharCloudException
     * @throws IOException
     */
    @Test(expected = CharCloudException.class)
    public void testExecute__nonExistingInput__CharCloudException() throws CharCloudException, IOException {
        Scenario scenario = new Scenario(new File("nonExistingResource.none"));
        scenario.execute();
    }

    /**
     * Generated files are removed before each test method.
     */
    @Before
    public void cleanup() {
        GeneratedFileName[] generatedFileNames = GeneratedFileName.values();
        for (GeneratedFileName generatedFileName : generatedFileNames) {
            File file = new File(generatedFileName.getName());
            file.delete();
        }
    }

}
