package Engine;

import Entities.Entity;
import Models.RawModel;
import Models.TextureModel;
import Render.DisplayManager;
import Render.Loader;
import Render.MasterRender;
import Shaders.StaticShader;
import Textures.ModelTexture;
import Toolbox.Math.Vec3;

import static org.lwjgl.glfw.GLFW.*;

public class Main {

    public static Loader loader1 = null;
    public static StaticShader shader1 = null;

    public static void main(String[] args){

        DisplayManager.createDisplay();

        MasterRender renderer = new MasterRender();
        Loader loader = new Loader();
        loader1 = loader;
        StaticShader shader = new StaticShader();
        shader1 = shader;

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
        Entity entity = new Entity(textureModel, new Vec3(0, 0, 0), 0, 0, 0, 1);

        long window = DisplayManager.getWindow();

        while (!glfwWindowShouldClose(window)) {

            glfwPollEvents();
            renderer.prepare();

            entity.changeScale(-0.001f);
            entity.changeRotation(0, 0, 0.1f);

            shader.start();
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
