package org.devi.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class Location {
    private final UUID id;
    private final String name;
    private final Location parent;
    private final List<WorldObject> objects;

    public Location(String name, Location parent) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Location name must not be null or blank");
        }
        this.id = UUID.randomUUID();
        this.name = name;
        this.parent = parent;
        this.objects = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Location getParent() {
        return parent;
    }

    public void addObject(WorldObject object) {
        if (object == null) {
            throw new IllegalArgumentException("Object must not be null");
        }
        if (!Objects.equals(object.getLocation(), this)) {
            throw new IllegalArgumentException("Object location must match this location");
        }
        objects.add(object);
    }

    public List<WorldObject> getObjects() {
        return Collections.unmodifiableList(objects);
    }

    public Optional<WorldObject> findObjectByName(String objectName) {
        if (objectName == null || objectName.isBlank()) {
            throw new IllegalArgumentException("Object name must not be null or blank");
        }
        return objects.stream().filter(o -> o.getName().equals(objectName)).findFirst();
    }
}
