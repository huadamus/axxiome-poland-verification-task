package hubertadamus.axxiomepolandverificationtask.logic;

import hubertadamus.axxiomepolandverificationtask.model.Group;
import hubertadamus.axxiomepolandverificationtask.model.Relation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class AlgorithmSolver {

    public static Group solve(List<Relation> relationsList) {
        Stream<Relation> relationsStream = relationsList.stream();
        Optional<Group> result = relationsStream
                .map(relation -> getGroupFromRelationsList(relation, relationsList))
                .max(new GroupComparator());
        return result.orElse(new Group.GroupBuilder().build());
    }

    private static Group getGroupFromRelationsList(Relation startingGroup, List<Relation> relationsList) {
        Group.GroupBuilder groupBuilder = matchGroup(startingGroup, relationsList);
        assignAcquaintances(groupBuilder, relationsList);
        return groupBuilder.build();
    }

    private static Group.GroupBuilder matchGroup(Relation startingGroup, List<Relation> relationsList) {
        Group.GroupBuilder groupBuilder = new Group.GroupBuilder(startingGroup.getName1(), startingGroup.getName2());
        relationsList.stream()
                .distinct()
                .forEach(relation -> {
                    Group unfinishedGroup = groupBuilder.build();
                    if (unfinishedGroup.containsOne(relation)) {
                        String stranger = unfinishedGroup.getStranger(relation);
                        if (unfinishedGroup.equals(createCheckGroup(stranger, unfinishedGroup, relationsList))) {
                            groupBuilder.addName(stranger);
                        }
                    }
                });
        return groupBuilder;
    }

    private static Group createCheckGroup(String name, Group testedAgainstGroup, List<Relation> relationsList) {
        Group.GroupBuilder checkGroupBuilder = new Group.GroupBuilder();
        relationsList.stream()
                .filter(relation -> testedAgainstGroup.contains(relation.getOther(name)) && relation.hasName(name))
                .forEach(relation -> checkGroupBuilder.addName(relation.getOther(name)));
        return checkGroupBuilder.build();
    }

    private static void assignAcquaintances(Group.GroupBuilder groupBuilder, List<Relation> relationsList) {
        List<String> knownAcquaintances = new ArrayList<>();
        relationsList.stream()
                .filter(relation -> groupBuilder.build().containsOne(relation))
                .forEach(relation -> {
                    String stranger = groupBuilder.build().getStranger(relation);
                    if (!knownAcquaintances.contains(stranger)) {
                        groupBuilder.addAcquaintance();
                        knownAcquaintances.add(stranger);
                    }
                });
    }

    static class GroupComparator implements Comparator<Group> {
        @Override
        public int compare(Group group1, Group group2) {
            return group1.compareTo(group2);
        }
    }
}