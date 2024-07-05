package org.spade.Render;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static java.sql.Types.NULL;
import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

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

    public static void updateDisplay() {}

    public static void closeDisplay() {}

}
