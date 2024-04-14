package plague;

import mvc.AppPanel;
import simstation.Simulation;
import simstation.SimulationPanel;

public class PlagueSimulation extends Simulation {
    public static int VIRULENCE = 10; // % chance of infection
    public static int RESISTANCE = 60; // % chance of resisting infection
    public void populate() {
        for(int i = 0; i < 50; i++)
            addAgent(new Plague(this));
    }

    public static void main(String[] args) {
        AppPanel panel = new SimulationPanel(new PlagueFactory());
        panel.display();
    }

}
