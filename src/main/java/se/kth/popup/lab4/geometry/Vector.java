package se.kth.popup.lab4.geometry;

import java.util.Objects;

public class Vector {
    public final int x, y;

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector add(Vector that) {
        return new Vector(this.x + that.x, this.y + that.y);
    }

    public Vector subtract(Vector that) {
        return new Vector(this.x - that.x, this.y - that.y);
    }

    public Vector multiply(int k) {
        return new Vector(this.x * k, this.y * k);
    }

    public int dot(Vector that) {
        return this.x * that.x + this.y * that.y;
    }

    public int cross(Vector that) {
        return this.x * that.y - this.y * that.x;
    }

    public double angle(Vector that) {
        return Math.atan2(this.cross(that), this.dot(that));
    }

    public double length() {
        return Math.sqrt(dot(this));
    }

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(o == null || getClass() != o.getClass())
            return false;
        Vector vector = (Vector) o;
        return x == vector.x && y == vector.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}