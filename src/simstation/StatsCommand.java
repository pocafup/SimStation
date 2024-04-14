package simstation;

import mvc.Command;
import mvc.Model;
import plague.PlagueSimulation;

import javax.swing.*;

public class StatsCommand extends Command {
    public StatsCommand(Model m) {
        super(m);
    }

    @Override
    public void execute() {
        Simulation SimStation = (Simulation)model;
        if(SimStation.getWorld()==null){
            JOptionPane.showMessageDialog(null,
                    "Simulation haven't start", "Message", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String msg = "#agents = "+ SimStation.getWorld().size()+
                "\nclock = " + SimStation.getClock();
        if (model instanceof PlagueSimulation) {
            msg += "\n#infected = " + ((PlagueSimulation)model).getInfected();
        }
        JOptionPane.showMessageDialog(null,
                        msg, "Message", JOptionPane.INFORMATION_MESSAGE);
    }
}
