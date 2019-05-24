package hubertadamus.axxiomepolandverificationtask;

import hubertadamus.axxiomepolandverificationtask.io.InputReceiver;
import hubertadamus.axxiomepolandverificationtask.io.InputTranslator;
import hubertadamus.axxiomepolandverificationtask.io.OutputPrinter;
import hubertadamus.axxiomepolandverificationtask.logic.AlgorithmSolver;

public class Main {
    public static void main(String[] args) {
        OutputPrinter.printResults(AlgorithmSolver.solve(InputTranslator.translate(InputReceiver.receive())));
    }
}