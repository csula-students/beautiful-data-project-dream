package edu.csula.datascience.acquisition;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

/**
 * A test case to show how to use Collector and Source
 */
public class CollectorTest {
    private Collector<SimpleModel, MockData> collector;
    private Source<MockData> source;

    @Before
    public void setup() {
        collector = new MockCollector();
        source = new MockSource();
    }

    @Test
    public void download() throws Exception {
        List<SimpleModel> list = (List<SimpleModel>) collector.download(source);

        Assert.assertEquals(list.size(), 3);

        for (int i = 0; i < 3; i ++) {
            Assert.assertEquals(list.get(i).getId(), "" + (i + 1));
            Assert.assertEquals(list.get(i).getContent(), "content" + (i + 1));
        }
    }

    @Test
    public void save() throws Exception {
        Assert.assertTrue(collector.save(collector.download(source)));
    }
}