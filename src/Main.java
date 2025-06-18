import Simulator.SavedSims;
import Simulator.Simulation;
import Simulator.Window;

import Math.*;
import javax.swing.*;

public class Main extends JFrame {

    public static final int WIDTH = 1200;
    public static final int HEIGHT = 1200;

    static Window window;

    public static void main(String[] args) throws InterruptedException {


        Main frame = new Main();
        SwingUtilities.invokeLater(() -> frame.createFrame(frame));
        synchronized (frame) {
            frame.wait();
        }

        while (true) {
            System.out.println("Sim started");
            window.startSim(Constants.FRAME_STEP);
            System.out.println("Sim stopped");
        }
    }


    public void createFrame(Object semaphore) {

        Simulation sim = new Simulation(SavedSims.earth());
        window = new Window(sim);

        setTitle("Space Sim");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(window);
        setVisible(true);

        synchronized (semaphore) {
            semaphore.notify();
        }
    }
}