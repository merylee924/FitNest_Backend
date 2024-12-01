package org.ilisi.participation.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class Location {
    private String locationName;
    private double latitude;
    private double longitude;
    private Map<String, Double> coordinatesAsMap;

}

