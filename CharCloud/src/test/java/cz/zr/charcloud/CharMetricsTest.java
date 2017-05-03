/*
 * Released under the MIT/X11 License.
 *
 * Copyright (c) 2017 Zbynek Ruzicka
 *
 */

package cz.zr.charcloud;

import junit.framework.Assert;
import junit.framework.TestCase;
import cz.zr.charcloud.utils.Consts;
import cz.zr.charcloud.utils.Factory;

public class CharMetricsTest extends TestCase {

    public void testCalculatePercentage() {
        float expectedPercentage = 1f;
        CharMetrics charMetrics = Factory.createMetrics('a', 10);
        charMetrics.calculatePercentage(10);
        Assert.assertEquals(charMetrics.getPercentage(), expectedPercentage);
    }

    public void testCalculateCharSize__for100Percent() {
        float expectedCharSize = 10f;
        CharMetrics charMetrics = Factory.createMetrics('a', 10, 1f);
        charMetrics.calculateCharSize(Consts.CHAR_MINIMAL_SIZE, Consts.CHAR_ADDITIONAL_SIZE_RANGE);
        Assert.assertEquals(expectedCharSize, charMetrics.getCharSize());
    }

    public void testCalculateCharSize__for0Percent() {
        float expectedCharSize = 30f;
        CharMetrics charMetrics = Factory.createMetrics('a', 10, 0f);
        charMetrics.calculateCharSize(Consts.CHAR_MINIMAL_SIZE, Consts.CHAR_ADDITIONAL_SIZE_RANGE);
        Assert.assertEquals(expectedCharSize, charMetrics.getCharSize());
    }

    public void testCalculateCharSize__for25Percent() {
        float expectedCharSize = 25f;
        CharMetrics charMetrics = Factory.createMetrics('a', 10, 0.25f);
        charMetrics.calculateCharSize(Consts.CHAR_MINIMAL_SIZE, Consts.CHAR_ADDITIONAL_SIZE_RANGE);
        Assert.assertEquals(expectedCharSize, charMetrics.getCharSize());
    }

    public void testCalculateCharSize__for75Percent() {
        float expectedCharSize = 15f;
        CharMetrics charMetrics = Factory.createMetrics('a', 10, 0.75f);
        charMetrics.calculateCharSize(Consts.CHAR_MINIMAL_SIZE, Consts.CHAR_ADDITIONAL_SIZE_RANGE);
        Assert.assertEquals(expectedCharSize, charMetrics.getCharSize());
    }

}
