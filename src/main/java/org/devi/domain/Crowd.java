package org.devi.domain;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

public class Crowd {
    private final UUID id;
    private final String name;
    private final Set<Person> members;
    private final Location location;
    private CrowdState state;
    private final EventLog eventLog;

    public Crowd(String name, Location location, EventLog eventLog) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Crowd name must not be null or blank");
        }
        if (location == null) {
            throw new IllegalArgumentException("Crowd location must not be null");
        }
        this.id = UUID.randomUUID();
        this.name = name;
        this.location = location;
        this.members = new LinkedHashSet<>();
        this.state = CrowdState.CALM;
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

    public CrowdState getState() {
        return state;
    }

    public void addMember(Person member) {
        if (member == null) {
            throw new IllegalArgumentException("Member must not be null");
        }
        members.add(member);
    }

    public void removeMember(Person member) {
        if (member == null) {
            throw new IllegalArgumentException("Member must not be null");
        }
        members.remove(member);
    }

    public int size() {
        return members.size();
    }

    public Set<Person> getMembers() {
        return Collections.unmodifiableSet(members);
    }

    public void cheer() {
        if (members.size() < 2) {
            throw new IllegalStateException("Crowd must have at least 2 members to cheer");
        }
        this.state = CrowdState.CHEERING;
        if (eventLog != null) {
            eventLog.add("Crowd cheered");
        }
    }
}
