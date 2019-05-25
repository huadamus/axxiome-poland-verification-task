package hubertadamus.axxiomepolandverificationtask.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GroupTest {

    @Test
    void getName() {
        String name1 = "Zbigniew";
        String name2 = "Ryszard";
        String name3 = "Eliza";
        Group group = new Group.GroupBuilder(name1, name2, name3).build();
        assertEquals(name3, group.getName(0));
    }

    @Test
    void getSize() {
        String name1 = "Zbigniew";
        String name2 = "Ryszard";
        String name3 = "Eliza";
        Group group = new Group.GroupBuilder(name1, name2, name3).build();
        assertEquals(3, group.getSize());
    }

    @Test
    void contains() {
        String name1 = "Zbigniew";
        String name2 = "Ryszard";
        String name3 = "Eliza";
        String name4 = "Maria";
        Group group = new Group.GroupBuilder(name1, name2, name3).build();
        assertTrue(group.contains(name2));
        assertFalse(group.contains(name4));
    }

    @Test
    void containsOne() {
        String name1 = "Zbigniew";
        String name2 = "Ryszard";
        String name3 = "Eliza";
        String name4 = "Maria";
        Group group = new Group.GroupBuilder(name1, name2).build();
        Relation relation1 = new Relation(name1, name4);
        Relation relation2 = new Relation(name3, name4);
        Relation relation3 = new Relation(name1, name2);
        assertTrue(group.containsOne(relation1));
        assertFalse(group.containsOne(relation2));
        assertFalse(group.containsOne(relation3));
    }

    @Test
    void getAcquaintances() {
        Group.GroupBuilder groupBuilder = new Group.GroupBuilder();
        groupBuilder.addAcquaintance();
        groupBuilder.addAcquaintance();
        groupBuilder.addAcquaintance();
        groupBuilder.addAcquaintance();
        groupBuilder.addAcquaintance();
        assertEquals(5, groupBuilder.build().getAcquaintances());
    }

    @Test
    void getStranger() {
        String name1 = "Zbigniew";
        String name2 = "Ryszard";
        String name3 = "Eliza";
        String name4 = "Maria";
        Group group = new Group.GroupBuilder(name1, name2, name3).build();
        Relation relation = new Relation(name2, name4);
        assertEquals(name4, group.getStranger(relation));
    }

    @Test
    void compareTo() {
        String name1 = "Zbigniew";
        String name2 = "Ryszard";
        String name3 = "Mariusz";
        String name4 = "Aleksander";
        String name5 = "Zygmunt";
        Group group1 = new Group.GroupBuilder(name1, name2, name3, name4).build();
        Group group2 = new Group.GroupBuilder(name1, name2, name3, name5).build();
        Group group3 = new Group.GroupBuilder(name1, name2, name3).build();
        assertTrue(group1.compareTo(group2) < 0);
        assertTrue(group1.compareTo(group3) > 0);
        assertTrue(group2.compareTo(group3) > 0);
    }

    @Test
    void equals() {
        String name1 = "Zbigniew";
        String name2 = "Ryszard";
        String name3 = "Amadea";
        String name4 = "Łukasz";
        Group group1 = new Group.GroupBuilder(name1, name2).build();
        Group group2 = new Group.GroupBuilder(name1, name2).build();
        Group group3 = new Group.GroupBuilder(name3, name4).build();
        assertEquals(group1, group2);
        assertNotEquals(group1, group3);
    }
}