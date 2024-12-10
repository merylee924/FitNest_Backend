package org.ilisi.tracking_service.model;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class Location {
    private String locationName;
    private double latitude;
    private double longitude;

}

