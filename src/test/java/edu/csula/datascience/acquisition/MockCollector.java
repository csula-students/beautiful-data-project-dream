package edu.csula.datascience.acquisition;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * A mock implementation of collector for testing
 */
public class MockCollector implements Collector<SimpleModel, MockData> {
    @Override
    public Collection<SimpleModel> mungee(Source<MockData> src) {
        // in your example, you might need to check src.hasNext() first
        return src.next()
            .stream()
            .filter(data -> data.getContent() != null)
            .map(SimpleModel::build)
            .collect(Collectors.toList());
    }

    @Override
    public void save(Collection<SimpleModel> data) {
    }
}
