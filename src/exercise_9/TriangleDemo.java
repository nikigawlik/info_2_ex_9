package exercise_9;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;

/*
 * Code copied from https://docs.oracle.com/javase/tutorial/uiswing/painting/step1.html
 */

public class TriangleDemo {
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    
    private static void createAndShowGUI() {
        System.out.println("Created GUI on EDT? "+
                SwingUtilities.isEventDispatchThread());
        JFrame f = new JFrame("Sierpinski Triangle");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f.add(new MyPanel());
        f.pack();
        
        f.setVisible(true);
    }
}