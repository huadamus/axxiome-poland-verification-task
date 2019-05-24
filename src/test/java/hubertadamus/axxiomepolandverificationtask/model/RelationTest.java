package hubertadamus.axxiomepolandverificationtask.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RelationTest {

    @Test
    void getName1() {
        String name1 = "Mariusz";
        String name2 = "Anna";
        Relation relation = new Relation(name1, name2);
        assertEquals(name1, relation.getName1());
    }

    @Test
    void getName2() {
        String name1 = "Mariusz";
        String name2 = "Anna";
        Relation relation = new Relation(name1, name2);
        assertEquals(name2, relation.getName2());
    }

    @Test
    void getOther() {
        String name1 = "Mariusz";
        String name2 = "Anna";
        Relation relation = new Relation(name1, name2);
        assertEquals(name2, relation.getOther(name1));
        assertEquals(name1, relation.getOther(name2));
    }

    @Test
    void hasName() {
        String name1 = "Mariusz";
        String name2 = "Anna";
        String name3 = "Mateusz";
        Relation relation = new Relation(name1, name2);
        assertTrue(relation.hasName(name1));
        assertTrue(relation.hasName(name2));
        assertFalse(relation.hasName(name3));
    }

    @Test
    void equals() {
        String name1 = "Mariusz";
        String name2 = "Anna";
        Relation relation = new Relation(name1, name2);
        Relation otherRelation = new Relation(name1, name2);
        assertEquals(relation, otherRelation);
    }
}