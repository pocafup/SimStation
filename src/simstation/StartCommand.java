package simstation;

import mvc.Command;
import mvc.Model;

public class StartCommand extends Command {

    private Model model;
    public StartCommand(Model m) {
        super(m);
        model = m;
    }

    @Override
    public void execute() {

    }
}
