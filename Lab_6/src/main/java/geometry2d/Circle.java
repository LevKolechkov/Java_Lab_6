package geometry2d;

import exceptions.RadiusException;

public class Circle implements Figure {
    private double radius;

    public void setRadius(double radius) throws RadiusException {
        if(radius <= 0)
            throw new RadiusException("Radius of circle must be higher than zero");
        else this.radius = radius;
    }

    public Circle(double radius) throws RadiusException {
        setRadius(radius);
    }

    public double area() {
        return 3.14 * this.radius * this.radius;
    }

    @Override
    public String toString() {
        return "radius = " + this.radius;
    }
}
