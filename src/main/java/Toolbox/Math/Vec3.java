package Toolbox.Math;

import java.io.Serial;
import java.io.Serializable;
import java.nio.FloatBuffer;

@SuppressWarnings("unused")
public class Vec3 extends Vec1 implements Serializable, ReadableVec3, WritableVec3 {

    @Serial
    private static final long serialVersionUID = 1L;

    public float x, y, z;

    public Vec3() {
        super();
    }

    public Vec3(ReadableVec3 src) {
        set(src);
    }

    public Vec3(float x, float y, float z) {
        set(x, y, z);
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

    public Vec3 set(ReadableVec3 src) {
        x = src.getX();
        y = src.getY();
        z = src.getZ();
        return this;
    }

    public float lengthSquared() {
        return x * x + y * y + z * z;
    }

    public Vec3 translate(float x, float y, float z) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }

    public static Vec3 add(Vec3 left, Vec3 right, Vec3 dest) {
        if (dest == null)
            return new Vec3(left.x + right.x, left.y + right.y, left.z + right.z);
        else {
            dest.set(left.x + right.x, left.y + right.y, left.z + right.z);
            return dest;
        }
    }

    public static Vec3 sub(Vec3 left, Vec3 right, Vec3 dest) {
        if (dest == null)
            return new Vec3(left.x - right.x, left.y - right.y, left.z - right.z);
        else {
            dest.set(left.x - right.x, left.y - right.y, left.z - right.z);
            return dest;
        }
    }

    public static Vec3 cross(
            Vec3 left,
            Vec3 right,
            Vec3 dest)
    {

        if (dest == null)
            dest = new Vec3();

        dest.set(
                left.y * right.z - left.z * right.y,
                right.x * left.z - right.z * left.x,
                left.x * right.y - left.y * right.x
        );

        return dest;
    }

    public Vec1 negate() {
        x = -x;
        y = -y;
        z = -z;
        return this;
    }

    public Vec3 negate(Vec3 dest) {
        if (dest == null)
            dest = new Vec3();
        dest.x = -x;
        dest.y = -y;
        dest.z = -z;
        return dest;
    }

    public Vec3 normalise(Vec3 dest) {
        float l = length();

        if (dest == null)
            dest = new Vec3(x / l, y / l, z / l);
        else
            dest.set(x / l, y / l, z / l);

        return dest;
    }

    public static float dot(Vec3 left, Vec3 right) {
        return left.x * right.x + left.y * right.y + left.z * right.z;
    }

    public static float angle(Vec3 a, Vec3 b) {
        float dls = dot(a, b) / (a.length() * b.length());
        if (dls < -1f)
            dls = -1f;
        else if (dls > 1.0f)
            dls = 1.0f;
        return (float)Math.acos(dls);
    }

    public Vec1 load(FloatBuffer buf) {
        x = buf.get();
        y = buf.get();
        z = buf.get();
        return this;
    }

    public Vec1 scale(float scale) {

        x *= scale;
        y *= scale;
        z *= scale;

        return this;

    }

    public Vec3 store(FloatBuffer buf) {

        buf.put(x);
        buf.put(y);
        buf.put(z);

        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(64);

        sb.append("Vector3f[");
        sb.append(x);
        sb.append(", ");
        sb.append(y);
        sb.append(", ");
        sb.append(z);
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

    public void setZ(float z) {
        this.z = z;
    }

    public float getZ() {
        return z;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Vec3 other = (Vec3)obj;

        if (x == other.x && y == other.y && z == other.z) return true;

        return false;
    }
}