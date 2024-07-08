package Toolbox.Math;

public class Transformation {

    public static Matrix4D createTransformationMatrix(Vec3D translation, float rX, float rY, float rZ, float scale) {

        Matrix4D matrix = new Matrix4D();
        matrix.setIdentity();

        Matrix4D.translate(translation, matrix, matrix);
        Matrix4D.rotate((float) Math.toRadians(rX), new Vec3D(1, 0, 0), matrix, matrix);
        Matrix4D.rotate((float) Math.toRadians(rY), new Vec3D(0, 1, 0), matrix, matrix);
        Matrix4D.rotate((float) Math.toRadians(rZ), new Vec3D(0, 0, 1), matrix, matrix);
        Matrix4D.scale(new Vec3D(scale, scale, scale), matrix, matrix);

        return matrix;

    }

}
