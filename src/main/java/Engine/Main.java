package Engine;

import Entities.Camera;
import Entities.Entity;
import Models.RawModel;
import Models.TextureModel;
import Render.DisplayManager;
import Render.Loader;
import Render.MasterRender;
import Shaders.StaticShader;
import Textures.ModelTexture;
import Toolbox.Math.Vec3;
import Toolbox.Necessities.Mouse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;

public class Main {

    public static Loader loader1 = null;
    public static StaticShader shader1 = null;
    public static int FPS = -1;
    public static double previousTime = glfwGetTime();
    public static float VOXEL_SIZE = 1f;
    static List<Entity> entityList = Collections.synchronizedList(new ArrayList<>());
    static Vec3 camPos = new Vec3(0, 0, 0);
    static List<Vec3> usedPos = Collections.synchronizedList(new ArrayList<>());

    static final int WORLD_SIZE = 15;

    public static void main(String[] args){

        DisplayManager.createDisplay();
        Loader loader = new Loader();
        loader1 = loader;
        StaticShader shader = new StaticShader();
        shader1 = shader;
        MasterRender renderer = new MasterRender(shader1);


        float[] vertices = {
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
        */

        int[] indices = {
                0, 1, 3,  3, 1, 2,  // Front face
                4, 5, 7,  7, 5, 6,  // Back face
                8, 9, 11, 11, 9, 10, // Right face
                12, 13, 15, 15, 13, 14, // Left face
                16, 17, 19, 19, 17, 18, // Top face
                20, 21, 23, 23, 21, 22  // Bottom face
        };

        float[] uvs = {
                0, 0,  0, 1,  1, 1,  1, 0,
                0, 0,  0, 1,  1, 1,  1, 0,
                0, 0,  0, 1,  1, 1,  1, 0,
                0, 0,  0, 1,  1, 1,  1, 0,
                0, 0,  0, 1,  1, 1,  1, 0,
                0, 0,  0, 1,  1, 1,  1, 0
        };


        RawModel model = loader.loadToVAO(vertices, indices, uvs);
        ModelTexture texture = new ModelTexture(loader.loadTexture("glass.png"));
        TextureModel textureModel = new TextureModel(model, texture);

        long window = DisplayManager.getWindow();

        Mouse.init();

        Camera camera = new Camera(new Vec3(0, 0, 0), 0, 0, 0);

        new Thread(() -> {

            while(!glfwWindowShouldClose(window)) {
                for (int x = (int) (camPos.x - WORLD_SIZE); x < camPos.x; x++){
                    for (int z = (int) (camPos.z); z < (camPos.z + WORLD_SIZE); z++) {

                        if(!usedPos.contains(new Vec3(x*VOXEL_SIZE,0,z*VOXEL_SIZE))) {

                            entityList.add(new Entity(textureModel, new Vec3(x*VOXEL_SIZE, 0, z*VOXEL_SIZE), 0, 0, 0, VOXEL_SIZE));
                            usedPos.add(new Vec3(x*VOXEL_SIZE,0,z*VOXEL_SIZE));

                        }
                    }
                }

                for (int x = (int) (camPos.x); x < (camPos.x + WORLD_SIZE); x++){
                    for (int z = (int) (camPos.z); z < (camPos.z + WORLD_SIZE); z++) {

                        if(!usedPos.contains(new Vec3(x*VOXEL_SIZE,0,z*VOXEL_SIZE))) {

                            entityList.add(new Entity(textureModel, new Vec3(x*VOXEL_SIZE, 0, z*VOXEL_SIZE), 0, 0, 0, VOXEL_SIZE));
                            usedPos.add(new Vec3(x*VOXEL_SIZE,0,z*VOXEL_SIZE));

                        }
                    }
                }


            }
        }).start();

        new Thread(() -> {

            while(!glfwWindowShouldClose(window)) {
                for (int x = (int) (camPos.x - WORLD_SIZE); x < camPos.x; x++){
                    for (int z = (int) (camPos.z - WORLD_SIZE); z < camPos.z; z++) {

                        if(!usedPos.contains(new Vec3(x*VOXEL_SIZE,0,z*VOXEL_SIZE))) {

                            entityList.add(new Entity(textureModel, new Vec3(x*VOXEL_SIZE, 0, z*VOXEL_SIZE), 0, 0, 0, VOXEL_SIZE));
                            usedPos.add(new Vec3(x*VOXEL_SIZE,0,z*VOXEL_SIZE));

                        }
                    }
                }

                for (int x = (int) (camPos.x); x < (camPos.x + WORLD_SIZE); x++){
                    for (int z = (int) (camPos.z - WORLD_SIZE); z < camPos.z; z++) {

                        if(!usedPos.contains(new Vec3(x*VOXEL_SIZE,0,z*VOXEL_SIZE))) {

                            entityList.add(new Entity(textureModel, new Vec3(x*VOXEL_SIZE, 0, z*VOXEL_SIZE), 0, 0, 0, VOXEL_SIZE));
                            usedPos.add(new Vec3(x*VOXEL_SIZE,0,z*VOXEL_SIZE));

                        }
                    }
                }
            }
        }).start();

        new Thread(() -> {

            while (!glfwWindowShouldClose(window)) {

                for(int i = 0; i < entityList.size(); i++) {

                    int distX = (int) (camPos.x - entityList.get(i).getPosition().x);
                    int distZ = (int) (camPos.z - entityList.get(i).getPosition().z);

                    if (distX < 0) {
                        distX *= -1;
                    }

                    if (distZ < 0) {
                        distZ *= -1;
                    }

                    if(distX > WORLD_SIZE || distZ > WORLD_SIZE) {

                        usedPos.remove(entityList.get(i).getPosition());
                        entityList.remove(i);

                    }
                }
            }
        }).start();


        while (!glfwWindowShouldClose(window)) {

            double currentTime = glfwGetTime();
            FPS++;

            Mouse.update();
            camera.move();

            camPos = camera.getPosition();

            if(currentTime - previousTime >= 1.0) {

                DisplayManager.updateWinName(FPS);
                FPS = 0;
                previousTime = currentTime;
            }

            glfwPollEvents();
            renderer.prepare();

            shader.start();
            shader.loadViewMatrix(camera);

            for (int i = 0; i < entityList.size(); i++) {
                renderer.render(entityList.get(i), shader);
            }

            shader.stop();

            if(glfwGetKey(window, GLFW_KEY_ESCAPE) == GLFW_PRESS){
                DisplayManager.closeDisplay();
            }

            DisplayManager.updateDisplay();

        }

        DisplayManager.closeDisplay();
    }
}