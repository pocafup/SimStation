package simstation;

import java.util.*;
import mvc.*;

public class Simulation extends Model {

    private List<Agent> world;
    transient private Timer timer;
    private int clock;
    private long elapsedTimeMillis;
    private long startTime;

    public List<Agent> getWorld() {
        return world;
    }

    public void setAgent(int position, Agent agent){
        world.set(position, agent);
    }

    public void populate(){

    };

    public void start() {
        world = new ArrayList<>();
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
        suspendTimer();
    }

    public void resume() {
        for(Agent agent : world){
            agent.resume();
        }
        resumeTimer();
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
        if (timer == null)  timer = new Timer();
        elapsedTimeMillis = 0;
        startTime = System.currentTimeMillis();
        resumeTimer();
    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
            elapsedTimeMillis = 0;
            timer = null;
        }
    }
    private void suspendTimer() {
        if (timer != null) {
            this.elapsedTimeMillis += System.currentTimeMillis() - startTime;
            timer = null;
        }
    }
    private void resumeTimer() {
        if (timer == null)  timer = new Timer();
        startTime = System.currentTimeMillis();
        timer.scheduleAtFixedRate(new TimerTask () {
            @Override
            public void run() {
                clock = (int)getClock();
                changed();
            }
        }, 0, 20); // Notify subscribers every 20 milliseconds

    }
    public long getClock() {
        if (timer != null) {
            long elapsedTimeMillis = this.elapsedTimeMillis + System.currentTimeMillis() - startTime;
            return (elapsedTimeMillis + 999) / 1000;
        }
        return (elapsedTimeMillis+999)/1000;

    }


    public String getText() {
        return "";
    }
}
