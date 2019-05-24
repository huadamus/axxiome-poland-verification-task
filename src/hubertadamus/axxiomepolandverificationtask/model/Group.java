package hubertadamus.axxiomepolandverificationtask.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Group implements Comparable<Group> {
    private final List<String> names;
    private int acquaintances;

    public Group() {
        names = new ArrayList<>();
        acquaintances = 0;
    }

    public void add(String name) {
        names.add(name);
        Collections.sort(names);
    }

    public final String getName(int i) {
        return names.get(i);
    }

    public final int getSize() {
        return names.size();
    }

    public boolean contains(String name) {
        return names.contains(name);
    }

    public boolean containsOne(Relation relation) {
        return (names.contains(relation.getName1()) && !names.contains(relation.getName2()))
                || (names.contains(relation.getName2()) && !names.contains(relation.getName1()));
    }

    public void addAcquaintance() {
        acquaintances++;
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
        if(names.size() > otherGroup.names.size()) {
            return 1;
        } else if(names.size() < otherGroup.names.size()) {
            return -1;
        } else {
            for(int i = 0; i < names.size(); i++) {
                if(names.get(i).equals(otherGroup.names.get(i))) {
                    continue;
                }
                return names.get(i).compareTo(otherGroup.names.get(i));
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
}
