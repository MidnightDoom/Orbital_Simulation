package Simulator;

import Bodies.Body;
import Bodies.Ship;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

// a system of bodies
// has code for updating and rendering them all
public class Simulation {

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
}
