package com.sutyaginev;

public class Coordinate {

    final Integer vertical;
    final Integer horizontal;

    public Coordinate(Integer vertical, Integer horizontal) {
        this.vertical = vertical;
        this.horizontal = horizontal;
    }

    public Integer getVertical() {
        return vertical;
    }

    public Integer getHorizontal() {
        return horizontal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinate that = (Coordinate) o;

        if (!vertical.equals(that.vertical)) return false;
        return horizontal.equals(that.horizontal);
    }

    @Override
    public int hashCode() {
        int result = vertical.hashCode();
        result = 31 * result + horizontal.hashCode();
        return result;
    }
}
