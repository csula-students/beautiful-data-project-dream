package edu.csula.datascience.examples;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.*;

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
        return data.stream()
            .mapToInt(Integer::intValue)
            .sum();
    }

    /**
     * Average of the numbers
     */
    public double mean() {
        OptionalDouble avg = data.stream()
            .mapToInt(Integer::intValue)
            .average();

        return avg.isPresent() ? avg.getAsDouble() : 0;
    }

    /**
     * Sort the numbers, median is the middle value of the sorted list
     */
    public int median() {
        List<Integer> copyList = Lists.newArrayList(data);
        Collections.sort(copyList);

        if (copyList.size() == 0) {
            return 0;
        } else if (copyList.size() % 2 != 0) {
            return copyList.get(copyList.size() / 2);
        } else {
            return (copyList.get(copyList.size() / 2) + copyList.get(copyList.size() / 2 - 1)) / 2;
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
