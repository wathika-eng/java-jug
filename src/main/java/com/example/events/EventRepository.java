package com.example.events;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByOrderByDateDesc();
    List<Event> findByStatusOrderByDateDesc(String status);
}
