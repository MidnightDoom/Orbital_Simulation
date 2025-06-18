package Bodies;

import Math.*;
import java.awt.*;
import java.util.ArrayList;

// shared code for all bodies
public abstract class Body {
    protected double mass;
    protected Vector position, velocity, acceleration;
    protected String name;

    public Body(String name, double mass, Vector position, Vector velocity, Vector acceleration) {
        this.mass = mass;
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.name = name;
    }

    // note: xxxScaled() outputs the scaled value, as most values are scaled to make fitting them onscreen easier

    public double massScaled() {
        return mass * Constants.MASS_CONSTANT;
    }

    public Vector position() {
        return position;
    }

    public Vector positionScaled() {
        return position.scale(Constants.DISTANCE_CONSTANT);
    }

    public Vector velocity() {
        return velocity;
    }

    public Vector accelerationScaled() {
        return acceleration.scale(Constants.DISTANCE_CONSTANT / Math.pow(Constants.TIME_STEP, 2));
    }

    public Vector acceleration() {
        return acceleration;
    }

    public Vector velocityScaled() {
        // scaled velocity removes TIME_STEP and scales the vector
        return velocity.scale(Constants.DISTANCE_CONSTANT / Constants.TIME_STEP);
    }

    // TODO: change to non-abstract method, draw previous and predicted path
    public abstract void paint(Graphics2D g);

    protected Vector netForce(ArrayList<Body> bodies) {
        Vector force = new Vector(0, 0);

        // calculate net force
        for (Body body : bodies) {

            // skip if this is the same as the body or if the distance is 0
            // prevents divide by 0 errors
            if (body.equals(this)) continue;

            // force component
            double forceComp = massScaled() * body.massScaled() * Constants.GRAVITATIONAL_CONSTANT / positionScaled().distanceSquared(body.positionScaled());
            Vector direction = position().distanceVector(body.position()).unitVector();
            force = force.add(direction.scale(forceComp));
        }
        return force;
    }

    protected void updateVectors(Vector force, ArrayList<Body> bodies) {
        /*
        Math.Vector newPosition = position.add(velocity().scale(Math.Constants.TIME_STEP / Math.Constants.DISTANCE_CONSTANT)).add(acceleration().scale(Math.pow(Math.Constants.TIME_STEP, 2) / (2 * Math.Constants.DISTANCE_CONSTANT)));

        System.out.println(newPosition);

        Math.Vector newAcceleration = force.scale(1 / massScaled());

        Math.Vector newVelocity = velocity.add(acceleration().add(newAcceleration).scale(Math.Constants.TIME_STEP / 2));


        acceleration = newAcceleration;
        velocity = newVelocity;
        position = newPosition;

         */

        // UPDATED SIMULATION WITH VERLET (thanks ChatGPT) (better accuracy, very little to no long term drift)

        Vector oldAcceleration = acceleration;

        // Update velocity (half step)
        velocity = velocity.add(oldAcceleration.scale(Constants.TIME_STEP / 2));

        // Update position
        position = position.add(velocity.scale(Constants.TIME_STEP / Constants.DISTANCE_CONSTANT));

        // Recompute acceleration using new position (requires calling netForce again)
        Vector newForce = netForce(bodies); // You'll need to pass the body list again
        Vector newAcceleration = newForce.scale(1 / massScaled());

        // Update velocity (second half step)
        velocity = velocity.add(newAcceleration.scale(Constants.TIME_STEP / 2));

        acceleration = newAcceleration;
    }

    // takes in a list of bodies, calculates the net force between them and this body, then updates its position, velocity, and acceleration
    public void update(ArrayList<Body> bodies) {

        Constants.debugPrint(name + ":" + position + ", " + velocity + ", " + acceleration);

        Vector force = netForce(bodies);
        updateVectors(force, bodies);
    }

}