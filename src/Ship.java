import java.awt.*;
import java.util.ArrayList;

// a body that represents a spaceship, able to move itself
public class Ship extends RotationalBody {

    double MAX_THRUST, thrust;
    // rating of the ship, determines its icon
    // ie: frigate, destroyer, cruiser, capital, super capital
    int type;

    public Ship(String name, double mass, Vector position, Vector velocity, Vector acceleration, double rotationalVelocity, double angle) {
        super(name, mass, position, velocity, acceleration, rotationalVelocity, angle);
    }

    // allows the ship to apply force to itself with its engine
    @Override
    public Vector netForce(ArrayList<Body> bodies) {
        return super.netForce(bodies).add(thrust * Math.cos(angle), thrust * Math.sin(angle));
    }

    @Override
    public void paint(Graphics2D g) {
        // scale the ship so it’s always visible
        switch(type) {
            case 1 -> {}
            case 2 -> {}
            case 3 -> {}
            case 4 -> {}
            default -> {}
        }
    }
}
