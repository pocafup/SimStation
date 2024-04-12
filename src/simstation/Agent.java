package simstation;

// Assuming Heading is an enum already defined somewhere
import mvc.Utilities;

import java.io.Serializable;

public abstract class Agent implements Runnable, Serializable {

    private String name;
    protected Heading heading;
    private int xc, yc; // Presumably for coordinates, but not shown in your diagram
    private boolean suspended = false;
    private boolean stopped = false;
    private transient Thread myThread; // Transient because Threads are not serializable

    public Agent() {
        this.heading = Heading.random(); // Assign a random heading
        this.xc = Utilities.rng.nextInt(); // Example usage, needs proper range
        this.yc = Utilities.rng.nextInt(); // Example usage, needs proper range
    }



    public String getName() {
        return name;
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
        while (!stopped) {
            if (!suspended) {
                update();
            }
            try {
                Thread.sleep(20); // Sleep time can be adjusted or made dynamic
            } catch (InterruptedException e) {
                // May need to check conditions again if interrupted
            }
        }
    }

    public abstract void update();

    public synchronized String toString() {
        return name + " [" + heading + "] (" +
                ((stopped) ? "stopped" : (suspended) ? "suspended" : "running") + ")";
    }

    protected void move(int steps) {

    }

    // Implement other methods such as move(steps: int) and getters for heading, xc, yc as necessary.
}
