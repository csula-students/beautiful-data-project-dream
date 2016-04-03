package edu.csula.datascience.examples;

import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * A simple math statistics programming exercise
 */
public class SimpleStats {
    private final List<Integer> data;
    public SimpleStats(List<Integer> data) {
        this.data = data;
    }

    /**
     * Sum of entire list of numbers
     */
    public int sum() {
        int total = 0;
        for (Integer item: data) {
            total += item;
        }
        return total;
    }

    /**
     * Average of the numbers
     */
    public double mean() {
        return sum() / data.size();
    }

    /**
     * Sort the numbers, median is the middle value of the sorted list
     */
    public int median() {
        Collections.sort(data);
        if (data.size() % 2 != 0) {
            return data.get(data.size() / 2);
        } else {
            return (data.get(data.size() / 2) + data.get(data.size() / 2 + 1)) / 2;
        }
    }

    /**
     * The value of max minus min
     */
    public int range() {
        return max() - min();
    }

    /**
     * The number that repeats the most in the numbers
     */
    public int mode() {
        Map<Integer, Integer> count = Maps.newTreeMap();
        int result = -1;

        for (Integer item: data) {
            count.put(
                item,
                count.getOrDefault(item, 0) + 1
            );
        }

        Iterator<Map.Entry<Integer, Integer>> iterator = count.entrySet().iterator();

        Integer maxCount = 0;
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> entry = iterator.next();

            if (maxCount < entry.getValue()) {
                maxCount = entry.getValue();
                result = entry.getKey();
            }
        }

        return result;
    }

    /**
     * Maximum number of the numbers
     */
    public int max() {
        return Collections.max(data);
    }

    /**
     * Minimum number of the numbers
     */
    public int min() {
        return Collections.min(data);
    }
}
