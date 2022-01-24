package com.cartrawler.assessment.model;

import lombok.Data;
import lombok.ToString;

import java.util.Objects;

@Data
@ToString
public class CarResult {

    private final String description;
    private final String supplierName;
    private final String sippCode;
    private final double rentalCost;
    private final FuelPolicy fuelPolicy;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarResult carResult = (CarResult) o;
        return Double.compare(carResult.rentalCost, rentalCost) == 0 && supplierName.equals(carResult.supplierName) && sippCode.equals(carResult.sippCode) && fuelPolicy == carResult.fuelPolicy;
    }

    @Override
    public int hashCode() {
        return Objects.hash(supplierName, sippCode, rentalCost, fuelPolicy);
    }
}
