package simstation;

import mvc.Command;
import mvc.Model;

import javax.swing.*;

public class StatsCommand extends Command {
    public StatsCommand(Model m) {
        super(m);
    }

    @Override
    public void execute() {
        Simulation SimStation = (Simulation)model;
        JOptionPane.showMessageDialog(null,
                "#agents = "+ SimStation.getWorld().size()+
                        "\nclock = " + SimStation.getClock(),
                    "Message", JOptionPane.INFORMATION_MESSAGE);
    }
}
