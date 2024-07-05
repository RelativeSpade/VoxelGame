package Engine;

import Render.DisplayManager;
import Render.MasterRender;

import static org.lwjgl.glfw.GLFW.*;

public class Main {

    public static void main(String[] args){

        DisplayManager.createDisplay();

        MasterRender renderer = new MasterRender();

        long window = DisplayManager.getWindow();

        while (!glfwWindowShouldClose(window)) {

            glfwPollEvents();
            renderer.prepare();
            DisplayManager.updateDisplay();

        }

    }

}
