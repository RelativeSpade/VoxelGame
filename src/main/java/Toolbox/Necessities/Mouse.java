package Toolbox.Necessities;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;

public class Mouse {
    private static double lastX;
    private static double lastY;
    private static double dx;
    private static double dy;
    private static long window;

    public static void init(long window) {
        Mouse.window = window;
        GLFW.glfwSetInputMode(window, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_DISABLED);

        GLFW.glfwSetCursorPosCallback(window, new GLFWCursorPosCallback() {
            @Override
            public void invoke(long window, double xpos, double ypos) {
                if (lastX == 0 && lastY == 0) {
                    lastX = xpos;
                    lastY = ypos;
                }
                dx = xpos - lastX;
                dy = ypos - lastY;
                lastX = xpos;
                lastY = ypos;
            }
        });
    }

    public static double getDX() {
        double delta = dx;
        dx = 0;
        return delta;
    }

    public static double getDY() {
        double delta = dy;
        dy = 0;
        return delta;
    }

    public static void resetDeltas() {
        dx = 0;
        dy = 0;
    }
}
