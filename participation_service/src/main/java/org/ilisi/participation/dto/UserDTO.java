package org.ilisi.participation.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String gender;
    private String profilePicture;
}
