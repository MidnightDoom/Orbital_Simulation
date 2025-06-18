package Simulator;

import Bodies.*;
import Math.*;

import java.util.ArrayList;

public final class SavedSims {

    private static final ArrayList<Body> bodies = new ArrayList<>();

    public static ArrayList<Body> mars() {

        Constants.TIME_STEP = 1;
        Constants.STARTING_ZOOM = 0.000002;
        Constants.STARTING_X_OFFSET = 0;
        Constants.STARTING_Y_OFFSET = 0;
        Constants.SCALE_FACTOR = 100;
        Constants.PLANET_SCALE = 0.5;

        bodies.clear();
        bodies.add(new Planet(
                "Mars",
                639 * Math.pow(10,23),
                new Vector(0, 0),
                new Vector(0,0),
                new Vector(0,0),
                Constants.getRotationSpeed(24.6),
                0,
                Constants.scaleBody(6794000),
                Constants.scaleBody(6752000)
        ));
        bodies.add(new Planet(
                "Phobos",
                1,
                new Vector(9376000, 0),
                Constants.velocityForOrbit(new Vector(9376000, 0), bodies.getFirst()),
                new Vector(0,0),
                Constants.getRotationSpeed(-7.6),//360 / (7.6 * Constants.getTimeScale()),
                0,
                Constants.scaleBody(25900),
                Constants.scaleBody(18320)
        ));
        bodies.add(new Planet(
                "Deimos",
                1.51 * Math.pow(10,15),
                new Vector(234632000, 0),
                Constants.velocityForOrbit(new Vector(234632000, 0), bodies.getFirst()),
                new Vector(0,0),
                Constants.getRotationSpeed(1.2644),
                0,
                Constants.scaleBody(16080),
                Constants.scaleBody(10220)
        ));
        return bodies;
    }

    public static ArrayList<Body> earth() {

        bodies.clear();
        bodies.add(new Planet(
                "Earth",
                5972,
                new Vector(0, 0),
                new Vector(0, 0),
                new Vector(0,0),
                360 / (24 * Constants.getTimeScale()),
                0,
                Constants.scaleBody(1275.6),
                Constants.scaleBody(1271.3)
        ));
        bodies.add(new Planet(
                "Luna",
                73.4,
                new Vector(38440, 0),
                Constants.velocityForOrbit(new Vector(38440, 0), bodies.getFirst()),
                new Vector(0,0),
                360 / (29.5 * 24 * Constants.getTimeScale()),
                0,
                Constants.scaleBody(347.6),
                Constants.scaleBody(347.2)
        ));
        return bodies;
    }

    public static ArrayList<Body> solarSystem() {

        Constants.TIME_STEP = 36000;
        Constants.STARTING_ZOOM = 0.0001;
        Constants.STARTING_X_OFFSET = 0;
        Constants.STARTING_Y_OFFSET = 0;
        Constants.PLANET_SCALE = 1000;

        bodies.clear();
        bodies.add(new Planet(
                "Sol",
                1.989 * Math.pow(10,9),
                new Vector(0, 0),
                new Vector(0,0),
                new Vector(0,0),
                0,
                0,
                Constants.scaleBody(139140),
                Constants.scaleBody(139140)
        ));
        bodies.add(new Planet(
                "Mercury",
                32.85,
                new Vector(0, 5800000),
                Constants.velocityForOrbit(new Vector(0, 5800000), bodies.getFirst()),
                new Vector(0,0),
                360 / (170 * 24 * Constants.getTimeScale()),
                0,
                Constants.scaleBody(487.9),
                Constants.scaleBody(487.9)
        ));
        bodies.add(new Planet(
                "Venus",
                4867,
                new Vector(0, 10893000),
                Constants.velocityForOrbit(new Vector(0, 10893000), bodies.getFirst()),
                new Vector(0,0),
                360 / (-243 * 24 * Constants.getTimeScale()),
                0,
                Constants.scaleBody(1210),
                Constants.scaleBody(1210)
        ));
        bodies.add(new Planet(
                "Earth",
                5972,
                new Vector(0, 14900000),
                Constants.velocityForOrbit(new Vector(0, 14900000), bodies.getFirst()),
                new Vector(0,0),
                360 / (24 * Constants.getTimeScale()),
                0,
                Constants.scaleBody(1275.6),
                Constants.scaleBody(1271.3)
        ));
        bodies.add(new Planet(
                "Luna",
                73.4,
                new Vector(38440, 14900000),
                Constants.velocityForOrbit(new Vector(38440, 14900000), bodies.get(3)).add(bodies.get(3).velocity()),
                new Vector(0,0),
                360 / (29.5 * 24 * Constants.getTimeScale()),
                0,
                Constants.scaleBody(347.6),
                Constants.scaleBody(347.2)
        ));
        bodies.add(new Planet(
                "Mars",
                639,
                new Vector(0, 22800000),
                Constants.velocityForOrbit(new Vector(0, 22800000), bodies.getFirst()),
                new Vector(0,0),
                360 / (24.6 * Constants.getTimeScale()),
                0,
                Constants.scaleBody(679.4),
                Constants.scaleBody(675.2)
        ));
        bodies.add(new Planet(
                "Phobos",
                0.0000106,
                new Vector(637.6, 22800000),
                Constants.velocityForOrbit(new Vector(637.6, 22800000), bodies.get(5)).add(bodies.get(5).velocity()),
                new Vector(0,0),
                360 / (24.6 * Constants.getTimeScale()),
                0,
                Constants.scaleBody(2.59),
                Constants.scaleBody(1.83)
        ));
        bodies.add(new Planet(
                "Deimos",
                0.00000151,
                new Vector(2346.32, 22800000),
                Constants.velocityForOrbit(new Vector(2346.32, 22800000), bodies.get(5)).add(bodies.get(5).velocity()),
                new Vector(0,0),
                360 / (1.2644 * Constants.getTimeScale()),
                0,
                Constants.scaleBody(1.608),
                Constants.scaleBody(1.022)
        ));
        return bodies;
    }
}
