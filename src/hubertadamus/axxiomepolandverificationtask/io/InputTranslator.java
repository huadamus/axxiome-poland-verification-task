package hubertadamus.axxiomepolandverificationtask.io;

import hubertadamus.axxiomepolandverificationtask.model.Relation;

import java.util.ArrayList;
import java.util.List;

public class InputTranslator {

    public static List<Relation> translate(String[] input) {
        List<Relation> relationsList = new ArrayList<>();
        for (String s : input) {
            String[] splitRawRelation = s.split(" ");
            relationsList.add(new Relation(splitRawRelation[0], splitRawRelation[1]));
        }
        return relationsList;
    }
}