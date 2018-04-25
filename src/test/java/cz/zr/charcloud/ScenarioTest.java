/*
 * Released under the MIT/X11 License.
 *
 * Copyright (c) 2017 Zbynek Ruzicka
 *
 */

package cz.zr.charcloud;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import cz.zr.charcloud.exc.CharCloudException;
import junit.framework.TestCase;

@RunWith(JUnit4.class)
public class ScenarioTest extends TestCase {

	public void testExecute__att_example() throws Exception {
		new Scenario(new File("input_att_wiki.txt")).execute();
	}

	@Test(expected = CharCloudException.class)
	public void testScenario__CharCloudException() throws CharCloudException {
		new Scenario(new File("nonExistingResource.none")).execute();
	}
}
