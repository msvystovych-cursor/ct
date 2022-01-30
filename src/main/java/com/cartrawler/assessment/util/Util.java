package com.cartrawler.assessment.util;

import com.cartrawler.assessment.comparator.LowToHighPriceComparator;
import com.cartrawler.assessment.comparator.SupplierBasedComparator;
import com.cartrawler.assessment.model.CarResult;
import com.cartrawler.assessment.model.Group;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * A utility class for car result objects manipulations
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Util {

    private static final Set<String> valuesOnTop = new HashSet<>();

   static  {
        valuesOnTop.add("AVIS");
        valuesOnTop.add("BUDGET");
        valuesOnTop.add("ENTERPRISE");
        valuesOnTop.add("FIREFLY");
        valuesOnTop.add("HERTZ");
        valuesOnTop.add("SIXT");
        valuesOnTop.add("THRIFTY");
    }

    /**
     * Converts to HashSet to remove duplicates
     * @param input
     * @return
     */
    public static Set<CarResult> toSet(List<CarResult> input) {
        System.out.println("Initial count: " + input.size());
        HashSet<CarResult> result = new HashSet<>(input);
        System.out.println("Size after removing duplicates = " +result.size());
        return result;
    }

    /**
     * A sorting method
     *
     * @param input an incoming set
     * @return a sorted TreeSet based on a comparator
     */
    public static Set<CarResult> sort(Set<CarResult> input) {
        Set<CarResult> sortedSet = new TreeSet<>(new SupplierBasedComparator());
        sortedSet.addAll(input);
        return sortedSet;
    }

    public static List<CarResult> sort(List<CarResult> arr) {
        List<CarResult> top = new ArrayList<>();
        List<CarResult> other = new ArrayList<>();
        List<CarResult> result = new ArrayList<>();
        for (CarResult item : arr) {
            if (valuesOnTop.contains(item.getSupplierName())) {
                top.add(item);

            } else {
                other.add(item);
            }
        }
        result.addAll(0, top);
        result.addAll(result.size() - 1, other);
        return result;
    }


    /**
     * Groups an incoming set based on group types
     *
     * @param input an incoming set
     * @return an association between a group and a list of values
     */
    public static Map<Group, List<CarResult>> groupBy(Collection<CarResult> input) {
        return input.stream().collect(Collectors.groupingBy(x -> Group.determineGroup(x.getSippCode()), toSortedList(new LowToHighPriceComparator())));
    }

    /**
     * Transforms to a sorted list based on comparator
     *
     * @param input a comparator
     * @return a sorted list
     */
    public static <T> Collector<T, ?, List<T>> toSortedList(Comparator<? super T> input) {
        return Collectors.collectingAndThen(Collectors.toCollection(ArrayList::new), l -> {
            l.sort(input);
            return l;
        });
    }
}
