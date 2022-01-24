package com.cartrawler.assessment.util;

import com.cartrawler.assessment.model.CarResult;
import com.cartrawler.assessment.model.FuelPolicy;
import com.cartrawler.assessment.model.Group;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UtilTest {

    @Test
    public void testNotHavingDuplicates() {
        List<CarResult> cars = new ArrayList<>();

        cars.add(new CarResult("Volkswagen Polo", "NIZA", "EDMR", 12.81d, FuelPolicy.FULLEMPTY));
        cars.add(new CarResult("Volkswagen Polo", "NIZA", "EDMR", 12.81d, FuelPolicy.FULLEMPTY));
        cars.add(new CarResult("Volkswagen Polo", "NIZA", "EDMR", 12.81d, FuelPolicy.FULLEMPTY));
        cars.add(new CarResult("Volkswagen Polo", "NIZA", "EDMR", 12.81d, FuelPolicy.FULLFULL));
        cars.add(new CarResult("Volkswagen Polo", "NIZA", "ICAV", 12.81d, FuelPolicy.FULLEMPTY));
        cars.add(new CarResult("Volkswagen Polo", "CENTAURO", "EDMR", 12.81d, FuelPolicy.FULLEMPTY));
        cars.add(new CarResult("Ford C-Max Diesel", "NIZA", "CMMD", 22.04d, FuelPolicy.FULLEMPTY));
        cars.add(new CarResult("Renault Scenic Diesel", "NIZA", "JGAD", 93.67d, FuelPolicy.FULLEMPTY));

        Set<CarResult> result = Util.toSet(cars);
        Assert.assertEquals(6, result.size());
    }

    @Test
    @Ignore
    public void testGeneralSort() {

        List<CarResult> cars = new ArrayList<>();
        CarResult car8 = new CarResult("BMW 3 Series", "RHODIUM", "FDAR", 409.68d, FuelPolicy.FULLFULL);
        cars.add(car8);
        cars.add(new CarResult("Opel Corsa", "FLIZZR", "EBMN", 56.51d, FuelPolicy.FULLFULL));

        CarResult car1 = new CarResult("Toyota Avensis", "AVIS", "IDMR", 373.69d, FuelPolicy.FULLFULL);
        cars.add(car1);
        CarResult car2 = new CarResult("Toyota Avensis", "BUDGET", "IDMR", 211.89d, FuelPolicy.FULLFULL);
        cars.add(car2);
        CarResult car3 = new CarResult("Peugeot 107", "ENTERPRISE", "MCMR", 78.1d, FuelPolicy.FULLFULL);
        cars.add(car3);
        CarResult car4 = new CarResult("Volkswagen Touran", "FIREFLY", "IVMR", 125.92d, FuelPolicy.FULLEMPTY);
        cars.add(car4);
        CarResult car5 = new CarResult("Peugeot 107", "HERTZ", "MCMR", 67.98d, FuelPolicy.FULLFULL);
        cars.add(car5);
        CarResult car6 = new CarResult("Audi A3", "SIXT", "ICMR", 186.37d, FuelPolicy.FULLFULL);
        cars.add(car6);
        CarResult car7 = new CarResult("Peugeot 107", "THRIFTY", "MCMR", 67.03d, FuelPolicy.FULLFULL);
        cars.add(car7);

        Set<CarResult> carSet = Util.toSet(cars);
        List<CarResult> sortedList = Util.sort(cars);
        CarResult firstElement = sortedList.get(0);
        CarResult secondElement = sortedList.get(1);
        CarResult lastElement = sortedList.get(sortedList.size() - 1);

        Assert.assertEquals(9, sortedList.size());
        Assert.assertEquals(car1, firstElement);
        Assert.assertEquals(car6, secondElement);
        Assert.assertEquals(car8, lastElement);
    }


    @Test
    public void testDividingIntoGroups() {

        List<CarResult> cars = new ArrayList<>();
        cars.add(new CarResult("Toyota Avensis", "AVIS", "CIDMR", 1d, FuelPolicy.FULLFULL));
        cars.add(new CarResult("Toyota Avensis", "BUDGET", "IDMR", 2d, FuelPolicy.FULLFULL));
        cars.add(new CarResult("Volkswagen Touran", "FIREFLY", "MUMR", 3d, FuelPolicy.FULLEMPTY));
        cars.add(new CarResult("Audi A3", "SIXT", "SICMR", 186.37d, FuelPolicy.FULLFULL));
        cars.add(new CarResult("Audi A3", "SIXT", "MICMR", 186.37d, FuelPolicy.FULLFULL));
        cars.add(new CarResult("Audi A3", "SIXT", "ENTERPRISE", 186.37d, FuelPolicy.FULLFULL));

        Set<CarResult> carSet = Util.toSet(cars);
        Map<Group, List<CarResult>> carGroups = Util.groupBy(carSet);

        Assert.assertEquals(2, carGroups.get(Group.OTHER).size());
        Assert.assertEquals(2, carGroups.get(Group.MINI).size());
        Assert.assertEquals(1, carGroups.get(Group.ECONOMY).size());
        Assert.assertEquals(1, carGroups.get(Group.COMPACT).size());
    }


    @Test
    public void testSortingInsideGroup() {

        List<CarResult> cars = new ArrayList<>();
        CarResult first = new CarResult("Toyota Avensis", "AVIS", "IDMR", 1d, FuelPolicy.FULLFULL);
        cars.add(first);
        CarResult second = new CarResult("Toyota Avensis", "BUDGET", "IDMR", 2d, FuelPolicy.FULLFULL);
        cars.add(second);
        CarResult third = new CarResult("Volkswagen Touran", "FIREFLY", "IVMR", 3d, FuelPolicy.FULLEMPTY);
        cars.add(third);
        CarResult forth = new CarResult("Audi A3", "SIXT", "ICMR", 186.37d, FuelPolicy.FULLFULL);
        cars.add(forth);

        Set<CarResult> carSet = Util.toSet(cars);
        Map<Group, List<CarResult>> carGroups = Util.groupBy(carSet);

        List<CarResult> otherGroup = carGroups.get(Group.OTHER);

        Assert.assertEquals(first, otherGroup.get(0));
        Assert.assertEquals(second, otherGroup.get(1));
        Assert.assertEquals(third, otherGroup.get(2));
        Assert.assertEquals(forth, otherGroup.get(3));

    }
}