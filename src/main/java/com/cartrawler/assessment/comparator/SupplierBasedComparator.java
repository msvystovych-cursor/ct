package com.cartrawler.assessment.comparator;

import com.cartrawler.assessment.model.CarResult;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * A supplied-based comparator
 */
public class SupplierBasedComparator implements Comparator<CarResult> {

    private static final Set<String> valuesOnTop = new HashSet<>();

    {
        valuesOnTop.add("AVIS");
        valuesOnTop.add("BUDGET");
        valuesOnTop.add("ENTERPRISE");
        valuesOnTop.add("FIREFLY");
        valuesOnTop.add("HERTZ");
        valuesOnTop.add("SIXT");
        valuesOnTop.add("THRIFTY");
    }

    @Override
    public int compare(CarResult arg0, CarResult arg1) {
        if (valuesOnTop.contains(arg0.getSupplierName())) {
            return -1;
        }
        if (valuesOnTop.contains(arg1.getSupplierName())) {
            return 1;
        }
        return arg0.getSupplierName().compareTo(arg1.getSupplierName());
    }
}