package org.devi.domain;

/** Окно здания с признаком величественности и номером этажа. */
public class Window extends WorldObject {
    private final boolean majestic;
    private final int floorNumber;

    public Window(String name, Location location, boolean majestic, int floorNumber) {
        super(name, location);
        if (floorNumber < 1) {
            throw new IllegalArgumentException("Номер этажа должен быть положительным >:0 ");
        }
        this.majestic = majestic;
        this.floorNumber = floorNumber;
    }

    /** Является ли окно величественным??? ужас */
    public boolean isMajestic() {
        return majestic;
    }

    /** Возвращаем номер этажа, на котором расположено окно. */
    public int getFloorNumber() {
        return floorNumber;
    }
}
