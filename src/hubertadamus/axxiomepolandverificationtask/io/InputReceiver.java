package hubertadamus.axxiomepolandverificationtask.io;

import java.util.Scanner;

public final class InputReceiver {

    public static String[] receive() {
        final Scanner consoleScanner = new Scanner(System.in);
        int relationsNumber;
        if(consoleScanner.hasNextLine()) {
            relationsNumber = consoleScanner.nextInt();
        } else {
            return new String[] {};
        }
        consoleScanner.nextLine();
        String[] relations = new String[relationsNumber];
        for(int i = 0; i < relationsNumber; i++) {
            relations[i] = consoleScanner.nextLine();
        }
        return relations;
    }
}