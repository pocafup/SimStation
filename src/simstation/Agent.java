package simstation;

// Assuming Heading is an enum already defined somewhere
import mvc.Model;
import mvc.Utilities;

import java.io.Serializable;

public abstract class Agent implements Runnable, Serializable {

    private String name;
    protected Heading heading;
    public int xc, yc; // Presumably for coordinates, but not shown in your diagram
    private boolean suspended = false;
    private boolean stopped = false;
    private transient Thread myThread; // Transient because Threads are not serializable

    protected Agent neighbor = null;

    private static int MAX_XC = 400;
    private static int MAX_YC = 500;


    public Simulation SimStation;

    public Agent(Model model) {
        this.heading = Heading.random(); // Assign a random heading
        this.xc = Math.abs(Utilities.rng.nextInt() % MAX_XC); // Ensure xc is positive
        this.yc = Math.abs(Utilities.rng.nextInt() % MAX_YC); // Ensure yc is positive
        SimStation = (Simulation) model;
        System.out.println("xc: " + xc + "    yc: " + yc);
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
        if(neighbor!=null) heading = neighbor.heading;
        switch (heading) {
            case NORTH:
                yc -= steps; // Assuming the upper part of the screen is "North", subtract from y to move up
                yc = yc < 0 ? MAX_YC + yc : yc;
                break;
            case SOUTH:
                yc += steps; // Add to y to move down
                yc = yc > 400 ? yc - MAX_YC : yc;
                break;
            case EAST:
                xc += steps; // Add to x to move right
                xc = xc > 400 ? xc - MAX_XC : xc;
                break;
            case WEST:
                xc -= steps; // Subtract from x to move left
                xc = xc < 0 ? MAX_XC + xc : xc;
                break;
        }
    }

    // Implement other methods such as move(steps: int) and getters for heading, xc, yc as necessary.
}