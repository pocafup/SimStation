package mvc;
/*
Taken from class website: https://www.cs.sjsu.edu/~pearce/modules/lectures/ood4/mvc/src/SafeFrame.java
 */

import javax.swing.*;
import java.awt.event.WindowEvent;

public class SafeFrame extends JFrame {

    public SafeFrame() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    protected void processWindowEvent(WindowEvent ev) {
        super.processWindowEvent(ev);
        if (ev.getID() == WindowEvent.WINDOW_CLOSING) {
            if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
                System.exit(0);
            }
        }
    }
}