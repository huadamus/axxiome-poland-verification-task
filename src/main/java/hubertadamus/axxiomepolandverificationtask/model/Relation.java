package hubertadamus.axxiomepolandverificationtask.model;

public class Relation {
    private final String name1, name2;

    public Relation(String name1, String name2) {
        this.name1 = name1;
        this.name2 = name2;
    }

    public String getName1() {
        return name1;
    }

    public String getName2() {
        return name2;
    }

    public boolean hasName(String name) {
        return name.equals(name1) || name.equals(name2);
    }

    public String getOther(String name) {
        if(name.equals(name1)) {
            return name2;
        }
        return name1;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Relation)) {
            return false;
        }
        Relation otherRelation = (Relation)o;
        return name1.equals(otherRelation.name1) && name2.equals(otherRelation.name2);
    }
}