package simstation;

import mvc.Command;
import mvc.Model;
import javax.swing.*;

public class StatsCommand extends Command {

    public StatsCommand(Model model) {
        super(model);
    }

    @Override
    public void execute() {
        if (model instanceof Simulation) { // No need to cast to a specific simulation type like BirdSimulation
            Simulation sim = (Simulation) model;
            // Assuming your Simulation class has a getAgentCount() method
            int agentCount = sim.getAgentCount();
            int simulationClock = sim.getClock();

            // Prepare the stats message
            String stats = "#agents = " + agentCount + "\nclock = " + simulationClock;

            // Display the message
            JOptionPane.showMessageDialog(null, stats, "Message", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Handle the case where model is not an instance of Simulation
            JOptionPane.showMessageDialog(null, "The model is not a Simulation instance.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
