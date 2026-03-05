package org.devi.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DomainInvariantsTest {

    @Test
    void crowdWithSingleMemberCannotCheer() {
        EventLog eventLog = new EventLog();
        Location square = new Location("Square", null);
        Crowd crowd = new Crowd("Small Crowd", square, eventLog);
        crowd.addMember(new Person("Only One", square, eventLog));

        assertThrows(IllegalStateException.class, crowd::cheer);
    }

    @Test
    void windowMajesticFlagShouldBeReadable() {
        Location secondFloor = new Location("SecondFloor", null);
        Window majesticWindow = new Window("Majestic", secondFloor, true, 2);
        Window ordinaryWindow = new Window("Ordinary", secondFloor, false, 2);

        assertTrue(majesticWindow.isMajestic());
        assertFalse(ordinaryWindow.isMajestic());
        assertEquals(2, majesticWindow.getFloorNumber());
    }

    @Test
    void moveToShouldChangeLocationAndState() {
        EventLog eventLog = new EventLog();
        Location building = new Location("Building", null);
        Location secondFloor = new Location("SecondFloor", building);
        Person arthur = new Person("Arthur", building, eventLog);

        arthur.moveTo(secondFloor);

        assertEquals(secondFloor, arthur.getLocation());
        assertEquals(PersonState.MOVING, arthur.getState());
    }

    @Test
    void addObjectShouldPutObjectIntoLocation() {
        Location secondFloor = new Location("SecondFloor", null);
        Podium podium = new Podium("Podium", secondFloor);

        secondFloor.addObject(podium);

        assertEquals(1, secondFloor.getObjects().size());
        assertEquals(podium, secondFloor.getObjects().get(0));
        assertTrue(secondFloor.findObjectByName("Podium").isPresent());
    }
}
