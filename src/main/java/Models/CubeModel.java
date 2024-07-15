package Models;

import Toolbox.Math.*;

public class CubeModel {

    public static Vec3[] PX_POS = {

            new Vec3(0.5f,0.5f,-0.5f),
            new Vec3(0.5f,-0.5f,-0.5f),
            new Vec3(0.5f,-0.5f,0.5f),
            new Vec3(0.5f,-0.5f,0.5f),
            new Vec3(0.5f,0.5f,0.5f),
            new Vec3(0.5f,0.5f,-0.5f)

    };

    public static Vec3[] NX_POS = {

            new Vec3(-0.5f,0.5f,-0.5f),
            new Vec3(-0.5f,-0.5f,-0.5f),
            new Vec3(-0.5f,-0.5f,0.5f),
            new Vec3(-0.5f,-0.5f,0.5f),
            new Vec3(-0.5f,0.5f,0.5f),
            new Vec3(-0.5f,0.5f,-0.5f)

    };

    public static Vec3[] PY_POS = {

            new Vec3(-0.5f,0.5f,0.5f),
            new Vec3(-0.5f,0.5f,-0.5f),
            new Vec3(0.5f,0.5f,-0.5f),
            new Vec3(0.5f,0.5f,-0.5f),
            new Vec3(0.5f,0.5f,0.5f),
            new Vec3(-0.5f,0.5f,0.5f)

    };

    public static Vec3[] NY_POS = {

            new Vec3(-0.5f,-0.5f,0.5f),
            new Vec3(-0.5f,-0.5f,-0.5f),
            new Vec3(0.5f,-0.5f,-0.5f),
            new Vec3(0.5f,-0.5f,-0.5f),
            new Vec3(0.5f,-0.5f,0.5f),
            new Vec3(-0.5f,-0.5f,0.5f)

    };

    public static Vec3[] PZ_POS = {

            new Vec3(-0.5f,0.5f,0.5f),
            new Vec3(-0.5f,-0.5f,0.5f),
            new Vec3(0.5f,-0.5f,0.5f),
            new Vec3(0.5f,-0.5f,0.5f),
            new Vec3(0.5f,0.5f,0.5f),
            new Vec3(-0.5f,0.5f,0.5f)

    };

    public static Vec3[] NZ_POS = {

            new Vec3(-0.5f,0.5f,-0.5f),
            new Vec3(-0.5f,-0.5f,-0.5f),
            new Vec3(0.5f,-0.5f,-0.5f),
            new Vec3(0.5f,-0.5f,-0.5f),
            new Vec3(0.5f,0.5f,-0.5f),
            new Vec3(-0.5f,0.5f,-0.5f)

    };

    public static Vec2[] UV = {

            new Vec2(0.f, 0.f),
            new Vec2(0.f, 1.f),
            new Vec2(1.f, 1.f),
            new Vec2(1.f, 1.f),
            new Vec2(1.f, 0.f),
            new Vec2(0.f, 0.f)

    };

    public static Vec2[] UV_PX = {

            // GRASS
            new Vec2(1.f / 16.f, 0.f),
            new Vec2(1.f / 16.f, 1.f / 16.f),
            new Vec2(2.f / 16.f, 1.f / 16.f),
            new Vec2(2.f / 16.f, 1.f / 16.f),
            new Vec2(2.f / 16.f, 0.f / 16.f),
            new Vec2(1.f / 16.f, 0.f),

            // DIRT
            new Vec2(2.f / 16.f, 0.f),
            new Vec2(2.f / 16.f, 1.f / 16.f),
            new Vec2(3.f / 16.f, 1.f / 16.f),
            new Vec2(3.f / 16.f, 1.f / 16.f),
            new Vec2(3.f / 16.f, 0.f / 16.f),
            new Vec2(2.f / 16.f, 0.f),

            // STONE
            new Vec2(3.f / 16.f, 0.f),
            new Vec2(3.f / 16.f, 1.f / 16.f),
            new Vec2(4.f / 16.f, 1.f / 16.f),
            new Vec2(4.f / 16.f, 1.f / 16.f),
            new Vec2(4.f / 16.f, 0.f / 16.f),
            new Vec2(3.f / 16.f, 0.f),

            // TREEBARK
            new Vec2(4.f / 16.f, 0.f),
            new Vec2(4.f / 16.f, 1.f / 16.f),
            new Vec2(5.f / 16.f, 1.f / 16.f),
            new Vec2(5.f / 16.f, 1.f / 16.f),
            new Vec2(5.f / 16.f, 0.f / 16.f),
            new Vec2(4.f / 16.f, 0.f),

            // TREELEAF
            new Vec2(6.f / 16.f, 0.f),
            new Vec2(6.f / 16.f, 1.f / 16.f),
            new Vec2(7.f / 16.f, 1.f / 16.f),
            new Vec2(7.f / 16.f, 1.f / 16.f),
            new Vec2(7.f / 16.f, 0.f / 16.f),
            new Vec2(6.f / 16.f, 0.f)

    };

