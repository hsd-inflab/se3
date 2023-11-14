package com.example.application.views.main;

public class AddSin {
    public double addsin(double a) {
        double angleInRadian = Math.toRadians(a);
        double sin = Math.sin(angleInRadian);
        return  sin;
    }
}
