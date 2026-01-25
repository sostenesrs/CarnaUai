package com.br.carnauai.infrastructure.persistence.event;

import com.br.carnauai.domain.event.Event;
import com.br.carnauai.domain.event.EventRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class EventRepositoryImpl implements EventRepository {

    private final Map<Long, Event> store = new HashMap<>();
    private final AtomicLong seq = new AtomicLong(1);

    @Override
    public Event save(Event event) {
        if (event.getId() == null) {
            event.setId(seq.getAndIncrement());
        }
        store.put(event.getId(), event);
        return event;
    }

    @Override
    public Optional<Event> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Event> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }
}
