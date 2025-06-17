import java.awt.*;

public final class Constants {

    // CONSTANTS

    static double GRAVITATIONAL_CONSTANT  = 6.6743 * Math.pow(10,-11); // represents big G
    static double DISTANCE_CONSTANT = 10000; // represents meters per pixel. Default value means Earth is 637.8 pixels wide
    static double MASS_CONSTANT = Math.pow(10,21); // represents kg per unit of mass

    static double PLANET_SCALE = 1; // represents the scale of the planet's size, increased to allow for visual clarity
    static double STARTING_ZOOM = 0.025;
    static double STARTING_X_OFFSET = 0;
    static double STARTING_Y_OFFSET = -20000;

    static double TIME_STEP = 600; // represents seconds per timestep (per frame)
    static int FRAME_STEP = 20; // represents milliseconds per frame

    // COLORS
    static Color PLANET_FILL = Color.WHITE;
    static Color PLANET_OUTLINE = Color.WHITE;
    static Color BACKGROUND = Color.BLACK;
    static Color CONTROLLED_SHIP = Color.BLUE;
    static Color FRIENDLY_SHIP = Color.GREEN;
    static Color ENEMY_SHIP = Color.RED;

    // SHIP TYPES
    static int FRIGATE = 0;
    static int DESTROYER = 1;
    static int CRUISER = 2;
    static int CAPITAL = 3;
    static int SUPER_CAPITAL = 4;

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
