
package simstation;

import mvc.Command;
import mvc.Model;

public class StartCommand extends Command {

    public StartCommand(Model m) {
        super(m);
    }

    @Override
    public void execute() {
        Simulation SimStation = (Simulation)model;
        SimStation.start();
    }
}

