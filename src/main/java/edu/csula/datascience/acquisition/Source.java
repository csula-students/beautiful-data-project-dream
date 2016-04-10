package edu.csula.datascience.acquisition;

import java.util.Collection;

/**
 * Data source interface
 */
public interface Source<T> {
    Collection<T> provide();
}
