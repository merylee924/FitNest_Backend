package org.ilisi.event.entities;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "sports")
@Getter
@NoArgsConstructor
@Setter
public class SportCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "boolean default false" ,name = "requiresroute")
    private boolean requiresRoute = false;
    @Column(name = "iconname")
    private String iconName;

}
