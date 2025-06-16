package oop;

public class ShapeExample {
    public static void main(String[] args) {
        Shape[] shapes = {new Circle(), new Rectangle()};
        for(Shape shape : shapes) {
            shape.draw();
        }

        for (int i = 0; i < shapes.length; i++) {
            if (shapes[i] instanceof Circle) {
                ((Circle) shapes[i]).radiusInfo();
            } else if (shapes[i] instanceof Rectangle) {
                ((Rectangle) shapes[i]).angleInfo();
            }
        }
    }
}