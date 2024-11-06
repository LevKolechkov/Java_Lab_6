package org.example.lab_6;

import geometry2d.Circle;
import geometry2d.Rectangle;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

public class Controller {

    @FXML
    private Canvas canvas;

    private final Random random = new Random();

    @FXML
    protected void onDrawCircleClick() {
        try {
            double radius = 10 + random.nextDouble() * 100;
            Circle circle = new Circle(radius);
            double x = random.nextDouble() * (canvas.getWidth() - radius * 2);
            double y = random.nextDouble() * (canvas.getHeight() - radius * 2);
            drawCircle(circle, x, y);
        } catch (Exception e) {
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
            drawRectangle(rectangle, x, y);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void drawCircle(Circle circle, double x, double y) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble()));
        gc.fillOval(x, y, circle.getRadius(), circle.getRadius());
    }

    private void drawRectangle(Rectangle rectangle, double x, double y) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble()));
        gc.fillRect(x, y, rectangle.getWidth(), rectangle.getHeight());
    }
}
