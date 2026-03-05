package org.devi.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EventLog {
    private final List<String> events = new ArrayList<>();

    public void add(String event) {
        if (event == null || event.isBlank()) {
            throw new IllegalArgumentException("Event must not be null or blank");
        }
        events.add(event);
    }

    public List<String> events() {
        return Collections.unmodifiableList(events);
    }

    public String last() {
        if (events.isEmpty()) {
            throw new IllegalStateException("No events in log");
        }
        return events.get(events.size() - 1);
    }
}