    public static Vec2[] UV_NX = {

            // GRASS
            new Vec2(1.f / 16.f, 0.f),
            new Vec2(1.f / 16.f, 1.f / 16.f),
            new Vec2(2.f / 16.f, 1.f / 16.f),
            new Vec2(2.f / 16.f, 1.f / 16.f),
            new Vec2(2.f / 16.f, 0.f / 16.f),
            new Vec2(1.f / 16.f, 0.f),

            // DIRT
            new Vec2(2.f / 16.f, 0.f),
            new Vec2(2.f / 16.f, 1.f / 16.f),
            new Vec2(3.f / 16.f, 1.f / 16.f),
            new Vec2(3.f / 16.f, 1.f / 16.f),
            new Vec2(3.f / 16.f, 0.f / 16.f),
            new Vec2(2.f / 16.f, 0.f),

            // STONE
            new Vec2(3.f / 16.f, 0.f),
            new Vec2(3.f / 16.f, 1.f / 16.f),
            new Vec2(4.f / 16.f, 1.f / 16.f),
            new Vec2(4.f / 16.f, 1.f / 16.f),
            new Vec2(4.f / 16.f, 0.f / 16.f),
            new Vec2(3.f / 16.f, 0.f),

            // TREEBARK
            new Vec2(4.f / 16.f, 0.f),
            new Vec2(4.f / 16.f, 1.f / 16.f),
            new Vec2(5.f / 16.f, 1.f / 16.f),
            new Vec2(5.f / 16.f, 1.f / 16.f),
            new Vec2(5.f / 16.f, 0.f / 16.f),
            new Vec2(4.f / 16.f, 0.f),

            // TREELEAF
            new Vec2(6.f / 16.f, 0.f),
            new Vec2(6.f / 16.f, 1.f / 16.f),
            new Vec2(7.f / 16.f, 1.f / 16.f),
            new Vec2(7.f / 16.f, 1.f / 16.f),
            new Vec2(7.f / 16.f, 0.f / 16.f),
            new Vec2(6.f / 16.f, 0.f)

    };

    public static Vec2[] UV_PY = {

            // GRASS
            new Vec2(0.f, 0.f),
            new Vec2(0.f, 1.f / 16.f),
            new Vec2(1.f / 16.f, 1.f / 16.f),
            new Vec2(1.f / 16.f, 1.f / 16.f),
            new Vec2(1.f / 16.f, 0.f),
            new Vec2(0.f, 0.f),

            // DIRT
            new Vec2(2.f / 16.f, 0.f),
            new Vec2(2.f / 16.f, 1.f / 16.f),
            new Vec2(3.f / 16.f, 1.f / 16.f),
            new Vec2(3.f / 16.f, 1.f / 16.f),
            new Vec2(3.f / 16.f, 0.f / 16.f),
            new Vec2(2.f / 16.f, 0.f),

            // STONE
            new Vec2(3.f / 16.f, 0.f),
            new Vec2(3.f / 16.f, 1.f / 16.f),
            new Vec2(4.f / 16.f, 1.f / 16.f),
            new Vec2(4.f / 16.f, 1.f / 16.f),
            new Vec2(4.f / 16.f, 0.f / 16.f),
            new Vec2(3.f / 16.f, 0.f),

            // TREEBARK
            new Vec2(5.f / 16.f, 0.f),
            new Vec2(5.f / 16.f, 1.f / 16.f),
            new Vec2(6.f / 16.f, 1.f / 16.f),
            new Vec2(6.f / 16.f, 1.f / 16.f),
            new Vec2(6.f / 16.f, 0.f / 16.f),
            new Vec2(5.f / 16.f, 0.f),

            // TREELEAF
            new Vec2(6.f / 16.f, 0.f),
            new Vec2(6.f / 16.f, 1.f / 16.f),
            new Vec2(7.f / 16.f, 1.f / 16.f),
            new Vec2(7.f / 16.f, 1.f / 16.f),
            new Vec2(7.f / 16.f, 0.f / 16.f),
            new Vec2(6.f / 16.f, 0.f)

    };

