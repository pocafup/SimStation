package simstation;

import mvc.AppFactory;
import mvc.AppPanel;

import javax.swing.*;
import java.awt.*;

public class SimulationPanel extends AppPanel {

    private JButton start;
    private JButton suspend;
    private JButton resume;
    private JButton stop;
    private JButton stats;
    public SimulationPanel(AppFactory factory) {
        super(factory);

        ControlPanel.setLayout(new GridLayout(5,2,10, 25));
        start = new JButton("Start");
        start.addActionListener(this);
        ControlPanel.add(start);
        suspend = new JButton("Suspend");
        suspend.addActionListener(this);
        ControlPanel.add(suspend);
        resume = new JButton("Resume");
        resume.addActionListener(this);
        ControlPanel.add(resume);
        stop = new JButton("Stop");
        stop.addActionListener(this);
        ControlPanel.add(stop);
        stats = new JButton("Stats");
        stats.addActionListener(this);
        ControlPanel.add(stats);
        this.display();
    }
}