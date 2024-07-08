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
    public static int FPS = -1;
    public static double previousTime = glfwGetTime();

    public static void main(String[] args){

        DisplayManager.createDisplay();
        Loader loader = new Loader();
        loader1 = loader;
        StaticShader shader = new StaticShader();
        shader1 = shader;
        MasterRender renderer = new MasterRender(shader1);


        float[] vertices = {
                -0.5f, 0.5f, 0,  // Top-left corner (0)
                -0.5f, -0.5f, 0, // Bottom-left corner (1)
                0.5f, -0.5f, 0,  // Bottom-right corner (2)
                0.5f, 0.5f, 0,   // Top-right corner (3)
        };

            /*
            (-0.5, 0.5) (0) ┌────────────┐ (3) (0.5, 0.5)
                            │            │
                            │            │
                            │            │
                            │            │
            (-0.5,-0.5) (1) └────────────┘ (2) (0.5,-0.5)
            */

        int[] indices = {
                0, 1, 2,
                2, 3, 0
        };

        float[] uvs = {
                0, 0,
                0, 1,
                1, 1,
                1, 0,
        };

        RawModel model = loader.loadToVAO(vertices, indices, uvs);
        ModelTexture texture = new ModelTexture(loader.loadTexture("dirt.png"));
        TextureModel textureModel = new TextureModel(model, texture);
        Entity entity = new Entity(textureModel, new Vec3(0, 0, -1), 0, 0, 0, 1);

        long window = DisplayManager.getWindow();

        Mouse.init();

        Camera camera = new Camera(new Vec3(0, 0, 0), 0, 0, 0);

        while (!glfwWindowShouldClose(window)) {

            double currentTime = glfwGetTime();
            FPS++;

            camera.move();

            if(currentTime - previousTime >= 1.0) {

                DisplayManager.updateWinName(FPS);
                FPS = 0;
                previousTime = currentTime;
            }

            glfwPollEvents();
            renderer.prepare();

            shader.start();
            shader.loadViewMatrix(camera);
            renderer.render(entity, shader);
            shader.stop();

            if(glfwGetKey(window, GLFW_KEY_ESCAPE) == GLFW_PRESS){
                DisplayManager.closeDisplay();
            }

            DisplayManager.updateDisplay();

        }

        DisplayManager.closeDisplay();
    }
}
