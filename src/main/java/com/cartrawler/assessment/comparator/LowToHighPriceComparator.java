package com.cartrawler.assessment.comparator;

import com.cartrawler.assessment.model.CarResult;

import java.util.Comparator;

/**
 * A low to high price comparator for CarResult objects
 */
public class LowToHighPriceComparator implements Comparator<CarResult> {
    @Override
    public int compare(CarResult o1, CarResult o2) {
        return Double.compare(o1.getRentalCost(), o2.getRentalCost());
    }
}
