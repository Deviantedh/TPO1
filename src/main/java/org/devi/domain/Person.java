package org.devi.domain;

import java.util.UUID;

public class Person {
    private final UUID id;
    private final String name;
    private Location location;
    private PersonState state;
    private final EventLog eventLog;

    public Person(String name, Location location, EventLog eventLog) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Person name must not be null or blank");
        }
        if (location == null) {
            throw new IllegalArgumentException("Person location must not be null");
        }
        this.id = UUID.randomUUID();
        this.name = name;
        this.location = location;
        this.state = PersonState.IDLE;
        this.eventLog = eventLog;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public PersonState getState() {
        return state;
    }

    public void moveTo(Location location) {
        if (location == null) {
            throw new IllegalArgumentException("Location must not be null");
        }
        this.location = location;
        this.state = PersonState.MOVING;
        if (eventLog != null) {
            eventLog.add(name + " moved to " + location.getName());
        }
    }

    public void slideTo(WorldObject target) {
        if (target == null) {
            throw new IllegalArgumentException("Target must not be null");
        }
        this.state = PersonState.SLIDING;
        this.location = target.getLocation();
        if (eventLog != null) {
            eventLog.add(name + " started sliding to " + target.getName());
        }
    }

    public void say(String phrase) {
        if (phrase == null || phrase.isBlank()) {
            throw new IllegalArgumentException("Phrase must not be null or blank");
        }
        this.state = PersonState.SPEAKING;
        if (eventLog != null) {
            eventLog.add(name + " addressed people: " + phrase);
        }
    }
}
