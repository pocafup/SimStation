package randomwalk;

import mvc.*;
import simstation.*;
import java.awt.*;
import java.util.Iterator;

class RandomWalkFactory extends SimulationFactory {
    public Model makeModel() { return new RandomWalkSimulation(); }



    @Override
    public String about() {
        return "Random Walk Simulation";
    }

    public String getTitle() { return "Random Walks";}
}