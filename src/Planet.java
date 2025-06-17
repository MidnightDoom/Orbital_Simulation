import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

// a spheroid body representing planets, moons, asteroids, stars, etc
public class Planet extends RotationalBody {

    // the major and minor axes represent the maximum and minimum diameter of the object
    // by default the body is longest in the x direction
    private final double majorAxis;
    private final double minorAxis;

    public Planet(String name, double mass, Vector position, Vector velocity, Vector acceleration, double rotationalVelocity, double angle, double majorAxis, double minorAxis) {
        super(name, mass,position,velocity,acceleration, rotationalVelocity, angle);
        this.majorAxis = majorAxis;
        this.minorAxis = minorAxis;
    }

    // code for painting the body
    @Override
    public void paint(Graphics2D g) {

        AffineTransform old = g.getTransform();
        g.rotate(angle, position.x(), position.y());

        g.setColor(Constants.PLANET_OUTLINE);
        g.drawOval(
                (int) (position.x() - majorAxis / 2),
                (int) (position.y() - minorAxis / 2),
                (int) majorAxis,
                (int) minorAxis
        );
        g.setColor(Constants.PLANET_FILL);
        g.fillOval(
                (int) (position.x() - majorAxis / 2),
                (int) (position.y() - minorAxis / 2),
                (int) (majorAxis),
                (int) (minorAxis)
        );
        g.setTransform(old);
    }

    @Override
    public void update(ArrayList<Body> bodies) {
        super.update(bodies);
    }
}
