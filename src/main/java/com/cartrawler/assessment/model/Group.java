package com.cartrawler.assessment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Group {
    MINI("Mini"), ECONOMY("Economy"), COMPACT("Compact"), OTHER("Other");

    private final String name;

    public static Group determineGroup(String sipp) {

        if (sipp == null) throw new IllegalArgumentException("SIPP cannot be null");

        return switch (sipp.toLowerCase().charAt(0)) {
            case 'm' -> MINI;
            case 'e' -> ECONOMY;
            case 'c' -> COMPACT;
            default -> Group.OTHER;
        };
    }
}
