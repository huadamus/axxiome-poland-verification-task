package hubertadamus.axxiomepolandverificationtask.io;

import hubertadamus.axxiomepolandverificationtask.model.Group;

public class OutputPrinter {

    public static void printResults(Group group) {
        System.out.println(group.getSize());
        for(int i = 0; i < group.getSize(); i++) {
            if(i != 0) {
                System.out.print(" ");
            }
            System.out.print(group.getName(i));
        }
    }
}
