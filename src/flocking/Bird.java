package flocking;

import mvc.Utilities;
import simstation.*;

import java.util.List;

public class Bird extends Agent {
    private int speed;

    public Bird(Simulation model) {
        super(model);
        this.heading = Heading.random();
        this.speed = Utilities.rng.nextInt(5) + 1; // Speed is randomly set between 1 and 5
    }

    @Override
    public void update() {
        int radius = 10; // Increase this radius to consider birds as neighbors at a greater distance
        Bird neighbor = findNeighbor(radius);
        if (neighbor != null) {
            this.speed = neighbor.speed;
            this.heading = neighbor.heading;
        }
        // Move in the current heading with the number of steps equal to the speed
        move(speed);
    }

    private Bird findNeighbor(int radius) {
        List<Agent> agents = SimStation.getWorld();
        for (Agent a : agents) {
            if (a instanceof Bird && a != this && SimStation.distance(a,this) <= radius) {
                return (Bird) a;
            }
        }
        return null;
    }
    public int getSpeed() {
        return speed;
    }

}