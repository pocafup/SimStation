
package prisonerdilemma;

import mvc.Model;
import plague.PlagueSimulation;
import simstation.SimulationFactory;

public class PrisonFactory extends SimulationFactory {

    public Model makeModel() { return new Prison(); }

    @Override
    public String about() {
        return "Play out the Prisoner's Dilemma";
    }

    public String getTitle() { return "Prisoner Dilemma";}
}
