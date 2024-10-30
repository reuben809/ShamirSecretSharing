package com.shamir;

import java.math.BigInteger;

public class Point {
    int x;
    BigInteger y;

    public Point(int x, BigInteger y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("Point(x=%d, y=%s)", x, y.toString());
    }
}