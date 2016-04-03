package edu.csula.datascience.examples;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Simple test to test our implementation on Stats class
 */
public class SimpleStatsTest {
    private SimpleStats stats;

    @Before
    public void setUp() throws Exception {
        List<Integer> data = Lists.newArrayList(
            13, 18, 13, 14, 13, 16, 14, 21, 13
        );
    }

    @Test
    @Ignore
    public void sum() throws Exception {
        Assert.assertEquals(135, stats.sum());
    }

    @Test
    @Ignore
    public void mean() throws Exception {
        Assert.assertEquals(15, stats.mean(), 0);
    }

    @Test
    @Ignore
    public void median() throws Exception {
        Assert.assertEquals(14, stats.median());
    }

    @Test
    @Ignore
    public void range() throws Exception {
        Assert.assertEquals(8, stats.range());
    }

    @Test
    @Ignore
    public void mode() throws Exception {
        Assert.assertEquals(13, stats.mode());
    }

    @Test
    @Ignore
    public void max() throws Exception {
        Assert.assertEquals(21, stats.max());
    }

    @Test
    @Ignore
    public void min() throws Exception {
        Assert.assertEquals(13, stats.min());
    }
}