package mvc;

/*
Adapted from class code:
https://www.cs.sjsu.edu/faculty/pearce/modules/lectures/ood3/mvc/source/AppPanel.java
https://www.cs.sjsu.edu/~pearce/modules/lectures/ood4/guis/stoplight1.1/src/AppPanel.java
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppPanel extends JPanel implements ActionListener, Subscriber {

    public static final int FRAME_WIDTH = 800;
    public static final int FRAME_HEIGHT = 500;
    protected final View view;
    protected final AppFactory factory;
    protected final JPanel controlPanel;
    private final JFrame frame;
    protected Model model;


    public AppPanel(AppFactory factory) {
        super();
        this.factory = factory;
        model = factory.makeModel();
        view = factory.makeView(model);
        view.setBackground(Color.GRAY);
        controlPanel = new JPanel();
        setLayout(new GridLayout(1, 2));
        add(controlPanel);
        add(view);

        model.subscribe(this);

        frame = new SafeFrame();
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(createMenuBar());
        frame.setTitle(factory.getTitle());
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    public void display() {
        frame.setVisible(true);
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model newModel) {
        this.model.unsubscribe(this);
        this.model = newModel;
        this.model.subscribe(this);
        view.setModel(this.model);
        model.changed();
    }

    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        JMenu fileMenu = Utilities.makeMenu("File",
                new String[]{"New", "Save", "Save As", "Open", "Quit"}, this);
        result.add(fileMenu);

        JMenu editMenu = Utilities.makeMenu("Edit", factory.getEditCommands(), this);
        result.add(editMenu);

        JMenu helpMenu =
                Utilities.makeMenu("Help", new String[]{"About", "Help"}, this);
        result.add(helpMenu);

        return result;
    }


    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            String cmd = ae.getActionCommand();

            switch (cmd) {
                case "Save" -> Utilities.save(model, false);
                case "Save As" -> Utilities.save(model, true);
                case "Open" -> {
                    Model newModel = Utilities.open(model);
                    if (newModel != null) {
                        setModel(newModel);
                        model.setUnsavedChanges(false);
                    }
                }
                case "New" -> {
                    Utilities.saveChanges(model);
                    setModel(factory.makeModel());
                    model.setUnsavedChanges(false);
                }
                case "Quit" -> {
                    Utilities.saveChanges(model);
                    System.exit(0);
                }
                case "About" -> Utilities.inform(factory.about());
                case "Help" -> Utilities.inform(factory.getHelp());
                default -> {
                    Command command = factory.makeEditCommand(model, cmd, ae.getSource());
                    command.execute();
                }
            }
        } catch (Exception ex) {
            Utilities.error(ex);
        }

    }

    @Override
    public void update() {

    }

    @Override
    public void update(String msg, Object oldState, Object newState) {
        update();
    }
}