package Toolbox.Math;

import java.io.Serializable;
import java.nio.FloatBuffer;

@SuppressWarnings("unused")
public abstract class Vec1 implements Serializable, ReadableVec1 {

    protected Vec1() {
        super();
    }

    public final float length() {
        return (float) Math.sqrt(lengthSquared());
    }

    public abstract float lengthSquared();

    public abstract Vec1 load(FloatBuffer buf);

    public abstract Vec1 negate();

    public final Vec1 normalise() {
        float len = length();
        if (len != 0.0f) {
            float l = 1.0f / len;
            return scale(l);
        } else
            throw new IllegalStateException("Vector Length Zero");
    }

    public abstract Vec1 store(FloatBuffer buf);

    public abstract Vec1 scale(float scale);

}