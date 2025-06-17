import Bodies.Body;
import Bodies.Planet;
import Simulator.Simulation;
import Simulator.Window;

import Math.*;
import javax.swing.*;
import java.util.ArrayList;

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
        ArrayList<Body> bodies = new ArrayList<>();

        bodies.add(new Planet(
                "Earth",
                597.22,
                new Vector(0, 0),
                new Vector(0,0),
                new Vector(0,0),
                0.25,
                0,
                637.8 * Constants.PLANET_SCALE,
                635.6 * Constants.PLANET_SCALE
        ));
        bodies.add(new Planet(
                "Luna",
                7.35,
                new Vector(0, 3875.89),
                /*new Math.Vector(0,0),*/ Constants.velocityForOrbit(new Vector(0, 3875.89), bodies.getFirst()),
                new Vector(0,0),
                0.009150135422,
                0,
                347.2 * Constants.PLANET_SCALE,
                347.6 * Constants.PLANET_SCALE
        ));


        /*
        bodies.add(new Bodies.Planet(
                1,
                new Math.Vector(0, 0),
                new Math.Vector(0,0),
                new Math.Vector(0,0),
                .1,
                0,
                250, //637.8,
                200 //635.6
        ));

         */

        Simulation sim = new Simulation(bodies);
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