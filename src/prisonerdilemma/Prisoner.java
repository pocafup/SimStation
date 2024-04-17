
package prisonerdilemma;

import mvc.Model;
import mvc.Utilities;
import simstation.Agent;
import simstation.Heading;


public class Prisoner extends Agent {

    private int fitness = 0;
    protected boolean cheated = false;

    private Strategy strategy;


    public Prisoner(Model model) {
        super(model);
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    @Override
    public void update() {
        heading = Heading.random();
        int steps = Utilities.rng.nextInt(10) + 1;
        move(steps);
        // get neighbors
        Prisoner neighbor = (Prisoner) SimStation.getNeighbors(this, 10);
        if (neighbor == null ) return;
        // perform prisoner dilemma
        boolean Icooperate = strategy.cooperate();
        boolean Ucooperate = neighbor.strategy.cooperate();
        if (Icooperate && Ucooperate) {
            fitness += 3;
            neighbor.fitness += 3;
            cheated = false;
            neighbor.cheated = false;
        } else if ( Icooperate ) {
            neighbor.fitness += 5;
            neighbor.cheated = false;
            cheated = true;
        } else if ( Ucooperate ) {
            fitness += 5;
            cheated = false;
            neighbor.cheated = true;
        } else {
            fitness += 1;
            neighbor.fitness += 1;
            cheated = true;
            neighbor.cheated = true;
        }

    }

    public int getFitness() {
        return fitness;
    }
}
