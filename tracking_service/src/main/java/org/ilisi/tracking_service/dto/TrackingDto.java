package org.ilisi.tracking_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrackingDto {
    private Long participantId;
    private Long eventId;
    private double latitude;
    private double longitude;
}
