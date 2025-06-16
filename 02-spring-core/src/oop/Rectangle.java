package oop;

public class Rectangle extends Shape {
    @Override
    public void draw() {
        System.out.println("직사각형을 그립니다.");
    }

    public void angleInfo() {
        System.out.println("직각을 이룹니다.");
    }
}