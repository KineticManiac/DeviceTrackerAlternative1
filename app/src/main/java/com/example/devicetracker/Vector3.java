package com.example.devicetracker;

import androidx.annotation.NonNull;

import java.util.Objects;

public final class Vector3 {
    public static final Vector3 ZERO = new Vector3(0, 0 ,0);

    private final double x, y, z;

    public Vector3(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public double getAbsoluteSquare(){
        return x*x + y*y + z*z;
    }

    public double getAbsolute(){
        return Math.sqrt(getAbsoluteSquare());
    }

    public Vector3 getUnit(){
        return scale(this, 1.0 / getAbsolute());
    }

    static public boolean equals(@NonNull Vector3 v1, @NonNull Vector3 v2){
        return (v1.x == v2.x) && (v1.y == v2.y) && (v1.z == v2.z);
    }

    static public Vector3 add(@NonNull Vector3 v1, @NonNull Vector3 v2){
        return new Vector3(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z);
    }

    static public Vector3 scale(@NonNull Vector3 v, double d){
        return new Vector3(v.x * d, v.y * d, v.z * d);
    }

    @NonNull
    @Override
    public String toString(){
        return String.format("x: %f, y: %f, z: %f", x, y ,z);
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof Vector3){
            return equals(this, (Vector3) obj);
        }
        else{
            return false;
        }
    }

    @Override
    public int hashCode(){
        return Objects.hash(x, y, z);
    }

}
