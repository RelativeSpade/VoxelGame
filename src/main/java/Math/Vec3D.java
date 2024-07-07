package Math;

import java.io.Serial;
import java.io.Serializable;
import java.nio.FloatBuffer;

@SuppressWarnings("unused")
public class Vec3D extends Vec1D implements Serializable, ReadableVec3D, WritableVec3D {

    @Serial
    private static final long serialVersionUID = 1L;

    public float x, y, z;

    public Vec3D() {
        super();
    }

    public Vec3D(ReadableVec3D src) {
        set(src);
    }

    public Vec3D(float x, float y, float z) {
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

    public Vec3D set(ReadableVec3D src) {
        x = src.getX();
        y = src.getY();
        z = src.getZ();
        return this;
    }

    public float lengthSquared() {
        return x * x + y * y + z * z;
    }

    public Vec3D translate(float x, float y, float z) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }

    public static Vec3D add(Vec3D left, Vec3D right, Vec3D dest) {
        if (dest == null)
            return new Vec3D(left.x + right.x, left.y + right.y, left.z + right.z);
        else {
            dest.set(left.x + right.x, left.y + right.y, left.z + right.z);
            return dest;
        }
    }

    public static Vec3D sub(Vec3D left, Vec3D right, Vec3D dest) {
        if (dest == null)
            return new Vec3D(left.x - right.x, left.y - right.y, left.z - right.z);
        else {
            dest.set(left.x - right.x, left.y - right.y, left.z - right.z);
            return dest;
        }
    }

    public static Vec3D cross(
            Vec3D left,
            Vec3D right,
            Vec3D dest)
    {

        if (dest == null)
            dest = new Vec3D();

        dest.set(
                left.y * right.z - left.z * right.y,
                right.x * left.z - right.z * left.x,
                left.x * right.y - left.y * right.x
        );

        return dest;
    }

    public Vec1D negate() {
        x = -x;
        y = -y;
        z = -z;
        return this;
    }

    public Vec3D negate(Vec3D dest) {
        if (dest == null)
            dest = new Vec3D();
        dest.x = -x;
        dest.y = -y;
        dest.z = -z;
        return dest;
    }

    public Vec3D normalise(Vec3D dest) {
        float l = length();

        if (dest == null)
            dest = new Vec3D(x / l, y / l, z / l);
        else
            dest.set(x / l, y / l, z / l);

        return dest;
    }

    public static float dot(Vec3D left, Vec3D right) {
        return left.x * right.x + left.y * right.y + left.z * right.z;
    }

    public static float angle(Vec3D a, Vec3D b) {
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
        return this;
    }

    public Vec1D scale(float scale) {

        x *= scale;
        y *= scale;
        z *= scale;

        return this;

    }

    public Vec3D store(FloatBuffer buf) {

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
        Vec3D other = (Vec3D)obj;

        if (x == other.x && y == other.y && z == other.z) return true;

        return false;
    }
}