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

import static Models.CubeModel.*;
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

    static final int WORLD_SIZE = 10;

    public static void main(String[] args){

        DisplayManager.createDisplay();
        Loader loader = new Loader();
        loader1 = loader;
        shader1 = new StaticShader();
        MasterRender renderer = new MasterRender();

        RawModel model = loader.loadToVAO(vertices, indices, uvs);
        ModelTexture texture = new ModelTexture(loader.loadTexture("dirt.png"));
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

            for (int i = 0; i < entityList.size(); i++) {

                int distX = (int) (camPos.x - entityList.get(i).getPosition().x);
                int distZ = (int) (camPos.z - entityList.get(i).getPosition().z);

                if (distX < 0) {
                    distX *= -1;
                }

                if (distZ < 0) {
                    distZ *= -1;
                }

                if((distX <= WORLD_SIZE) && (distZ <= WORLD_SIZE)) {

                    renderer.addEntity(entityList.get(i));

                }
            }

            renderer.render(camera);

            if(glfwGetKey(window, GLFW_KEY_ESCAPE) == GLFW_PRESS){
                DisplayManager.closeDisplay();
            }

            DisplayManager.updateDisplay();

        }

        DisplayManager.closeDisplay();
    }
}