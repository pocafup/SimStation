package randomwalk;

import mvc.*;
import simstation.*;
import java.awt.*;
import java.util.Iterator;

class RandomWalkFactory extends SimulationFactory {
    public Model makeModel() { return new RandomWalkSimulation(); }

    @Override
    public String getHelp() {
        return null;
    }

    @Override
    public String about() {
        return null;
    }

    public String getTitle() { return "Random Walks";}
}