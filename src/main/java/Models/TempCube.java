package Models;

public class TempCube {
    //TODO: 07/12/24 Get this reduced vertice model working...;
    public static float[] vertices = {
            // Vertex positions
            -0.5f,  0.5f,  0.5f,  // 0 - Front top left
            -0.5f, -0.5f,  0.5f,  // 1 - Front bottom left
            0.5f, -0.5f,  0.5f,  // 2 - Front bottom right
            0.5f,  0.5f,  0.5f,  // 3 - Front top right

            -0.5f,  0.5f, -0.5f,  // 4 - Back top left
            -0.5f, -0.5f, -0.5f,  // 5 - Back bottom left
            0.5f, -0.5f, -0.5f,  // 6 - Back bottom right
            0.5f,  0.5f, -0.5f   // 7 - Back top right
    };


            /*
        Front face:
        (-0.5, 0.5) (0) ┌────────────┐ (0.5, 0.5) (3)
                        │            │
                        │            │
                        │            │
                        │            │
        (-0.5,-0.5) (1) └────────────┘ (0.5,-0.5) (2)

        Back face:
        (-0.5, 0.5) (4) ┌────────────┐ (0.5, 0.5) (7)
                        │            │
                        │            │
                        │            │
                        │            │
        (-0.5,-0.5) (5) └────────────┘ (0.5,-0.5) (6)

        Right face:
        (0.5, 0.5) (3)  ┌────────────┐ (0.5, 0.5) (7)
                        │            │
                        │            │
                        │            │
                        │            │
        (0.5,-0.5) (2)  └────────────┘ (0.5,-0.5) (6)

        Left face:
        (-0.5, 0.5) (0) ┌────────────┐ (0.5, 0.5) (4)
                        │            │
                        │            │
                        │            │
                        │            │
        (-0.5,-0.5) (1) └────────────┘ (0.5,-0.5) (5)

        Top face:
        (-0.5, 0.5) (0) ┌────────────┐ (0.5, 0.5) (3)
                        │            │
                        │            │
                        │            │
                        │            │
        (-0.5,-0.5) (4) └────────────┘ (0.5,-0.5) (7)

        Bottom face:
        (-0.5,-0.5) (5) ┌────────────┐ (0.5,-0.5) (6)
                        │            │
                        │            │
                        │            │
                        │            │
        (-0.5,-0.5) (1) └────────────┘ (0.5,-0.5) (2)

        https://www.desmos.com/3d/vp6mbo4xlf
        */

    public static int[] indices = {
            // Front face
            0, 1, 2,
            2, 3, 0,
            // Back face
            4, 5, 6,
            6, 7, 4,
            // Right face
            3, 2, 6,
            6, 7, 3,
            // Left face
            0, 1, 5,
            5, 4, 0,
            // Top face
            4, 0, 3,
            3, 7, 4,
            // Bottom face
            1, 5, 6,
            6, 2, 1
    };



    public static float[] uvs = {

            0, 0,  0, 1,
            1, 1,  1, 0,

            0, 0,  0, 1,
            1, 1,  1, 0,

            0, 0,  0, 1,
            1, 1,  1, 0,

            0, 0,  0, 1,
            1, 1,  1, 0,

            0, 0,  0, 1,
            1, 1,  1, 0,

            0, 0,  0, 1,
            1, 1,  1, 0

    };

}
