package plague;

import mvc.Model;
import mvc.View;
import simstation.SimulationFactory;

class PlagueFactory extends SimulationFactory {
    public Model makeModel() { return new PlagueSimulation(); }
    @Override
    public View makeView(Model model) { return new PlagueView((PlagueSimulation)model); }
    @Override
    public String about() {
        return "Spread the plague";
    }

    public String getTitle() { return "Plague";}
}