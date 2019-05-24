package hubertadamus.axxiomepolandverificationtask.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GroupTest {

    @Test
    void add() {
        Group group = new Group();
        String name1 = "Zbigniew";
        String name2 = "Ryszard";
        group.add(name1);
        group.add(name2);
        //no way to test this independently
        assertEquals(2, group.getSize());
    }

    @Test
    void getName() {
        Group group = new Group();
        String name1 = "Zbigniew";
        String name2 = "Ryszard";
        String name3 = "Eliza";
        group.add(name1);
        group.add(name2);
        group.add(name3);
        assertEquals(name3, group.getName(0));
    }

    @Test
    void getSize() {
        Group group = new Group();
        String name1 = "Zbigniew";
        String name2 = "Ryszard";
        String name3 = "Eliza";
        group.add(name1);
        group.add(name2);
        group.add(name3);
        assertEquals(3, group.getSize());
    }

    @Test
    void contains() {
        Group group = new Group();
        String name1 = "Zbigniew";
        String name2 = "Ryszard";
        String name3 = "Eliza";
        String name4 = "Maria";
        group.add(name1);
        group.add(name2);
        group.add(name3);
        assertTrue(group.contains(name2));
        assertFalse(group.contains(name4));
    }

    @Test
    void containsOne() {
        Group group = new Group();
        String name1 = "Zbigniew";
        String name2 = "Ryszard";
        String name3 = "Eliza";
        String name4 = "Maria";
        group.add(name1);
        group.add(name2);
        Relation relation1 = new Relation(name1, name4);
        Relation relation2 = new Relation(name3, name4);
        Relation relation3 = new Relation(name1, name2);
        assertTrue(group.containsOne(relation1));
        assertFalse(group.containsOne(relation2));
        assertFalse(group.containsOne(relation3));
    }

    @Test
    void addAcquaintance() {
        Group group = new Group();
        group.addAcquaintance();
        group.addAcquaintance();
        group.addAcquaintance();
        assertEquals(3, group.getAcquaintances());
    }

    @Test
    void getAcquaintances() {
        Group group = new Group();
        group.addAcquaintance();
        group.addAcquaintance();
        group.addAcquaintance();
        group.addAcquaintance();
        group.addAcquaintance();
        assertEquals(5, group.getAcquaintances());
    }

    @Test
    void getStranger() {
        Group group = new Group();
        String name1 = "Zbigniew";
        String name2 = "Ryszard";
        String name3 = "Eliza";
        String name4 = "Maria";
        group.add(name1);
        group.add(name2);
        group.add(name3);
        Relation relation = new Relation(name2, name4);
        assertEquals(name4, group.getStranger(relation));
    }

    @Test
    void compareTo() {
        Group group1 = new Group();
        String name1 = "Zbigniew";
        String name2 = "Ryszard";
        String name3 = "Mariusz";
        String name4 = "Aleksander";
        String name5 = "Zygmunt";
        group1.add(name1);
        group1.add(name2);
        group1.add(name3);
        group1.add(name4);
        Group group2 = new Group();
        group2.add(name1);
        group2.add(name2);
        group2.add(name3);
        group2.add(name5);
        Group group3 = new Group();
        group3.add(name1);
        group3.add(name2);
        group3.add(name3);
        assertTrue(group1.compareTo(group2) < 0);
        assertTrue(group1.compareTo(group3) > 0);
        assertTrue(group2.compareTo(group3) > 0);
    }

    @Test
    void equals() {
        Group group1 = new Group();
        String name1 = "Zbigniew";
        String name2 = "Ryszard";
        group1.add(name1);
        group1.add(name2);
        Group group2 = new Group();
        group2.add(name1);
        group2.add(name2);
        Group group3 = new Group();
        String name3 = "Amadea";
        String name4 = "Łukasz";
        group3.add(name3);
        group3.add(name4);
        assertEquals(group1, group2);
        assertNotEquals(group1, group3);
    }
}