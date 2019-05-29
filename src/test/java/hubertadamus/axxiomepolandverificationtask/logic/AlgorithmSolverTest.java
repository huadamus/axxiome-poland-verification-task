package hubertadamus.axxiomepolandverificationtask.logic;

import hubertadamus.axxiomepolandverificationtask.model.Group;
import hubertadamus.axxiomepolandverificationtask.model.Relation;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AlgorithmSolverTest {

    @Test
    void solve() {
        List<Relation> relationsList = new ArrayList<>();
        relationsList.add(new Relation("Ala", "Ola"));
        relationsList.add(new Relation("Ola", "Mirek"));
        relationsList.add(new Relation("Janek", "Kasia"));
        relationsList.add(new Relation("Kasia", "Ala"));
        relationsList.add(new Relation("Ala", "Mirek"));
        relationsList.add(new Relation("Janek", "Ala"));
        relationsList.add(new Relation("Mirek", "Ela"));
        relationsList.add(new Relation("Zosia", "Janek"));
        Group results = AlgorithmSolver.solve(relationsList);
        assertEquals(3, Objects.requireNonNull(results).getSize());
        assertEquals("Ala", Objects.requireNonNull(results).getName(0));
        assertEquals("Janek", Objects.requireNonNull(results).getName(1));
        assertEquals("Kasia", Objects.requireNonNull(results).getName(2));
        assertEquals(3, Objects.requireNonNull(results).getAcquaintances());

        relationsList = new ArrayList<>();
        Relation startingRelation = new Relation("1", "2");
        relationsList.add(startingRelation);
        relationsList.add(new Relation("2", "3"));
        relationsList.add(new Relation("1", "4"));
        relationsList.add(new Relation("1", "5"));
        relationsList.add(new Relation("2", "4"));
        relationsList.add(new Relation("5", "4"));
        relationsList.add(new Relation("5", "2"));
        relationsList.add(new Relation("5", "3"));
        relationsList.add(new Relation("2", "6"));
        relationsList.add(new Relation("2", "7"));
        relationsList.add(new Relation("3", "7"));
        relationsList.add(new Relation("6", "7"));
        results = AlgorithmSolver.solve(relationsList);
        assertEquals(4, Objects.requireNonNull(results).getSize());
        assertEquals("1", Objects.requireNonNull(results).getName(0));
        assertEquals("2", Objects.requireNonNull(results).getName(1));
        assertEquals("4", Objects.requireNonNull(results).getName(2));
        assertEquals("5", Objects.requireNonNull(results).getName(3));
        assertEquals(3, Objects.requireNonNull(results).getAcquaintances());
    }

    @Test
    void compare() {
        Group group1 = new Group.GroupBuilder("Ala", "Janek", "Kasia")
                .addAcquaintance()
                .addAcquaintance()
                .addAcquaintance()
                .build();
        Group group2 = new Group.GroupBuilder("Ala", "Mirek", "Ola")
                .addAcquaintance()
                .addAcquaintance()
                .addAcquaintance()
                .build();
        assertTrue(new AlgorithmSolver.GroupComparator().compare(group1, group2) > 0);
    }
}