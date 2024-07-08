package Toolbox.Math;

import java.io.Serial;
import java.io.Serializable;
import java.nio.FloatBuffer;

@SuppressWarnings("unused")
public class Vec2 extends Vec1 implements Serializable, ReadableVec2, WritableVec2 {
    @Serial
    private static final long serialVersionUID = 1L;

    public float x, y;

    public Vec2() {
        super();
    }

    public Vec2(ReadableVec2 src) {
        set(src);
    }

    public Vec2(float x, float y) {
        set(x, y);
    }

    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vec2 set(ReadableVec2 src) {
        x = src.getX();
        y = src.getY();
        return this;
    }

    public float lengthSquared() {
        return x * x + y * y;
    }

    public Vec2 translate(float x, float y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Vec1 negate() {
        x = -x;
        y = -y;
        return this;
    }

    public Vec2 negate(Vec2 dest) {
        if (dest == null)
            dest = new Vec2();
        dest.x = -x;
        dest.y = -y;
        return dest;
    }

    public Vec2 normalise(Vec2 dest) {
        float l = length();

        if (dest == null)
            dest = new Vec2(x / l, y / l);
        else
            dest.set(x / l, y / l);

        return dest;
    }

    public static float dot(Vec2 left, Vec2 right) {
        return left.x * right.x + left.y * right.y;
    }

    public static float angle(Vec2 a, Vec2 b) {
        float dls = dot(a, b) / (a.length() * b.length());
        if (dls < -1f)
            dls = -1f;
        else if (dls > 1.0f)
            dls = 1.0f;
        return (float)Math.acos(dls);
    }

    public static Vec2 add(Vec2 left, Vec2 right, Vec2 dest) {
        if (dest == null)
            return new Vec2(left.x + right.x, left.y + right.y);
        else {
            dest.set(left.x + right.x, left.y + right.y);
            return dest;
        }
    }

    public static Vec2 sub(Vec2 left, Vec2 right, Vec2 dest) {
        if (dest == null)
            return new Vec2(left.x - right.x, left.y - right.y);
        else {
            dest.set(left.x - right.x, left.y - right.y);
            return dest;
        }
    }

    public Vec2 store(FloatBuffer buf) {
        buf.put(x);
        buf.put(y);
        return this;
    }

    public Vec1 load(FloatBuffer buf) {
        x = buf.get();
        y = buf.get();
        return this;
    }

    public Vec1 scale(float scale) {

        x *= scale;
        y *= scale;

        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(64);

        sb.append("Vec2[");
        sb.append(x);
        sb.append(", ");
        sb.append(y);
        sb.append(']');
        return sb.toString();
    }

    public final float getX() {
        return x;
    }

    public final float getY() {
        return y;
    }

    public final void setX(float x) {
        this.x = x;
    }

    public final void setY(float y) {
        this.y = y;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Vec2 other = (Vec2) obj;

        if (x == other.x && y == other.y) return true;

        return false;
    }
}
