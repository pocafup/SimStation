package simstation;

import java.util.*;
import mvc.*;

public class Simulation extends Model {

    transient private Timer timer; // timers aren't serializable (wasn't sure what this is for but il leave)
    private int clock;
    //not static
    protected List<Agent> world;

    public List<Agent> getWorld() {
        return world;
    }
    public void setAgent(int position, Agent agent){
        world.set(position,agent);
    }
    public void populate(){

    }
    // I don't think we need these
    // I think populate will deal with time. 
    // Since populate will be overwritten in the individual projects I don't really have to deal with time rn
//    private void startTimer() {
//        timer = new Timer();
//        timer.scheduleAtFixedRate(new ClockUpdater(), 1000, 1000);
//    }
//
//    private void stopTimer() {
//        timer.cancel();
//        timer.purge();
//    }
//
    public void start() {
        for(Agent v : world){
            v.start();
        }
        populate();
    }
    public void suspend() {
        for(Agent v : world){
            v.suspend();
        }
    }
    public void resume() {
        for(Agent v : world){
            v.resume();
        }
    }
    public void stop() {
        for(Agent v : world){
            v.stop();
        }
    }

    public double distance(Agent a, Agent b){
        return Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2));
    }
    public Agent getNeighbors(Agent agent, int radius){
        for (Agent a : world){
            if (a != agent && distance(a,agent)<=radius){
                return a;
            }
        }
        return null;
    }
    protected void addAgent(Agent agent) {
        world.add(agent);
    }

    // etc.

}
