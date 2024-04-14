package plague;

import mvc.Model;
import simstation.SimulationFactory;

class PlagueFactory extends SimulationFactory {
    public Model makeModel() { return new PlagueSimulation(); }

    @Override
    public String getHelp() {
        return null;
    }

    @Override
    public String about() {
        return null;
    }

    public String getTitle() { return "Plague";}
}