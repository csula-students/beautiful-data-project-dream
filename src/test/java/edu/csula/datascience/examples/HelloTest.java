package edu.csula.datascience.examples;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * A simple sanity test to test name
 */
public class HelloTest {

    @Test
    public void getName() throws Exception {
        Hello eric = new Hello("eric");

        Assert.assertEquals("eric", eric.getName());
    }
}