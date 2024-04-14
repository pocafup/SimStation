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

        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS)); // Set layout to vertical BoxLayout

        start = new JButton("Start");
        start.setAlignmentX(Component.CENTER_ALIGNMENT); // Align button horizontally to center
        start.addActionListener(this);
        controlPanel.add(start);

        controlPanel.add(Box.createVerticalGlue()); // Add glue to evenly space buttons

        suspend = new JButton("Suspend");
        suspend.setAlignmentX(Component.CENTER_ALIGNMENT); // Align button horizontally to center
        suspend.addActionListener(this);
        controlPanel.add(suspend);

        controlPanel.add(Box.createVerticalGlue()); // Add glue to evenly space buttons

        resume = new JButton("Resume");
        resume.setAlignmentX(Component.CENTER_ALIGNMENT); // Align button horizontally to center
        resume.addActionListener(this);
        controlPanel.add(resume);

        controlPanel.add(Box.createVerticalGlue()); // Add glue to evenly space buttons

        stop = new JButton("Stop");
        stop.setAlignmentX(Component.CENTER_ALIGNMENT); // Align button horizontally to center
        stop.addActionListener(this);
        controlPanel.add(stop);

        controlPanel.add(Box.createVerticalGlue()); // Add glue to evenly space buttons

        stats = new JButton("Stats");
        stats.setAlignmentX(Component.CENTER_ALIGNMENT); // Align button horizontally to center
        stats.addActionListener(this);
        controlPanel.add(stats);

        this.display();
    }
}
