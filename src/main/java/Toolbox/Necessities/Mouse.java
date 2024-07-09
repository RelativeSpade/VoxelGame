package Toolbox.Necessities;

import Render.DisplayManager;

import static org.lwjgl.glfw.GLFW.*;

public class Mouse {
    private static double lastX = -1;
    private static double lastY = -1;
    private static float dx;
    private static float dy;

    public static void init() {
        long window = DisplayManager.getWindow();

        if (glfwRawMouseMotionSupported()) {
            glfwSetInputMode(window, GLFW_RAW_MOUSE_MOTION, GLFW_TRUE);
        }
        glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_DISABLED);
    }

    public static void update() {
        long window = DisplayManager.getWindow();
        double xpos, ypos;
        double[] xposArr = new double[1];
        double[] yposArr = new double[1];
        glfwGetCursorPos(window, xposArr, yposArr);
        xpos = xposArr[0];
        ypos = yposArr[0];

        if (lastX == -1 && lastY == -1) {
            lastX = xpos;
            lastY = ypos;
        }

        dx = (float) (xpos - lastX);
        dy = (float) (ypos - lastY);
        lastX = xpos;
        lastY = ypos;
    }

    public static float getDX() {
        return dx;
    }

    public static float getDY() {
        return dy;
    }
}
