package Bodies;

import Math.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerShip extends Ship implements KeyListener {

    boolean selected;

    public PlayerShip(String name, double mass, Vector position, Vector velocity, Vector acceleration, double rotationalVelocity, double angle) {
        super(name, mass, position, velocity, acceleration, rotationalVelocity, angle);
    }

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

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case (KeyEvent.VK_W) -> {
                thrust += RAMP_UP * Constants.TIME_STEP;
                if (thrust > MAX_THRUST) thrust = MAX_THRUST;
            }
            case (KeyEvent.VK_S) -> {
                thrust -= RAMP_DOWN * Constants.TIME_STEP;
                if (thrust < 0) thrust = 0;
            }
            // TODO: check if rotation is right way
            // TODO: refine values
            case (KeyEvent.VK_A) -> rotationalVelocity += ROTATIONAL_ACCELERATION * Constants.TIME_STEP;
            case (KeyEvent.VK_D) -> rotationalVelocity -= ROTATIONAL_ACCELERATION * Constants.TIME_STEP;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
