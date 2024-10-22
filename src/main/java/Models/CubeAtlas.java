package Models;

public class CubeAtlas {

        public static float[] vertices = {

                -0.5f, 0.5f, -0.5f,  // 0
                -0.5f, -0.5f, -0.5f, // 1
                0.5f, -0.5f, -0.5f,  // 2
                0.5f, 0.5f, -0.5f,   // 3

                -0.5f, 0.5f, 0.5f,   // 4
                -0.5f, -0.5f, 0.5f,  // 5
                0.5f, -0.5f, 0.5f,   // 6
                0.5f, 0.5f, 0.5f,    // 7

                0.5f, 0.5f, -0.5f,   // 8 (Same as 3)
                0.5f, -0.5f, -0.5f,  // 9 (Same as 2)
                0.5f, -0.5f, 0.5f,   // 10 (Same as 6)
                0.5f, 0.5f, 0.5f,    // 11 (Same as 7)

                -0.5f, 0.5f, -0.5f,  // 12 (Same as 0)
                -0.5f, -0.5f, -0.5f, // 13 (Same as 1)
                -0.5f, -0.5f, 0.5f,  // 14 (Same as 5)
                -0.5f, 0.5f, 0.5f,   // 15 (Same as 4)

                -0.5f, 0.5f, 0.5f,   // 16 (Same as 4)
                -0.5f, 0.5f, -0.5f,  // 17 (Same as 0)
                0.5f, 0.5f, -0.5f,   // 18 (Same as 3)
                0.5f, 0.5f, 0.5f,    // 19 (Same as 7)

                -0.5f, -0.5f, 0.5f,  // 20 (Same as 5)
                -0.5f, -0.5f, -0.5f, // 21 (Same as 1)
                0.5f, -0.5f, -0.5f,  // 22 (Same as 2)
                0.5f, -0.5f, 0.5f    // 23 (Same as 6)

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
                8, 9, 10,
                10, 11, 8,

                // Left face
                12, 13, 14,
                14, 15, 12,

                // Top face
                16, 17, 18,
                18, 19, 16,

                // Bottom face
                20, 21, 22,
                22, 23, 20
        };


        public static float[] uvs = {

                1.01f/3f, 1.01f/3f,
                1.01f/3f, 1.99f/3f,
                1.99f/3f, 1.99f/3f,
                1.99f/3f, 1.01f/3f,

                1.01f/3f, 1.01f/3f,
                1.01f/3f, 1.99f/3f,
                1.99f/3f, 1.99f/3f,
                1.99f/3f, 1.01f/3f,

                1.01f/3f, 1.01f/3f,
                1.01f/3f, 1.99f/3f,
                1.99f/3f, 1.99f/3f,
                1.99f/3f, 1.01f/3f,

                1.01f/3f, 1.01f/3f,
                1.01f/3f, 1.99f/3f,
                1.99f/3f, 1.99f/3f,
                1.99f/3f, 1.01f/3f,

                1.01f / 3f, 2.01f / 3f,
                1.01f / 3f, 0.99f,
                1.99f / 3f, 0.99f,
                1.99f / 3f, 2.01f / 3f,

                0.01f, 1.01f / 3f,
                0.01f, 1.99f / 3f,
                0.99f / 3f, 1.99f /3f,
                0.99f / 3f, 1.01f / 3f

        };
}
