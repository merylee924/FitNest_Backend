package org.ilisi.event.repository;
import org.ilisi.event.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event,Long> {


}
