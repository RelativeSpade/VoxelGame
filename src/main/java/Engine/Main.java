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

import static org.lwjgl.glfw.GLFW.*;

public class Main {

    public static Loader loader1 = null;
    public static StaticShader shader1 = null;
    private static int FPS = -1;
    private static double previousTime = glfwGetTime();

    public static void main(String[] args) {
        DisplayManager.createDisplay();
        initResources();

        long window = DisplayManager.getWindow();
        Mouse.init();
        Camera camera = new Camera(new Vec3(0, 0, 0), 0, 0, 0);
        Entity entity = createEntity();

        gameLoop(window, camera, entity);

        cleanup();
    }

    private static void initResources() {
        loader1 = new Loader();
        shader1 = new StaticShader();
    }

    private static Entity createEntity() {
        float[] vertices = {
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

        int[] indices = {
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

        float[] uvs = {
                0,0,
                0,1,
                1,1,
                1,0,
                0,0,
                0,1,
                1,1,
                1,0,
                0,0,
                0,1,
                1,1,
                1,0,
                0,0,
                0,1,
                1,1,
                1,0,
                0,0,
                0,1,
                1,1,
                1,0,
                0,0,
                0,1,
                1,1,
                1,0
        };

        RawModel model = loader1.loadToVAO(vertices, indices, uvs);
        ModelTexture texture = new ModelTexture(loader1.loadTexture("dirt.png"));
        TextureModel textureModel = new TextureModel(model, texture);
        return new Entity(textureModel, new Vec3(0, 0, -1), 0, 0, 0, 1);
    }

    private static void gameLoop(long window, Camera camera, Entity entity) {
        MasterRender renderer = new MasterRender(shader1);

        while (!glfwWindowShouldClose(window)) {
            double currentTime = glfwGetTime();
            FPS++;

            Mouse.update();
            camera.move();

            if (currentTime - previousTime >= 1.0) {
                DisplayManager.updateWinName(FPS);
                FPS = 0;
                previousTime = currentTime;
            }

            glfwPollEvents();
            renderer.prepare();

            entity.changeRotation(1f, 1f, 0);

            shader1.start();
            shader1.loadViewMatrix(camera);
            renderer.render(entity, shader1);
            shader1.stop();

            if (glfwGetKey(window, GLFW_KEY_ESCAPE) == GLFW_PRESS) {
                DisplayManager.closeDisplay();
            }

            DisplayManager.updateDisplay();
        }
    }

    private static void cleanup() {
        DisplayManager.closeDisplay();
        shader1.cleanUp();
        loader1.cleanUp();
    }
}
