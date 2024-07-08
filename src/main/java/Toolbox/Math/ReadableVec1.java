package Toolbox.Math;
import java.nio.FloatBuffer;

@SuppressWarnings("unused")
public interface ReadableVec1 {
    float length();
    float lengthSquared();
    Vec1 store(FloatBuffer buf);
}