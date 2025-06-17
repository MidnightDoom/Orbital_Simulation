import java.util.ArrayList;

// A body that can rotate
public abstract class RotationalBody extends Body {
    protected double rotationalVelocity, angle;

    public RotationalBody(String name,double mass, Vector position, Vector velocity, Vector acceleration, double rotationalVelocity, double angle) {
        super(name, mass,position,velocity,acceleration);
        this.rotationalVelocity = rotationalVelocity;
        this.angle = angle;
    }

    // add rotation handling
    @Override
    public void update(ArrayList<Body> bodies) {
        super.update(bodies);
        angle += rotationalVelocity * Constants.TIME_STEP;
        if (angle >= 360) angle -= 360;
    }

    // allows the ship to apply force to itself with its engine
    @Override
    protected Vector netForce(ArrayList<Body> bodies) {
        return super.netForce(bodies);
    }
}
