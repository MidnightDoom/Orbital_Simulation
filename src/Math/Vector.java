package Math;

public record Vector(double x, double y) {

    // VECTOR OPERATIONS

    public double magnitudeSquared() {
        return Math.pow(x(),2) + Math.pow(y(),2);
    }

    public double magnitude() {
        return Math.sqrt(magnitudeSquared());
    }

    public double distanceSquared(Vector other) {
        return subtract(other).magnitudeSquared();
    }

    public double distance(Vector other) {
        return Math.sqrt(distanceSquared(other));
    }

    public Vector distanceVector(Vector other) {
        return other.subtract(this);
    }

    public Vector scale(double s) {
        return new Vector(x() * s, y() * s);
    }

    public Vector rotate(double theta) {
        theta = Math.toRadians(theta);
        return new Vector(x() * Math.cos(theta) - y() * Math.sin(theta), x() * Math.sin(theta) + y() * Math.cos(theta));
    }

    public Vector add(double d) {
        if (magnitude() == 0) return this;
        return scale((magnitude() + d) / magnitude());
    }

    public Vector add(double x, double y) {
        return new Vector(this.x() + x, this.y() + y);
    }

    public Vector add(Vector other) {
        return new Vector(x() + other.x(), y() + other.y());
    }

    public Vector subtract(Vector other) {
        return new Vector(x() - other.x(), y() - other.y());
    }

    public Vector unitVector() {
        if (magnitude() == 0) return this;
        return new Vector(x() / magnitude(), y() / magnitude());
    }

    public double cross(Vector other) {
        return x() * other.y() - y() * other.x();
    }

    public double dot(Vector other) {
        return x() * other.x() + y() * other.y();
    }
        
    @Override
    public String toString() {
        return "<" + x() + "," + y() + ">";
    }
}
