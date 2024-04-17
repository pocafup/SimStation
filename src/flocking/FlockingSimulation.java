package flocking;

import mvc.AppPanel;
import simstation.Agent;
import simstation.SimulationPanel;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlockingSimulation extends simstation.Simulation {

    public void populate() {
        for (int i = 0; i < 30; i++) {
            addAgent(new Bird(this));
        }
    }

    public static void main(String[] args) {
        AppPanel panel = new SimulationPanel(new FlockingFactory());
        panel.display();
    }

    @Override
    public String getText() {
        return "\nBirds at each speed:\n" + getSpeed();
    }

    public String getSpeed() {
        Map<Integer, Integer> speedCount = new HashMap<>();
        for (Agent agent : getWorld()) {
            int speed = ((Bird) agent).getSpeed();
            speedCount.put(speed, speedCount.getOrDefault(speed, 0) + 1);
        }

        StringBuilder message = new StringBuilder();
        for (Map.Entry<Integer, Integer> entry : speedCount.entrySet()) {
            message.append("# birds @ speed ").append(entry.getKey()).append(" = ").append(entry.getValue()).append("\n");
        }

        return message.toString();
    }
}