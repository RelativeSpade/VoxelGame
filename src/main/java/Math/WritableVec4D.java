package Math;

@SuppressWarnings("unused")
public interface WritableVec4D extends WritableVec3D {

    void setW(float w);

    void set(float x, float y, float z, float w);

}