package Toolbox.Math;

import java.io.Serial;
import java.io.Serializable;
import java.nio.FloatBuffer;

@SuppressWarnings("unused")
public class Vec4D extends Vec1D implements Serializable, ReadableVec4D, WritableVec4D {

    @Serial
    private static final long serialVersionUID = 1L;

    public float x, y, z, w;

    public Vec4D() {
        super();
    }

    public Vec4D(ReadableVec4D src) {
        set(src);
    }

    public Vec4D(float x, float y, float z, float w) {
        set(x, y, z, w);
    }

    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void set(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Vec4D set(ReadableVec4D src) {
        x = src.getX();
        y = src.getY();
        z = src.getZ();
        w = src.getW();
        return this;
    }

    public float lengthSquared() {
        return x * x + y * y + z * z + w * w;
    }

    public Vec4D translate(float x, float y, float z, float w) {
        this.x += x;
        this.y += y;
        this.z += z;
        this.w += w;
        return this;
    }

    public static Vec4D add(Vec4D left, Vec4D right, Vec4D dest) {
        if (dest == null)
            return new Vec4D(left.x + right.x, left.y + right.y, left.z + right.z, left.w + right.w);
        else {
            dest.set(left.x + right.x, left.y + right.y, left.z + right.z, left.w + right.w);
            return dest;
        }
    }

    public static Vec4D sub(Vec4D left, Vec4D right, Vec4D dest) {
        if (dest == null)
            return new Vec4D(left.x - right.x, left.y - right.y, left.z - right.z, left.w - right.w);
        else {
            dest.set(left.x - right.x, left.y - right.y, left.z - right.z, left.w - right.w);
            return dest;
        }
    }

    public Vec1D negate() {
        x = -x;
        y = -y;
        z = -z;
        w = -w;
        return this;
    }

    public Vec4D negate(Vec4D dest) {
        if (dest == null)
            dest = new Vec4D();
        dest.x = -x;
        dest.y = -y;
        dest.z = -z;
        dest.w = -w;
        return dest;
    }

    public Vec4D normalise(Vec4D dest) {
        float l = length();

        if (dest == null)
            dest = new Vec4D(x / l, y / l, z / l, w / l);
        else
            dest.set(x / l, y / l, z / l, w / l);

        return dest;
    }

    public static float dot(Vec4D left, Vec4D right) {
        return left.x * right.x + left.y * right.y + left.z * right.z + left.w * right.w;
    }

    public static float angle(Vec4D a, Vec4D b) {
        float dls = dot(a, b) / (a.length() * b.length());
        if (dls < -1f)
            dls = -1f;
        else if (dls > 1.0f)
            dls = 1.0f;
        return (float)Math.acos(dls);
    }

    public Vec1D load(FloatBuffer buf) {
        x = buf.get();
        y = buf.get();
        z = buf.get();
        w = buf.get();
        return this;
    }

    public Vec1D scale(float scale) {
        x *= scale;
        y *= scale;
        z *= scale;
        w *= scale;
        return this;
    }

    public Vec1D store(FloatBuffer buf) {

        buf.put(x);
        buf.put(y);
        buf.put(z);
        buf.put(w);

        return this;
    }

    public String toString() {
        return "Vector4f: " + x + " " + y + " " + z + " " + w;
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

    public void setZ(float z) {
        this.z = z;
    }

    public float getZ() {
        return z;
    }

    public void setW(float w) {
        this.w = w;
    }

    public float getW() {
        return w;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Vec4D other = (Vec4D)obj;

        if (x == other.x && y == other.y && z == other.z && w == other.w) return true;

        return false;
    }
}