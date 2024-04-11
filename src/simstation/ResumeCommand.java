package simstation;

import mvc.Command;
import mvc.Model;

public class ResumeCommand extends Command {
    private Model model;
    public ResumeCommand(Model m) {
        super(m);
        model = m;
    }

    @Override
    public void execute() {

    }
}
