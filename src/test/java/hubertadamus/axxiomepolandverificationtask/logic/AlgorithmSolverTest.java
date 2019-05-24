package hubertadamus.axxiomepolandverificationtask.logic;

import hubertadamus.axxiomepolandverificationtask.model.Group;
import hubertadamus.axxiomepolandverificationtask.model.Relation;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class AlgorithmSolverTest {

    @Test
    void solve() {
        List<Relation> relationList = new ArrayList<>();
        relationList.add(new Relation("Ala", "Ola"));
        relationList.add(new Relation("Ola", "Mirek"));
        relationList.add(new Relation("Janek", "Kasia"));
        relationList.add(new Relation("Kasia", "Ala"));
        relationList.add(new Relation("Ala", "Mirek"));
        relationList.add(new Relation("Janek", "Ala"));
        relationList.add(new Relation("Mirek", "Ela"));
        relationList.add(new Relation("Zosia", "Janek"));
        Group results = AlgorithmSolver.solve(relationList);
        assertEquals(3, Objects.requireNonNull(results).getSize());
        assertEquals("Ala", Objects.requireNonNull(results).getName(0));
        assertEquals("Janek", Objects.requireNonNull(results).getName(1));
        assertEquals("Kasia", Objects.requireNonNull(results).getName(2));
    }

    @Test
    void getMaximumGroupFromRelationsList() {
        List<Relation> relationsList = new ArrayList<>();
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
        Group results = AlgorithmSolver.getMaximumGroupFromRelationsList(startingRelation, relationsList);
        assertEquals(4, Objects.requireNonNull(results).getSize());
        assertEquals("1", Objects.requireNonNull(results).getName(0));
        assertEquals("2", Objects.requireNonNull(results).getName(1));
        assertEquals("4", Objects.requireNonNull(results).getName(2));
        assertEquals("5", Objects.requireNonNull(results).getName(3));
        assertEquals(3, Objects.requireNonNull(results).getAcquaintances());
    }

    @Test
    void getLargestGroupsList() {
        List<Relation> relationsList = new ArrayList<>();
        relationsList.add(new Relation("1", "2"));
        relationsList.add(new Relation("2", "3"));
        relationsList.add(new Relation("1", "4"));
        relationsList.add(new Relation("1", "5"));
        relationsList.add(new Relation("2", "4"));
        relationsList.add(new Relation("5", "4"));
        relationsList.add(new Relation("5", "2"));
        relationsList.add(new Relation("5", "3"));
        relationsList.add(new Relation("2", "6"));
        relationsList.add(new Relation("2", "7"));
        relationsList.add(new Relation("3", "6"));
        relationsList.add(new Relation("3", "7"));
        relationsList.add(new Relation("6", "7"));
        Group group1 = new Group();
        group1.add("1");
        group1.add("2");
        group1.add("4");
        group1.add("5");
        Group group2 = new Group();
        group2.add("2");
        group2.add("3");
        group2.add("6");
        group2.add("7");
        List<Group> result = AlgorithmSolver.getLargestGroupsList(relationsList);
        assertEquals(group1, result.get(0));
        assertEquals(group2, result.get(1));
    }

    @Test
    void getGroupsWithMostAcquaintances() {
        List<Group> groupsList = new ArrayList<>();
        Group group1 = new Group();
        group1.add("1");
        group1.add("2");
        group1.add("4");
        group1.add("5");
        group1.addAcquaintance();
        group1.addAcquaintance();
        group1.addAcquaintance();
        groupsList.add(group1);
        Group group2 = new Group();
        group2.add("2");
        group2.add("3");
        group2.add("6");
        group2.add("7");
        group2.addAcquaintance();
        group2.addAcquaintance();
        groupsList.add(group2);
        List<Group> result = AlgorithmSolver.getGroupsWithMostAcquaintances(groupsList);
        assertEquals(group1, result.get(0));
    }
}