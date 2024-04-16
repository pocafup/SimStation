package simstation;

import mvc.Command;
import mvc.Model;

public class StopCommand extends Command {

    public StopCommand(Model m) {
        super(m);
    }

    @Override
    public void execute() {
        Simulation SimStation = (Simulation)model;
        SimStation.stop();
    }
}
