package Math;

import Bodies.Body;

import java.awt.*;

public final class Constants {

    // CONSTANTS
    public static final double GRAVITATIONAL_CONSTANT  = 6.6743 * Math.pow(10,-11); // represents big G
    public static final double DISTANCE_CONSTANT = 10000; // represents meters per pixel. Default value means Earth is 1275.6 pixels wide
    public static final double MASS_CONSTANT = Math.pow(10,21); // represents kg per unit of mass

    public static boolean TRUE_SCALE = false; // Disables planet scaling for a true scale experience
    // PLANET_SCALE and SCALE_FACTOR help scale the planets for more visual clarity
    public static double PLANET_SCALE = .5; // controls how aggressively smaller bodies are scaled
    public static double SCALE_FACTOR = 100; // controls overall scale amount
    public static double STARTING_ZOOM = 0.1;
    public static double STARTING_X_OFFSET = 0;
    public static double STARTING_Y_OFFSET = -0;

    public static double TIME_STEP = 600; // represents seconds per timestep (per frame)
    public static final int FRAME_STEP = 50; // represents milliseconds per frame

    public static final boolean DEBUG_PRINT = false;

    // COLORS
    public static Color PLANET_FILL = Color.WHITE;
    public static Color PLANET_OUTLINE = Color.WHITE;
    public static Color BACKGROUND = Color.BLACK;
    public static Color CONTROLLED_SHIP = Color.BLUE;
    public static Color FRIENDLY_SHIP = Color.GREEN;
    public static Color ENEMY_SHIP = Color.RED;

    // SHIP TYPES
    public static final int FRIGATE = 0;
    public static final int DESTROYER = 1;
    public static final int CRUISER = 2;
    public static final int CAPITAL = 3;
    public static final int SUPER_CAPITAL = 4;

    // HELPER METHODS

    // used to get velocities to put things in orbit
    // pos is the orbital position of the object, the parent is what it will orbit
    public static Vector velocityForOrbit(Vector pos, Body parent) {
        // calculates velocity with v = sqrt(MG/R)
        double velocity = Math.sqrt(GRAVITATIONAL_CONSTANT * parent.massScaled() / pos.scale(DISTANCE_CONSTANT).distance(parent.positionScaled()));

        Vector direction = pos.distanceVector(parent.position()).rotate(90).unitVector();
        return direction.scale(velocity);
    }

    // returns simulated seconds per second
    // Time step is simulated seconds per step, frame step is milliseconds between steps
    public static double getTimeScale() {
        return TIME_STEP * ((double) 1000 / FRAME_STEP);
    }

    public static void debugPrint(String s) {
        if (DEBUG_PRINT) System.out.println(s);
    }

    // HELPS CONVERT VALUES FROM WIKIPEDIA TO USABLE VALUES

    public static double scaleBody(double size) {
        if (TRUE_SCALE) return size;
        return size + (SCALE_FACTOR / Math.pow(size, PLANET_SCALE));
    }

    public static double getRotationSpeed(double hours) {
        return 1 / (hours * 10);
    }

    public static Vector getPositionVector(Vector base) {
        return base.scale(1 / DISTANCE_CONSTANT);
    }

    public static Vector getPositionVectorFromKM(Vector base) {
        return base.scale(1000 / DISTANCE_CONSTANT);
    }

    public static double getRadius(double base) {
        return scaleBody(base / DISTANCE_CONSTANT);
    }

    public static double getMass(double base) {
        return base / MASS_CONSTANT;
    }
}
