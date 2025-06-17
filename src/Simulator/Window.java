package Simulator;

import Math.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;

// has a simulation which it draws
// use mouse to pan and zoom
public class Window extends JPanel implements MouseListener, MouseWheelListener, MouseMotionListener {

    boolean running = false;

    // variables for zooming and panning
    double zoom = Constants.STARTING_ZOOM;
    double xOffset = Constants.STARTING_X_OFFSET * Constants.STARTING_ZOOM;
    double yOffset = Constants.STARTING_Y_OFFSET * Constants.STARTING_ZOOM;
    Point lastMouseDrag = null;

    Simulation simulation;

    public Window(Simulation simulation) {
        this.simulation = simulation;

        setBounds(0,0,WIDTH,HEIGHT);
        setBackground(Constants.BACKGROUND);

        addMouseWheelListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);

        setVisible(true);
    }

    public void startSim(int delay) {
        running = true;

        try {
            while (running) {
                simulation.updateAll();
                repaint();
                Thread.sleep(delay);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Constants.BACKGROUND);
        g.clearRect(0,0,WIDTH,HEIGHT);
        g.fillRect(0,0,WIDTH,HEIGHT);

        Graphics2D g2d = (Graphics2D) g;

        AffineTransform original = g2d.getTransform();

        // Apply pan to all bodies
        g2d.translate((int) (getWidth() / 2.0 + xOffset), (int) (getHeight() / 2.0 + yOffset));
        simulation.paintAll(g2d, zoom);
        g2d.setTransform(original);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        double delta = 0.1f * e.getPreciseWheelRotation();
        double scaleFactor = 1 - delta;

        // Zoom around mouse position
        int mouseX = e.getX();
        int mouseY = e.getY();

        double dx = (mouseX - getWidth() / 2.0 - xOffset) / zoom;
        double dy = (mouseY - getHeight() / 2.0 - yOffset) / zoom;

        zoom *= scaleFactor;

        xOffset -= dx * (scaleFactor - 1) * zoom;
        yOffset -= dy * (scaleFactor - 1) * zoom;

        repaint();
    }

    // Panning with mouse drag
    @Override
    public void mousePressed(MouseEvent e) {
        lastMouseDrag = e.getPoint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (lastMouseDrag != null) {
            int dx = e.getX() - lastMouseDrag.x;
            int dy = e.getY() - lastMouseDrag.y;

            xOffset += dx;
            yOffset += dy;

            lastMouseDrag = e.getPoint();
            repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        lastMouseDrag = null;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}

}
