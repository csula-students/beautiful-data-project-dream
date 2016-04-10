package edu.csula.datascience.acquisition;

import com.google.common.collect.Lists;

import java.util.Collection;

/**
 * A mock source to provide data
 */
public class MockSource implements Source<MockData> {
    @Override
    public Collection<MockData> provide() {
        return Lists.newArrayList(
            new MockData("1", "content1"),
            new MockData("2", "content2"),
            new MockData("3", "content3")
        );
    }
}
