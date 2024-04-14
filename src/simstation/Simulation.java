package simstation;

import java.util.*;
import mvc.*;

public abstract class Simulation extends Model {

    private List<Agent> world = new ArrayList<>();
    private Timer timer;

    private long startTime;

    public List<Agent> getWorld() {
        return world;
    }

    public void setAgent(int position, Agent agent){
        world.set(position, agent);
    }

    public abstract void populate();

    public void start() {
        populate();
        for(Agent agent : world){
            agent.start();
        }
        startTimer();
    }

    public void suspend() {
        for(Agent agent : world){
            agent.suspend();
        }
        stopTimer();
    }

    public void resume() {
        for(Agent agent : world){
            agent.resume();
        }
        startTimer();
    }

    public void stop() {
        for(Agent agent : world){
            agent.stop();
        }
        stopTimer();
    }

    public double distance(Agent a, Agent b){
        return Math.sqrt(Math.pow(a.xc - b.xc, 2) + Math.pow(a.yc - b.yc, 2));
    }

    public Agent getNeighbors(Agent agent, int radius){
        for (Agent a : world){
            if (a != agent && distance(a, agent) <= radius){
                return a;
            }
        }
        return null;
    }

    protected void addAgent(Agent agent) {
        world.add(agent);
    }

    private void startTimer() {
        if (timer == null) {
            startTime = System.currentTimeMillis();
            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    changed();
                }
            }, 0, 20); // Notify subscribers every 20 milliseconds
        }
    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    public long getClock() {
        if (timer != null) {
            long elapsedTimeMillis = System.currentTimeMillis() - startTime;
            return (elapsedTimeMillis + 999) / 1000;
        } else {
            return 0;
        }
    }

}
