package plague;

import mvc.Model;
import simstation.SimulationFactory;

class PlagueFactory extends SimulationFactory {
    public Model makeModel() { return new PlagueSimulation(); }

    @Override
    public String getHelp() {
        return "Start Command: Start the simulation\n" +
                "Suspend Command: Pause the simulation\n" +
                "Resume Command: Resume the simulation\n" +
                "Stop Command: Stop the simulation\n" +
                "Stats Command: Display the statistics of the simulation\n";
    }

    @Override
    public String about() {
        return "Spread the plague";
    }

    public String getTitle() { return "Plague";}
}