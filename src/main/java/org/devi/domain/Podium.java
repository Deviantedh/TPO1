package org.devi.domain;

public class Podium extends WorldObject {
    private Person speaker;

    public Podium(String name, Location location) {
        super(name, location);
    }

    public Person getSpeaker() {
        return speaker;
    }

    public void setSpeaker(Person speaker) {
        this.speaker = speaker;
    }
}
