package flocking;

import mvc.Model;
import simstation.SimulationFactory;

class FlockingFactory extends SimulationFactory {
    public Model makeModel() { return new FlockingSimulation(); }

    @Override
    public String about() {
        return "Birds flying at different speeds and flocking together when nearby.";
    }

    public String getTitle() { return "Flocking";}
}