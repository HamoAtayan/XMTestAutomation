package com.xm.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ExpectedData {

    TITLE("A New Hope"),
    NAME("Biggs Darklighter"),
    STARSHIP_CLASS("Starfighter"),
    PILOT_NAME("Luke Skywalker");


    @Getter
    private final String value;
}
