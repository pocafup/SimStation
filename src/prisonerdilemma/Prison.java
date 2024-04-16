package prisonerdilemma;

import mvc.AppPanel;


import simstation.Agent;
import simstation.Simulation;
import simstation.SimulationPanel;

import java.awt.*;

public class Prison extends Simulation {

    @Override
    public void populate() {
        Prisoner prisoner;
        for (int i = 0; i < 10; i++) {
            prisoner = new Prisoner(this);
            prisoner.setStrategy(new Cheat(prisoner));
            addAgent(prisoner);
            prisoner = new Prisoner(this);
            prisoner.setStrategy(new Cooperate(prisoner));
            addAgent(prisoner);
            prisoner = new Prisoner(this);
            prisoner.setStrategy(new RandomlyCooperate(prisoner));
            addAgent(prisoner);
            prisoner = new Prisoner(this);
            prisoner.setStrategy(new Tit4Tat(prisoner));
            addAgent(prisoner);
        }
    }


    @Override
    public String getText() {
        int aveCheat = 0;
        int aveCooperate = 0;
        int aveRandom = 0;
        int aveTit4Tat = 0;
        for (Agent a : getWorld()){
            Prisoner v = (Prisoner) a;
            switch(v.getStrategy().getClass().getSimpleName()) {
                case "Cheat":
                    aveCheat += v.getFitness();
                    break;
                case "Cooperate":
                    aveCooperate += v.getFitness();
                    break;
                case "Tit4Tat":
                    aveTit4Tat += v.getFitness();
                    break;
                default:
                    aveRandom += v.getFitness();
                    break;
            }

        }
        int size = getWorld().size() / 4;
        return String.format("""
                        \nAverage Cheated Fitness: %d\s
                        Average Random Fitness: %d\s
                        Average Cooperation Fitness: %d\s
                        Average Tit4Tat Fitness: %d\s
                        """,
                aveCheat / size, aveRandom / size, aveCooperate / size, aveTit4Tat / size);
    }

    public static void main(String[] args) {
        AppPanel panel = new SimulationPanel(new PrisonFactory());
        panel.display();
    }
}
