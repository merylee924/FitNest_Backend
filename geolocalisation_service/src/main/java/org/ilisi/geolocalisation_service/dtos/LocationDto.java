package org.ilisi.geolocalisation_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationDto {

    private String locationName;
    private double latitude;
    private double longitude;
}