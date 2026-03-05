package org.devi.domain;

public class Window extends WorldObject {
    private final boolean majestic;
    private final int floorNumber;

    public Window(String name, Location location, boolean majestic, int floorNumber) {
        super(name, location);
        if (floorNumber < 1) {
            throw new IllegalArgumentException("Floor number must be positive");
        }
        this.majestic = majestic;
        this.floorNumber = floorNumber;
    }

    public boolean isMajestic() {
        return majestic;
    }

    public int getFloorNumber() {
        return floorNumber;
    }
}
