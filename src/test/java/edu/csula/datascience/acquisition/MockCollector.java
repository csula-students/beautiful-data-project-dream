package edu.csula.datascience.acquisition;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * A mock implementation of collector for testing
 */
public class MockCollector implements Collector<SimpleModel, MockData> {
    @Override
    public Collection<SimpleModel> download(Source<MockData> src) {
        return src.provide()
            .stream()
            .map(SimpleModel::build)
            .collect(Collectors.toList());
    }

    @Override
    public boolean save(Collection<SimpleModel> data) {
        // TODO: maybe replace with file write and test file
        return true;
    }
}
