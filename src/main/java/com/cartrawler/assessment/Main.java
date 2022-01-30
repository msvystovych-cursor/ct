package com.cartrawler.assessment;


import com.cartrawler.assessment.model.CarResult;
import com.cartrawler.assessment.model.Group;
import com.cartrawler.assessment.util.Util;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<CarResult> cars = Data.getInstance().getCars();
        Set<CarResult> sortedCars = Util.sort(cars);
        Map<Group, List<CarResult>> groups = Util.groupBy(sortedCars);


//        System.out.println(groups);
        System.out.println(sortedCars);
    }
}
