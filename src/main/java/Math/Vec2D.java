package Math;

import java.io.Serial;
import java.io.Serializable;
import java.nio.FloatBuffer;

@SuppressWarnings("unused")
public class Vec2D extends Vec1D implements Serializable, ReadableVec2D, WritableVec2D {
    @Serial
    private static final long serialVersionUID = 1L;

    public float x, y;

    public Vec2D() {
        super();
    }

    public Vec2D(ReadableVec2D src) {
        set(src);
    }

    public Vec2D(float x, float y) {
        set(x, y);
    }

    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vec2D set(ReadableVec2D src) {
        x = src.getX();
        y = src.getY();
        return this;
    }

    public float lengthSquared() {
        return x * x + y * y;
    }

    public Vec2D translate(float x, float y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Vec1D negate() {
        x = -x;
        y = -y;
        return this;
    }

    public Vec2D negate(Vec2D dest) {
        if (dest == null)
            dest = new Vec2D();
        dest.x = -x;
        dest.y = -y;
        return dest;
    }

    public Vec2D normalise(Vec2D dest) {
        float l = length();

        if (dest == null)
            dest = new Vec2D(x / l, y / l);
        else
            dest.set(x / l, y / l);

        return dest;
    }

    public static float dot(Vec2D left, Vec2D right) {
        return left.x * right.x + left.y * right.y;
    }

    public static float angle(Vec2D a, Vec2D b) {
        float dls = dot(a, b) / (a.length() * b.length());
        if (dls < -1f)
            dls = -1f;
        else if (dls > 1.0f)
            dls = 1.0f;
        return (float)Math.acos(dls);
    }

    public static Vec2D add(Vec2D left, Vec2D right, Vec2D dest) {
        if (dest == null)
            return new Vec2D(left.x + right.x, left.y + right.y);
        else {
            dest.set(left.x + right.x, left.y + right.y);
            return dest;
        }
    }

    public static Vec2D sub(Vec2D left, Vec2D right, Vec2D dest) {
        if (dest == null)
            return new Vec2D(left.x - right.x, left.y - right.y);
        else {
            dest.set(left.x - right.x, left.y - right.y);
            return dest;
        }
    }

    public Vec2D store(FloatBuffer buf) {
        buf.put(x);
        buf.put(y);
        return this;
    }

    public Vec1D load(FloatBuffer buf) {
        x = buf.get();
        y = buf.get();
        return this;
    }

    public Vec1D scale(float scale) {

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
        Vec2D other = (Vec2D) obj;

        if (x == other.x && y == other.y) return true;

        return false;
    }
}
