package Simulator;

import Bodies.Body;
import Bodies.Ship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

// a system of bodies
// has code for updating and rendering them all
public class Simulation extends KeyAdapter {

    private boolean paused = false, spacePressed = false, timeWarp = false;
    private int delay;
    private boolean running;


    private final ArrayList<Body> bodies;

    public Simulation() {
        bodies = new ArrayList<>();
    }

    public Simulation(ArrayList<Body> bodies) {
        this.bodies = bodies;
    }

    public void updateAll() {
        for (Body body : bodies) {
            body.update(bodies);
        }
    }

    public void paintAll(Graphics2D g, double zoom) {

        AffineTransform original = g.getTransform();

        for (Body body : bodies) {
            // don’t zoom for ships
            if (!(body instanceof Ship)) g.scale(zoom, zoom);

            body.paint(g);
            g.setTransform(original);
        }
    }

    public void startSim(int delay, JPanel p) {
        this.delay = delay;
        running = true;
        try {
            while (running) {
                // prevent updating while paused
                if (!paused) {
                    updateAll();
                    p.repaint();
                }
                Thread.sleep(this.delay / (timeWarp ? 10 : 1));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case(KeyEvent.VK_SPACE) -> {
                if (!spacePressed) {
                    paused = !paused;
                    System.out.println(paused ? "Paused" : "Unpaused");
                    spacePressed = true;
                }
            }
            case(KeyEvent.VK_SHIFT) -> {timeWarp = true;}
            default -> {}
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case(KeyEvent.VK_SHIFT) -> {timeWarp = false;}
            case(KeyEvent.VK_SPACE) -> {spacePressed = false;}
            default -> {}
        }

    }
}
