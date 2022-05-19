package com.example.devicetracker;

public final class Rotation {
    public static final Rotation ZERO = new Rotation(Angle.ZERO, Angle.ZERO, Angle.ZERO);

    private final Angle x, y, z;

    public Rotation(Angle x, Angle y, Angle z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Angle getX() {
        return x;
    }

    public Angle getY() {
        return y;
    }

    public Angle getZ() {
        return z;
    }

    static public boolean equals(Rotation r1, Rotation r2){
        return Angle.equals(r1.x, r2.x) &&
                Angle.equals(r1.y, r2.y) &&
                Angle.equals(r1.z, r2.z);
    }
}
