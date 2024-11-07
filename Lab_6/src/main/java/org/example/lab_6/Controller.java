package org.example.lab_6;

import exceptions.HeightException;
import exceptions.RadiusException;
import exceptions.WidthException;
import geometry2d.Circle;
import geometry2d.Rectangle;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Controller {

    @FXML
    private Canvas canvas;

    private final Random random = new Random();
    private final List<Drawable> shapes = new ArrayList<>();
    private Drawable selectedShape = null;
    private double offsetX, offsetY;

    @FXML
    protected void initialize() {
        canvas.setOnMousePressed(this::onMousePressed);
        canvas.setOnMouseDragged(this::onMouseDragged);
        canvas.setOnMouseReleased(this::onMouseReleased);
    }

    @FXML
    protected void onDrawCircleClick() {
        try {
            double radius = 10 + random.nextDouble() * 100;
            Circle circle = new Circle(radius);
            double x = random.nextDouble() * (canvas.getWidth() - radius * 2);
            double y = random.nextDouble() * (canvas.getHeight() - radius * 2);
            Color color = Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble());
            shapes.add(new DrawableCircle(circle, x, y, color));
            drawShapes();
        } catch (RadiusException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onDrawRectangleClick() {
        try {
            double width = 10 + random.nextDouble() * 100;
            double height = 10 + random.nextDouble() * 100;
            Rectangle rectangle = new Rectangle(width, height);
            double x = random.nextDouble() * (canvas.getWidth() - width);
            double y = random.nextDouble() * (canvas.getHeight() - height);
            Color color = Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble());
            shapes.add(new DrawableRectangle(rectangle, x, y, color));
            drawShapes();
        } catch (HeightException | WidthException e) {
            e.printStackTrace();
        }
    }

    private void drawShapes() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (Drawable shape : shapes) {
            gc.setFill(shape.getColor());
            if (shape instanceof DrawableCircle) {
                DrawableCircle drawableCircle = (DrawableCircle) shape;
                gc.fillOval(drawableCircle.getX(), drawableCircle.getY(),
                        drawableCircle.getCircle().getRadius() * 2, drawableCircle.getCircle().getRadius() * 2);
            } else if (shape instanceof DrawableRectangle) {
                DrawableRectangle drawableRectangle = (DrawableRectangle) shape;
                gc.fillRect(drawableRectangle.getX(), drawableRectangle.getY(),
                        drawableRectangle.getRectangle().getWidth(), drawableRectangle.getRectangle().getHeight());
            }
        }
    }

    private void onMousePressed(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            for (int i = shapes.size() - 1; i >= 0; i--) {
                Drawable shape = shapes.get(i);
                if (isMouseOverShape(event.getX(), event.getY(), shape)) {
                    selectedShape = shape;
                    offsetX = event.getX() - shape.getX();
                    offsetY = event.getY() - shape.getY();
                    shapes.remove(shape);
                    shapes.add(shape); // Move to front
                    drawShapes();
                    break;
                }
            }
        } else if (event.getButton() == MouseButton.SECONDARY) {
            for (int i = shapes.size() - 1; i >= 0; i--) {
                Drawable shape = shapes.get(i);
                if (isMouseOverShape(event.getX(), event.getY(), shape)) {
                    shape.setColor(Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble()));
                    drawShapes();
                    break;
                }
            }
        }
    }

    private void onMouseDragged(MouseEvent event) {
        if (selectedShape != null) {
            selectedShape.setX(event.getX() - offsetX);
            selectedShape.setY(event.getY() - offsetY);
            drawShapes();
        }
    }

    private void onMouseReleased(MouseEvent event) {
        selectedShape = null;
    }

    private boolean isMouseOverShape(double mouseX, double mouseY, Drawable shape) {
        if (shape instanceof DrawableCircle) {
            DrawableCircle drawableCircle = (DrawableCircle) shape;
            double radius = drawableCircle.getCircle().getRadius();
            return mouseX >= drawableCircle.getX() && mouseX <= drawableCircle.getX() + radius * 2
                    && mouseY >= drawableCircle.getY() && mouseY <= drawableCircle.getY() + radius * 2;
        } else if (shape instanceof DrawableRectangle) {
            DrawableRectangle drawableRectangle = (DrawableRectangle) shape;
            return mouseX >= drawableRectangle.getX() && mouseX <= drawableRectangle.getX() + drawableRectangle.getRectangle().getWidth()
                    && mouseY >= drawableRectangle.getY() && mouseY <= drawableRectangle.getY() + drawableRectangle.getRectangle().getHeight();
        }
        return false;
    }

    private interface Drawable {
        double getX();
        double getY();
        void setX(double x);
        void setY(double y);
        Color getColor();
        void setColor(Color color);
    }

    private class DrawableCircle implements Drawable {
        private final Circle circle;
        private double x, y;
        private Color color;

        public DrawableCircle(Circle circle, double x, double y, Color color) {
            this.circle = circle;
            this.x = x;
            this.y = y;
            this.color = color;
        }

        public Circle getCircle() {
            return circle;
        }

        @Override
        public double getX() {
            return x;
        }

        @Override
        public double getY() {
            return y;
        }

        @Override
        public void setX(double x) {
            this.x = x;
        }

        @Override
        public void setY(double y) {
            this.y = y;
        }

        @Override
        public Color getColor() {
            return color;
        }

        @Override
        public void setColor(Color color) {
            this.color = color;
        }
    }

    private class DrawableRectangle implements Drawable {
        private final Rectangle rectangle;
        private double x, y;
        private Color color;

        public DrawableRectangle(Rectangle rectangle, double x, double y, Color color) {
            this.rectangle = rectangle;
            this.x = x;
            this.y = y;
            this.color = color;
        }

        public Rectangle getRectangle() {
            return rectangle;
        }

        @Override
        public double getX() {
            return x;
        }

        @Override
        public double getY() {
            return y;
        }

        @Override
        public void setX(double x) {
            this.x = x;
        }

        @Override
        public void setY(double y) {
            this.y = y;
        }

        @Override
        public Color getColor() {
            return color;
        }

        @Override
        public void setColor(Color color) {
            this.color = color;
        }
    }
}
