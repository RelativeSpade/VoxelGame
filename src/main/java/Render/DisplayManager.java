package Render;

import Engine.Main;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import java.nio.IntBuffer;

import static java.sql.Types.NULL;
import static org.lwjgl.glfw.GLFW.*;

@SuppressWarnings("unused")
public class DisplayManager {
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    public static String NAME = "Spades Voxel Project";
    private static long window;

    public static void createDisplay() {
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        window = glfwCreateWindow(WIDTH, HEIGHT, NAME, NULL, NULL);
        if (window == NULL) {
            glfwTerminate();
            throw new RuntimeException("Failed to create the GLFW window");
        }

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);

        glfwShowWindow(window);

        IntBuffer width = BufferUtils.createIntBuffer(1);
        IntBuffer height = BufferUtils.createIntBuffer(1);
        glfwGetWindowSize(window, width, height);

        GL.createCapabilities();

        GL11.glViewport(0, 0, width.get(0), height.get(0));
        glfwFocusWindow(window);
    }

    public static void updateDisplay() {

        glfwSwapInterval(1); // Cap fps to screen refresh rate
        glfwSwapBuffers(window); //Update the screen

    }

    public static void closeDisplay() {
        Main.loader1.cleanUp();

        glfwTerminate();
        System.exit(0);

    }

    public static long getWindow(){
        return window;
    }
}
