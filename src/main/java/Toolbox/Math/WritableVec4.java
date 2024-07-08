package Toolbox.Math;

@SuppressWarnings("unused")
public interface WritableVec4 extends WritableVec3 {

    void setW(float w);

    void set(float x, float y, float z, float w);

}