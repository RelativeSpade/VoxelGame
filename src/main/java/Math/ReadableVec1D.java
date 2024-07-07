package Math;
import java.nio.FloatBuffer;

@SuppressWarnings("unused")
public interface ReadableVec1D {
    float length();
    float lengthSquared();
    Vec1D store(FloatBuffer buf);
}