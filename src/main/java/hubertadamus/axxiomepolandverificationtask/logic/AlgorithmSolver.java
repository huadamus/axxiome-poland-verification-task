package hubertadamus.axxiomepolandverificationtask.logic;

import hubertadamus.axxiomepolandverificationtask.model.Group;
import hubertadamus.axxiomepolandverificationtask.model.Relation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlgorithmSolver {

    public static Group solve(List<Relation> relationsList) {
        List<Group> groups = getLargestGroupsList(relationsList);
        if(groups.size() == 1) {
            return groups.get(0);
        }
        groups = getGroupsWithMostAcquaintances(groups);
        if(groups.size() == 1) {
            return groups.get(0);
        }
        Collections.sort(groups);
        return groups.get(0);
    }

    static Group getMaximumGroupFromRelationsList(Relation startingGroup, List<Relation> relationsList) {
        Group group = new Group();
        group.add(startingGroup.getName1());
        group.add(startingGroup.getName2());
        for(int i = 0; i < relationsList.size(); i++) {
            if(startingGroup.equals(relationsList.get(i))) {
                continue;
            }
            if(group.containsOne(relationsList.get(i))) {
                String stranger = group.getStranger(relationsList.get(i));
                Group checkGroup = new Group();
                for (Relation relation : relationsList) {
                    if (group.contains(relation.getOther(stranger)) && relation.hasName(stranger)) {
                        checkGroup.add(relation.getOther(stranger));
                    }
                }
                if(group.equals(checkGroup)) {
                    group.add(stranger);
                }
            }
        }
        List<String> knownAcquaintances = new ArrayList<>();
        for (Relation relation : relationsList) {
            if (group.containsOne(relation)) {
                String stranger = group.getStranger(relation);
                if (!knownAcquaintances.contains(stranger)) {
                    group.addAcquaintance();
                    knownAcquaintances.add(stranger);
                }
            }
        }
        return group;
    }

    static List<Group> getLargestGroupsList(List<Relation> relationsList) {
        List<Group> largestGroupsList = new ArrayList<>();
        outer:
        for(int i = 0; i < relationsList.size(); i++) {
            Group group = getMaximumGroupFromRelationsList(relationsList.get(i), relationsList);
            if(largestGroupsList.isEmpty()) {
                largestGroupsList.add(group);
            } else if(group.getSize() == largestGroupsList.get(0).getSize()) {
                //this loop below makes sure the groups in the output list are not occurring several times.
                for (Group sameGroup : largestGroupsList) {
                    if (sameGroup.equals(group)) {
                        continue outer;
                    }
                }
                largestGroupsList.add(group);
            } else if(group.getSize() > largestGroupsList.get(0).getSize()) {
                largestGroupsList.clear();
                largestGroupsList.add(group);
            }
        }
        return largestGroupsList;
    }

    static List<Group> getGroupsWithMostAcquaintances(List<Group> groups) {
        List<Group> groupsWithMostAcquaintances = new ArrayList<>();
        for (Group group : groups) {
            if (groupsWithMostAcquaintances.isEmpty()) {
                groupsWithMostAcquaintances.add(group);
            } else if (group.getAcquaintances() == groupsWithMostAcquaintances.get(0).getAcquaintances()) {
                groupsWithMostAcquaintances.add(group);
            } else if (group.getAcquaintances() > groupsWithMostAcquaintances.get(0).getAcquaintances()) {
                groupsWithMostAcquaintances.clear();
                groupsWithMostAcquaintances.add(group);
            }
        }
        return groupsWithMostAcquaintances;
    }
}