package Engine;

import Models.RawModel;
import Render.DisplayManager;
import Render.Loader;
import Render.MasterRender;
import Shaders.ShaderProgram;
import Shaders.StaticShader;

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

        RawModel model = loader.loadToVAO(vertices, indices);

        long window = DisplayManager.getWindow();

        while (!glfwWindowShouldClose(window)) {

            glfwPollEvents();
            renderer.prepare();
            shader.start();
            renderer.render(model);
            shader.stop();

            if(glfwGetKey(window, GLFW_KEY_ESCAPE) == GLFW_PRESS){
                DisplayManager.closeDisplay();
            }

            DisplayManager.updateDisplay();

        }

        DisplayManager.closeDisplay();
    }
}
