package mvc;


import javax.swing.*;

public class SimulationView extends JPanel implements Subscriber {
    protected Model model;

    public View() {

    }

    public View(Model model) {
        this.model = model;
        model.subscribe(this);
    }

    public void setModel(Model model) {
        this.model = model;
        model.subscribe(this);
    }
}