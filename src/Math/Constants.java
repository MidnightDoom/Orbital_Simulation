package Math;

import Bodies.Body;

import java.awt.*;

public final class Constants {

    // CONSTANTS

    public static final double GRAVITATIONAL_CONSTANT  = 6.6743 * Math.pow(10,-11); // represents big G
    public static final double DISTANCE_CONSTANT = 10000; // represents meters per pixel. Default value means Earth is 637.8 pixels wide
    public static final double MASS_CONSTANT = Math.pow(10,21); // represents kg per unit of mass

    public static final double PLANET_SCALE = 1; // represents the scale of the planet's size, increased to allow for visual clarity
    public static final double STARTING_ZOOM = 0.025;
    public static final double STARTING_X_OFFSET = 0;
    public static final double STARTING_Y_OFFSET = -20000;

    public static final double TIME_STEP = 600; // represents seconds per timestep (per frame)
    public static final int FRAME_STEP = 20; // represents milliseconds per frame

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
}
