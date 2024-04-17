package plague;

import mvc.Model;
import mvc.Utilities;
import simstation.Agent;
import simstation.Heading;
import simstation.Simulation;

import java.awt.*;
import java.util.ArrayList;


class Plague extends Agent {
    public Color status;
    public Plague(Model model) {
        super(model);
        status = (Utilities.rng.nextInt(100) > ((PlagueSimulation)model).VIRULENCE)
                ? Color.GREEN : Color.RED;
        heading = Heading.random();
    }
    @Override
    public Color getColor() {
        return status;
    }
    public ArrayList<Plague> getAllNeighbors(int radius){
        PlagueSimulation plague = (PlagueSimulation) SimStation;
        ArrayList<Plague> neighbors = new ArrayList<Plague>();
        for(Agent b : plague.getWorld()){
            Plague a = (Plague) b;
            if(a != this && plague.distance(this,a) < radius){
                neighbors.add(a);
            }
        }
        return neighbors;
    }
    public void update() {
        heading = Heading.random();
        PlagueSimulation plague = (PlagueSimulation) SimStation;
        ArrayList<Plague> neighbors = getAllNeighbors(10);
        for(Plague neighbor : neighbors){
            if(neighbor.status == Color.RED && Utilities.rng.nextInt(100) > plague.RESISTANCE){
                status = Color.RED;
            }
        }
        int steps = Utilities.rng.nextInt(10) + 1;
        move(steps);
    }

}