package simstation;

import mvc.Command;
import mvc.Model;

public class SuspendCommand extends Command {
    private Model model;
    public SuspendCommand(Model m) {
        super(m);
        model = m;
    }

    @Override
    public void execute() {
        Simulation SimStation = (Simulation)model;
        SimStation.suspend();
    }
}
