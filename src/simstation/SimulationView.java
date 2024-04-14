package simstation;

import mvc.*;

import java.awt.*;
import java.util.List;


public class SimulationView extends View {


    public SimulationView(Model model) {
        super(model);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Color temp = g.getColor();

        List<Agent> agents = ((Simulation) model).getWorld();

        if (agents == null) return;

        g.setColor(Color.WHITE);

        for (Agent agent: agents) {
            // g.setColor(agent.getColor());   // this method is not yet implemented (may be important for plague)
            g.fillRect(agent.xc, agent.yc, 5, 5);
        }

        g.setColor(temp);
    }
}