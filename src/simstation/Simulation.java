package simstation;

import java.util.*;
import mvc.*;

public class Simulation extends Model {

    private transient Timer timer; // Timers aren't serializable
    private int clock;
    private List<Agent> agents; // To hold all the agents in the simulation
    private boolean isRunning; // To track the state of the simulation

    public Simulation() {
        clock = 0;
        agents = Collections.synchronizedList(new ArrayList<>());
        isRunning = false;
    }

    public void start() {
        if (!isRunning) {
            isRunning = true;
            populate(); // Initialize the agents for the simulation
            for (Agent a : agents) {
                a.start(); // Start each agent's thread
            }
            startTimer(); // Start the simulation clock
        }
    }

    public void suspend() {
        for (Agent a : agents) {
            a.suspend(); // Suspend each agent
        }
        stopTimer(); // Stop the simulation clock
    }

    public void resume() {
        if (!isRunning) {
            isRunning = true;
            for (Agent a : agents) {
                a.resume(); // Resume each agent
            }
            startTimer(); // Start the simulation clock
        }
    }

    public void stop() {
        isRunning = false;
        for (Agent a : agents) {
            a.stop(); // Stop each agent's thread
        }
        stopTimer(); // Stop the simulation clock
    }

    protected void addAgent(Agent agent) {
        agents.add(agent);
        if (isRunning) {
            agent.start(); // If the simulation is running, start the agent immediately
        }
    }

    protected Agent getNeighbor(Agent asker, double radius) {
        // Placeholder for actual implementation
        // This method would need to check for agents within a certain radius of asker
        return null; // Should be replaced with actual neighbor finding logic
    }

    public int getAgentCount() {
        return agents.size();
    }


    protected void populate() {
        // To be overridden in subclasses
    }

    public int getClock() {
        return clock;
    }

    private void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new ClockUpdater(), 1000, 1000);
    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
            timer = null;
        }
    }

    private class ClockUpdater extends TimerTask {
        public void run() {
            clock++;
        }
    }

    // etc. Implement other methods as necessary for your simulation

}
