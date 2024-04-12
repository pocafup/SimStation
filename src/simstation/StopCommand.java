package simstation;

import mvc.Command;
import mvc.Model;

public class StopCommand extends Command {

    private Model model;

    public StopCommand(Model m) {
        super(m);
        model = m;
    }

    @Override
    public void execute() {
        if (model instanceof Simulation) {
            ((Simulation) model).stop();
        }
    }
}