    public static Vec2[] UV_NY = {

            // GRASS
            new Vec2(2.f / 16.f, 0.f),
            new Vec2(2.f / 16.f, 1.f / 16.f),
            new Vec2(3.f / 16.f, 1.f / 16.f),
            new Vec2(3.f / 16.f, 1.f / 16.f),
            new Vec2(3.f / 16.f, 0.f / 16.f),
            new Vec2(2.f / 16.f, 0.f),

            // DIRT
            new Vec2(2.f / 16.f, 0.f),
            new Vec2(2.f / 16.f, 1.f / 16.f),
            new Vec2(3.f / 16.f, 1.f / 16.f),
            new Vec2(3.f / 16.f, 1.f / 16.f),
            new Vec2(3.f / 16.f, 0.f / 16.f),
            new Vec2(2.f / 16.f, 0.f),

            // STONE
            new Vec2(3.f / 16.f, 0.f),
            new Vec2(3.f / 16.f, 1.f / 16.f),
            new Vec2(4.f / 16.f, 1.f / 16.f),
            new Vec2(4.f / 16.f, 1.f / 16.f),
            new Vec2(4.f / 16.f, 0.f / 16.f),
            new Vec2(3.f / 16.f, 0.f),

            // TREEBARK
            new Vec2(5.f / 16.f, 0.f),
            new Vec2(5.f / 16.f, 1.f / 16.f),
            new Vec2(6.f / 16.f, 1.f / 16.f),
            new Vec2(6.f / 16.f, 1.f / 16.f),
            new Vec2(6.f / 16.f, 0.f / 16.f),
            new Vec2(5.f / 16.f, 0.f),

            // TREELEAF
            new Vec2(6.f / 16.f, 0.f),
            new Vec2(6.f / 16.f, 1.f / 16.f),
            new Vec2(7.f / 16.f, 1.f / 16.f),
            new Vec2(7.f / 16.f, 1.f / 16.f),
            new Vec2(7.f / 16.f, 0.f / 16.f),
            new Vec2(6.f / 16.f, 0.f)

    };

    public static Vec2[] UV_PZ = {

            // GRASS
            new Vec2(1.f / 16.f, 0.f),
            new Vec2(1.f / 16.f, 1.f / 16.f),
            new Vec2(2.f / 16.f, 1.f / 16.f),
            new Vec2(2.f / 16.f, 1.f / 16.f),
            new Vec2(2.f / 16.f, 0.f / 16.f),
            new Vec2(1.f / 16.f, 0.f),

            // DIRT
            new Vec2(2.f / 16.f, 0.f),
            new Vec2(2.f / 16.f, 1.f / 16.f),
            new Vec2(3.f / 16.f, 1.f / 16.f),
            new Vec2(3.f / 16.f, 1.f / 16.f),
            new Vec2(3.f / 16.f, 0.f / 16.f),
            new Vec2(2.f / 16.f, 0.f),

            // STONE
            new Vec2(3.f / 16.f, 0.f),
            new Vec2(3.f / 16.f, 1.f / 16.f),
            new Vec2(4.f / 16.f, 1.f / 16.f),
            new Vec2(4.f / 16.f, 1.f / 16.f),
            new Vec2(4.f / 16.f, 0.f / 16.f),
            new Vec2(3.f / 16.f, 0.f),

            // TREEBARK
            new Vec2(4.f / 16.f, 0.f),
            new Vec2(4.f / 16.f, 1.f / 16.f),
            new Vec2(5.f / 16.f, 1.f / 16.f),
            new Vec2(5.f / 16.f, 1.f / 16.f),
            new Vec2(5.f / 16.f, 0.f / 16.f),
            new Vec2(4.f / 16.f, 0.f),

            // TREELEAF
            new Vec2(6.f / 16.f, 0.f),
            new Vec2(6.f / 16.f, 1.f / 16.f),
            new Vec2(7.f / 16.f, 1.f / 16.f),
            new Vec2(7.f / 16.f, 1.f / 16.f),
            new Vec2(7.f / 16.f, 0.f / 16.f),
            new Vec2(6.f / 16.f, 0.f)

    };

