package plague;

import mvc.AppPanel;
import simstation.Agent;
import simstation.Simulation;
import simstation.SimulationPanel;

import java.awt.*;

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

    public String getInfected() {
        int infected = 0;
        for (Agent a : getWorld()){
            Plague v = (Plague) a;
            if (v.status == Color.RED){
                infected++;
            }
        }
        double result = infected*100.0/getWorld().size();
        String formattedResult = String.format("%.1f", result);
        return formattedResult + "%";
    }
}
