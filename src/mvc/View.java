package mvc;


import javax.swing.*;

public class View extends JPanel implements Subscriber {
    protected Model model;

    public View() {
        // use setModel to init model
    }

    public View(Model model) {
        this.model = model;
        model.subscribe(this);
    }

    public void setModel(Model model) {
        this.model = model;
        model.subscribe(this);
    }

    @Override
    public void update() {
        update("?", null, null);
    }

    @Override
    public void update(String msg, Object oldState, Object newState) {
        repaint();
    }
}