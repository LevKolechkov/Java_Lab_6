package geometry2d;

import exceptions.HeightException;
import exceptions.WidthException;

public class Rectangle implements Figure{
    private double width;
    private double height;

    public void setWidth(Double width) throws WidthException {
        if (width <= 0)
            throw new WidthException("Width must be higher than zero");

        else this.width = width;
    }

    public void setHeight(Double height) throws HeightException {
        if (height <= 0)
            throw new HeightException("Height of rectangle must be higher than zero");

        else this.height = height;
    }

    public double getWidth (){
        return this.width;
    }

    public double getHeight(){
        return this.height;
    }

    public Rectangle(double width, double height) throws HeightException, WidthException {
            setHeight(height);
            setWidth(width);

    }

    @Override
    public double area() {
        return width * height;
    }

    @Override
    public String toString() {
        return "width = " + this.width + "\nheight = " + this.height;
    }
}
