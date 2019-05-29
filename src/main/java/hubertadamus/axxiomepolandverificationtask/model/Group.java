package hubertadamus.axxiomepolandverificationtask.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Group implements Comparable<Group> {
    private final List<String> names;
    private final int acquaintances;

    private Group(List<String> names, int acquaintances) {
        this.names = names;
        this.acquaintances = acquaintances;
    }

    public String getName(int i) {
        return names.get(i);
    }

    public int getSize() {
        return names.size();
    }

    public boolean contains(String name) {
        return names.contains(name);
    }

    public boolean containsOne(Relation relation) {
        return (names.contains(relation.getName1()) && !names.contains(relation.getName2()))
                || (names.contains(relation.getName2()) && !names.contains(relation.getName1()));
    }

    public int getAcquaintances() {
        return acquaintances;
    }

    public String getStranger(Relation relation) {
        for(String name : names) {
            if(name.equals(relation.getName1())) {
                return relation.getName2();
            } else if(name.equals(relation.getName2())) {
                return relation.getName1();
            }
        }
        return null;
    }

    @Override
    public int compareTo(Group otherGroup) {
        if (names.size() != otherGroup.names.size()) {
            return Integer.compare(names.size(), otherGroup.names.size());
        } else {
            if (acquaintances != otherGroup.acquaintances) {
                return Integer.compare(acquaintances, otherGroup.acquaintances);
            } else {
                for (int i = 0; i < names.size(); i++) {
                    if (names.get(i).equals(otherGroup.names.get(i))) {
                        continue;
                    }
                    return otherGroup.names.get(i).compareTo(names.get(i));
                }
            }
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Group)) {
            return false;
        }
        Group otherGroup = (Group)o;
        return names.equals(otherGroup.names);
    }

    public static class GroupBuilder {
        private final List<String> names;
        private int acquaintances;

        public GroupBuilder(String... names) {
            this.names = new ArrayList<>();
            this.names.addAll(Arrays.asList(names));
            Collections.sort(this.names);
            acquaintances = 0;
        }

        public GroupBuilder addName(String name) {
            names.add(name);
            Collections.sort(names);
            return this;
        }

        public GroupBuilder addAcquaintance() {
            acquaintances++;
            return this;
        }

        public Group build() {
            return new Group(names, acquaintances);
        }
    }
}