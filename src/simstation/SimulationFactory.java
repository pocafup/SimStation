package simstation;

import mvc.*;

public abstract class SimulationFactory implements AppFactory {
    @Override
    public abstract Model makeModel();

    @Override
    public View makeView(Model m) {
        return new SimulationView((Simulation) m);
    }

    @Override
    public String[] getEditCommands() {
        return new String[] { "Start", "Suspend", "Resume", "Stop", "Stats" };
    }

    @Override
    public Command makeEditCommand(Model model, String type, Object source) {
        return switch (type) {
            case "Start" -> new StartCommand(model);
            case "Suspend" -> new SuspendCommand(model);
            case "Resume" -> new ResumeCommand(model);
            case "Stop" -> new StopCommand(model);
            case "Stats" -> new StatsCommand(model);
            default -> null;
        };
    }

    @Override
    public String getHelp() {
        return "Start Command: Start the simulation\n" +
                "Suspend Command: Pause the simulation\n" +
                "Resume Command: Resume the simulation\n" +
                "Stop Command: Stop the simulation\n" +
                "Stats Command: Display the statistics of the simulation\n";
    }
}