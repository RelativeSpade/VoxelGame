package Toolbox.Math;

import Entities.Camera;

public class Transformation {

    public static Matrix4 createTransformationMatrix(Vec3 translation, float rX, float rY, float rZ, float scale) {

        Matrix4 matrix = new Matrix4();
        matrix.setIdentity();

        Matrix4.translate(translation, matrix, matrix);
        Matrix4.rotate((float) Math.toRadians(rX), new Vec3(1, 0, 0), matrix, matrix);
        Matrix4.rotate((float) Math.toRadians(rY), new Vec3(0, 1, 0), matrix, matrix);
        Matrix4.rotate((float) Math.toRadians(rZ), new Vec3(0, 0, 1), matrix, matrix);
        Matrix4.scale(new Vec3(scale, scale, scale), matrix, matrix);

        return matrix;

    }

    public static Matrix4 createViewMatrix(Camera camera) {

        Matrix4 matrix = new Matrix4();
        matrix.setIdentity();

        Matrix4.rotate((float) Math.toRadians(camera.getRX()), new Vec3(1, 0, 0), matrix, matrix);
        Matrix4.rotate((float) Math.toRadians(camera.getRY()), new Vec3(0, 1, 0), matrix, matrix);
        Matrix4.rotate((float) Math.toRadians(camera.getRZ()), new Vec3(0, 0, 1), matrix, matrix);
        Matrix4.translate(new Vec3(-camera.getPosition().x, -camera.getPosition().y, -camera.getPosition().z), matrix, matrix);

        return matrix;

    }

}
