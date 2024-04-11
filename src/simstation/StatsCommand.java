package simstation;

import mvc.Command;
import mvc.Model;

public class StatsCommand extends Command {
    private Model model;
    public StatsCommand(Model m) {
        super(m);
        model = m;
    }

    @Override
    public void execute() {

    }
}
