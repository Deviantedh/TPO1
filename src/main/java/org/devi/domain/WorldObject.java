package org.devi.domain;

import java.util.UUID;

public abstract class WorldObject {
    private final UUID id;
    private final String name;
    private final Location location;

    protected WorldObject(String name, Location location) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Object name must not be null or blank");
        }
        if (location == null) {
            throw new IllegalArgumentException("Object location must not be null");
        }
        this.id = UUID.randomUUID();
        this.name = name;
        this.location = location;
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
}
