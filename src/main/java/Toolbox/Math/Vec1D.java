package Toolbox.Math;

import java.io.Serializable;
import java.nio.FloatBuffer;

@SuppressWarnings("unused")
public abstract class Vec1D implements Serializable, ReadableVec1D {

    protected Vec1D() {
        super();
    }

    public final float length() {
        return (float) Math.sqrt(lengthSquared());
    }

    public abstract float lengthSquared();

    public abstract Vec1D load(FloatBuffer buf);

    public abstract Vec1D negate();

    public final Vec1D normalise() {
        float len = length();
        if (len != 0.0f) {
            float l = 1.0f / len;
            return scale(l);
        } else
            throw new IllegalStateException("Vector Length Zero");
    }

    public abstract Vec1D store(FloatBuffer buf);

    public abstract Vec1D scale(float scale);

}