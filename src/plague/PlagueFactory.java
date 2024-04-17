package plague;

import mvc.Model;
import simstation.SimulationFactory;

class PlagueFactory extends SimulationFactory {
    public Model makeModel() { return new PlagueSimulation(); }

    @Override
    public String about() {
        return "Spread the plague";
    }

    public String getTitle() { return "Plague";}
}