package Toolbox.Necessities;

import Render.DisplayManager;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;

public class Mouse {
    private static double lastX;
    private static double lastY;
    private static float dx;
    private static float dy;

    public static void init() {
        long window = DisplayManager.getWindow();
        GLFW.glfwSetInputMode(window, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_DISABLED);

        GLFW.glfwSetCursorPosCallback(window, new GLFWCursorPosCallback() {
            @Override
            public void invoke(long window, double xpos, double ypos) {
                if (lastX == 0 && lastY == 0) {
                    lastX = xpos;
                    lastY = ypos;
                }

                dx = (float) (xpos - lastX);
                dy = (float) (ypos - lastY);
                lastX = xpos;
                lastY = ypos;
            }
        });
    }

    public static float getDX() {
        float delta = dx;
        dx = 0;
        return delta;
    }

    public static float getDY() {
        float delta = dy;
        dy = 0;
        return delta;
    }
}
