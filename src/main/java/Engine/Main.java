package Engine;

import Models.RawModel;
import Render.DisplayManager;
import Render.Loader;
import Render.MasterRender;

import static org.lwjgl.glfw.GLFW.*;

public class Main {

    public static Loader loader1 = null;

    public static void main(String[] args){

        DisplayManager.createDisplay();

        MasterRender renderer = new MasterRender();
        Loader loader = new Loader();
        loader1 = loader;

        float[] vertices = {
                // First triangle
                -0.5f, 0.5f, 0,  // Top-left corner
                -0.5f, -0.5f, 0, // Bottom-left corner
                0.5f, -0.5f, 0,  // Bottom-right corner

                // Second triangle
                0.5f, -0.5f, 0,  // Bottom-right corner
                0.5f, 0.5f, 0,   // Top-right corner
                -0.5f, 0.5f, 0   // Top-left corner
        };

            /*
            (-0.5, 0.5) ┌────────────┐ (0.5, 0.5)
                        │            │
                        │            │
                        │            │
                        │            │
            (-0.5,-0.5) └────────────┘ (0.5,-0.5)
            */

        RawModel model = loader.loadToVAO(vertices);

        long window = DisplayManager.getWindow();

        while (!glfwWindowShouldClose(window)) {

            glfwPollEvents();
            renderer.prepare();

            renderer.render(model);

            if(glfwGetKey(window, GLFW_KEY_ESCAPE) == GLFW_PRESS){
                DisplayManager.closeDisplay();
            }

            DisplayManager.updateDisplay();

        }

        DisplayManager.closeDisplay();
    }
}
