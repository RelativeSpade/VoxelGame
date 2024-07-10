package Render;

import Engine.Main;
import Toolbox.Necessities.Image;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWImage;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.nio.IntBuffer;

import static java.sql.Types.NULL;
import static org.lwjgl.glfw.GLFW.*;

@SuppressWarnings("unused")
public class DisplayManager {
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    public static String NAME = "Spades Voxel Project";
    private static long window;
    private static final Image icon = Image.load_image("C:/Users/Spade/Documents/Voxel/src/main/resources/textures/dirt.png");

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

        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        assert vidmode != null;
        glfwSetWindowPos(
                window,
                (vidmode.width() - WIDTH) / 2,
                (vidmode.height() - HEIGHT) / 2
        );

        glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_DISABLED);
        glfwMakeContextCurrent(window);

        glfwShowWindow(window);

        IntBuffer width = BufferUtils.createIntBuffer(1);
        IntBuffer height = BufferUtils.createIntBuffer(1);
        glfwGetWindowSize(window, width, height);

        GLFWImage image = GLFWImage.malloc(); GLFWImage.Buffer imagebf = GLFWImage.malloc(1);
        image.set(icon.get_width(), icon.get_heigh(), icon.get_image());
        imagebf.put(0, image);
        glfwSetWindowIcon(window, imagebf);


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
        Main.shader1.cleanUp();

        glfwTerminate();
        System.exit(0);

    }

    public static float getAspectRatio() {
        int[] width = new int[1];
        int[] height = new int[1];
        glfwGetWindowSize(window, width, height);
        return (float) width[0] / (float) height[0];
    }

    public static long getWindow(){
        return window;
    }

    public static void updateWinName(int fps) {

        glfwSetWindowTitle(window, NAME + " FPS: " + fps);

    }
}
