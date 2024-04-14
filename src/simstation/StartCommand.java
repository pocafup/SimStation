package simstation;

import mvc.Command;
import mvc.Model;

public class StartCommand extends Command {


    public StartCommand(Model m) {
        super(m);
    }

    @Override
    public void execute() {
        if (model instanceof Simulation) ((Simulation) model).start();
    }
}
