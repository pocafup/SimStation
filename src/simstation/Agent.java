package simstation;

// Assuming Heading is an enum already defined somewhere
import mvc.Model;
import mvc.Utilities;

import java.awt.*;
import java.io.Serializable;

public abstract class Agent implements Runnable, Serializable {

    private String name;
    protected Heading heading;
    public int xc, yc; // Presumably for coordinates, but not shown in your diagram
    private boolean suspended = false;
    private boolean stopped = false;
    private transient Thread myThread; // Transient because Threads are not serializable
    public Simulation SimStation;
    public final int xOffset = 395;
    public final int yOffset = 440;

    public Agent(Model model) {
        this.heading = Heading.random(); // Assign a random heading
        this.xc = Utilities.rng.nextInt()%xOffset; // Example usage, needs proper range
        this.yc = Utilities.rng.nextInt()%yOffset; // Example usage, needs proper range
        SimStation = (Simulation) model;
    }



    public String getName() {
        return name;
    }
    private synchronized void checkSuspended() {
        try {
            while (!stopped && suspended) {
                wait();
            }
        } catch (InterruptedException e) {
            // Handle interruption
        }
    }
    public synchronized void start() {
        stopped = false;
        suspended = false;
        myThread = new Thread(this);
        myThread.start();
    }

    public synchronized void suspend() {
        suspended = true;
    }

    public synchronized void resume() {
        if (suspended) {
            suspended = false;
            notify();
        }
    }

    public synchronized void stop() {
        stopped = true;
        if (myThread != null) {
            myThread.interrupt();
        }
    }

    public synchronized boolean isSuspended() {
        return suspended;
    }

    public synchronized boolean isStopped() {
        return stopped;
    }

    public synchronized void join() throws InterruptedException {
        if (myThread != null) {
            myThread.join();
        }
    }

    @Override
    public void run() {
        myThread = Thread.currentThread();
        while (!isStopped()) {
            if (!isSuspended()) {
                update();
            }
            try {
                Thread.sleep(20); // Sleep time can be adjusted or made dynamic
                checkSuspended();
            } catch (InterruptedException e) {
                // Check if the thread was interrupted to stop
            }
        }
    }

    public abstract void update();

    public synchronized String toString() {
        return name + " [" + heading + "] (" +
                ((stopped) ? "stopped" : (suspended) ? "suspended" : "running") + ")";
    }

    public void move(int steps) {
        Agent Neighbor = SimStation.getNeighbors(this,10);
        if(Neighbor!=null) heading = Neighbor.heading;
        switch (heading) {
            case NORTH:
                yc = (yc-steps+yOffset)%yOffset; // Assuming the upper part of the screen is "North", subtract from y to move up
                break;
            case SOUTH:
                yc = (yc+steps+yOffset)%yOffset; // Add to y to move down
                break;
            case EAST:
                xc = (xc+steps+xOffset)%xOffset; // Add to x to move right
                break;
            case WEST:
                xc = (xc-steps+xOffset)%xOffset; // Subtract from x to move left
                break;
        }
    }

    public Color getColor() {
        return Color.WHITE; // Example usage, needs to be implemented
    }

    // Implement other methods such as move(steps: int) and getters for heading, xc, yc as necessary.
}
