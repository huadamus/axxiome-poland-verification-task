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
        Collections.sort(groups);
        return groups.get(0);
    }

    static Group getMaximumGroupFromRelationsList(Relation startingGroup, List<Relation> relationsList) {
        Group.GroupBuilder groupBuilder = new Group.GroupBuilder(startingGroup.getName1(), startingGroup.getName2());
        for(int i = 0; i < relationsList.size(); i++) {
            if(startingGroup.equals(relationsList.get(i))) {
                continue;
            }
            Group unfinishedGroup = groupBuilder.build();
            if (unfinishedGroup.containsOne(relationsList.get(i))) {
                String stranger = unfinishedGroup.getStranger(relationsList.get(i));
                Group.GroupBuilder checkGroupBuilder = new Group.GroupBuilder();
                for (Relation relation : relationsList) {
                    if (unfinishedGroup.contains(relation.getOther(stranger)) && relation.hasName(stranger)) {
                        checkGroupBuilder.addName(relation.getOther(stranger));
                    }
                }
                if (unfinishedGroup.equals(checkGroupBuilder.build())) {
                    groupBuilder.addName(stranger);
                }
            }
        }
        List<String> knownAcquaintances = new ArrayList<>();
        for (Relation relation : relationsList) {
            Group unfinishedGroup = groupBuilder.build();
            if (unfinishedGroup.containsOne(relation)) {
                String stranger = unfinishedGroup.getStranger(relation);
                if (!knownAcquaintances.contains(stranger)) {
                    groupBuilder.addAcquaintance();
                    knownAcquaintances.add(stranger);
                }
            }
        }
        return groupBuilder.build();
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
}