    public static Vec2[] UV_NZ = {

            // GRASS
            new Vec2(1.f / 16.f, 0.f),
            new Vec2(1.f / 16.f, 1.f / 16.f),
            new Vec2(2.f / 16.f, 1.f / 16.f),
            new Vec2(2.f / 16.f, 1.f / 16.f),
            new Vec2(2.f / 16.f, 0.f / 16.f),
            new Vec2(1.f / 16.f, 0.f),

            // DIRT
            new Vec2(2.f / 16.f, 0.f),
            new Vec2(2.f / 16.f, 1.f / 16.f),
            new Vec2(3.f / 16.f, 1.f / 16.f),
            new Vec2(3.f / 16.f, 1.f / 16.f),
            new Vec2(3.f / 16.f, 0.f / 16.f),
            new Vec2(2.f / 16.f, 0.f),

            // STONE
            new Vec2(3.f / 16.f, 0.f),
            new Vec2(3.f / 16.f, 1.f / 16.f),
            new Vec2(4.f / 16.f, 1.f / 16.f),
            new Vec2(4.f / 16.f, 1.f / 16.f),
            new Vec2(4.f / 16.f, 0.f / 16.f),
            new Vec2(3.f / 16.f, 0.f),

            // TREEBARK
            new Vec2(4.f / 16.f, 0.f),
            new Vec2(4.f / 16.f, 1.f / 16.f),
            new Vec2(5.f / 16.f, 1.f / 16.f),
            new Vec2(5.f / 16.f, 1.f / 16.f),
            new Vec2(5.f / 16.f, 0.f / 16.f),
            new Vec2(4.f / 16.f, 0.f),

            // TREELEAF
            new Vec2(6.f / 16.f, 0.f),
            new Vec2(6.f / 16.f, 1.f / 16.f),
            new Vec2(7.f / 16.f, 1.f / 16.f),
            new Vec2(7.f / 16.f, 1.f / 16.f),
            new Vec2(7.f / 16.f, 0.f / 16.f),
            new Vec2(6.f / 16.f, 0.f)

    };

    public static Vec3[] NORMALS = {

            new Vec3(0.f, 0.f, 0.f),
            new Vec3(0.f, 0.f, 0.f),
            new Vec3(0.f, 0.f, 0.f),
            new Vec3(0.f, 0.f, 0.f),
            new Vec3(0.f, 0.f, 0.f),
            new Vec3(0.f, 0.f, 0.f)

    };

    public static float[] vertices = {

            -0.5f,0.5f,-0.5f,
            -0.5f,-0.5f,-0.5f,
            0.5f,-0.5f,-0.5f,
            0.5f,0.5f,-0.5f,

            -0.5f,0.5f,0.5f,
            -0.5f,-0.5f,0.5f,
            0.5f,-0.5f,0.5f,
            0.5f,0.5f,0.5f,

            0.5f,0.5f,-0.5f,
            0.5f,-0.5f,-0.5f,
            0.5f,-0.5f,0.5f,
            0.5f,0.5f,0.5f,

            -0.5f,0.5f,-0.5f,
            -0.5f,-0.5f,-0.5f,
            -0.5f,-0.5f,0.5f,
            -0.5f,0.5f,0.5f,

            -0.5f,0.5f,0.5f,
            -0.5f,0.5f,-0.5f,
            0.5f,0.5f,-0.5f,
            0.5f,0.5f,0.5f,

            -0.5f,-0.5f,0.5f,
            -0.5f,-0.5f,-0.5f,
            0.5f,-0.5f,-0.5f,
            0.5f,-0.5f,0.5f

    };

    public static int[] indices = {

            0,1,3,
            3,1,2,
            4,5,7,
            7,5,6,
            8,9,11,
            11,9,10,
            12,13,15,
            15,13,14,
            16,17,19,
            19,17,18,
            20,21,23,
            23,21,22

    };

    public static float[] uv = {

            0, 0,
            0, 1,
            1, 1,
            1, 0,
            0, 0,
            0, 1,
            1, 1,
            1, 0,
            0, 0,
            0, 1,
            1, 1,
            1, 0,
            0, 0,
            0, 1,
            1, 1,
            1, 0,
            0, 0,
            0, 1,
            1, 1,
            1, 0,
            0, 0,
            0, 1,
            1, 1,
            1, 0

    };

}
