package org.devi.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DomainInvariantsTest {

    // Проверяет инвариант: толпа из одного участника не может ликовать.
    @Test
    void crowdWithSingleMemberCannotCheer() {
        EventLog eventLog = new EventLog();
        Location square = new Location("Square", null);
        Crowd crowd = new Crowd("Small Crowd", square, eventLog);
        crowd.addMember(new Person("Only One", square, eventLog));

        assertThrows(IllegalStateException.class, crowd::cheer);
    }

    // Проверяет, что входные переменные окна читаются корректно.
    @Test
    void windowMajesticFlagShouldBeReadable() {
        Location secondFloor = new Location("SecondFloor", null);
        Window majesticWindow = new Window("Majestic", secondFloor, true, 2);
        Window ordinaryWindow = new Window("Ordinary", secondFloor, false, 2);

        assertTrue(majesticWindow.isMajestic());
        assertFalse(ordinaryWindow.isMajestic());
        assertEquals(2, majesticWindow.getFloorNumber());
    }

    // Проверяет, что moveTo меняет локацию/состояние и пишет событие в журнал.
    @Test
    void moveToShouldChangeLocationAndStateAndLogEvent() {
        EventLog eventLog = new EventLog();
        Location building = new Location("Building", null);
        Location secondFloor = new Location("SecondFloor", building);
        Person arthur = new Person("Arthur", building, eventLog);

        arthur.moveTo(secondFloor);

        assertEquals(secondFloor, arthur.getLocation());
        assertEquals(PersonState.MOVING, arthur.getState());
        assertEquals("Arthur переместился в SecondFloor", eventLog.last());
    }

    // Проверяет, что объект добавляется в локацию и доступен через поиск по имени.
    @Test
    void addObjectShouldPutObjectIntoLocation() {
        Location secondFloor = new Location("SecondFloor", null);
        Podium podium = new Podium("Podium", secondFloor);

        secondFloor.addObject(podium);

        assertEquals(1, secondFloor.getObjects().size());
        assertEquals(podium, secondFloor.getObjects().get(0));
        assertTrue(secondFloor.findObjectByName("Podium").isPresent());
    }

    // Проверяет удаление участника и неизменяемость множества, возвращаемого getMembers.
    @Test
    void removeMemberShouldUpdateMembersAndGetMembersShouldBeUnmodifiable() {
        EventLog eventLog = new EventLog();
        Location square = new Location("Square", null);
        Crowd crowd = new Crowd("People", square, eventLog);
        Person first = new Person("A", square, eventLog);
        Person second = new Person("B", square, eventLog);

        crowd.addMember(first);
        crowd.addMember(second);
        crowd.removeMember(first);

        assertEquals(1, crowd.size());
        assertTrue(crowd.getMembers().contains(second));
        assertFalse(crowd.getMembers().contains(first));
        assertThrows(UnsupportedOperationException.class,
                () -> crowd.getMembers().add(new Person("C", square, eventLog)));
    }

    // Проверяет, что last() возвращает последнее добавленное событие.
    @Test
    void eventLogLastShouldReturnMostRecentEvent() {
        EventLog eventLog = new EventLog();
        eventLog.add("событие 1");
        eventLog.add("событие 2");

        assertEquals("событие 2", eventLog.last());
    }

    // Проверяет базовые геттеры идентификатора и имени у Person и Crowd.
    @Test
    void personAndCrowdGettersShouldReturnIdAndName() {
        EventLog eventLog = new EventLog();
        Location square = new Location("Square", null);
        Person person = new Person("Arthur", square, eventLog);
        Crowd crowd = new Crowd("People", square, eventLog);

        assertNotNull(person.getId());
        assertEquals("Arthur", person.getName());
        assertNotNull(crowd.getId());
        assertEquals("People", crowd.getName());
    }
}
