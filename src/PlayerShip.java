import java.awt.*;

public class PlayerShip extends Ship {

    boolean selected;

    public PlayerShip(String name, double mass, Vector position, Vector velocity, Vector acceleration, double rotationalVelocity, double angle) {
        super(name, mass, position, velocity, acceleration, rotationalVelocity, angle);
    }

    // TODO: add code for ship control
    // W and S control thrust level
    // A and D apply rotational velocity

    // TODO: zero thrust, zero rotation button
    // zeros thrust and rotation at max speed possible

    @Override
    public void paint(Graphics2D g) {
        super.paint(g);
        if (selected) {
            // TODO: draw circle around ship
            // TODO: draw line in facing direction
            // TODO: info overlay: thrust, velocity, rpm
        }
    }
}
