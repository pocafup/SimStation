package plague;

import mvc.Model;
import simstation.Agent;
import simstation.Simulation;
import simstation.SimulationView;

import java.awt.*;
import java.util.List;

public class PlagueView extends SimulationView {

    public PlagueView(Model model) {
        super(model);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Color temp = g.getColor();

        List<Agent> agents = ((Simulation) model).getWorld();

        if (agents == null) return;

        for (Agent agent: agents) {
            g.setColor(((Plague)agent).getColor());
            g.fillRect(agent.xc, agent.yc, 5, 5);
        }

        g.setColor(temp);
    }
}
