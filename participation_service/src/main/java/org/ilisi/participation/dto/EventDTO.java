package org.ilisi.participation.dto;

import lombok.Data;

@Data
public class EventDTO {
    private Long id;
    private String name;
    private int maxParticipants;
    private int currentNumParticipants;
}
