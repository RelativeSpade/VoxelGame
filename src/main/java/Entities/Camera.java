package Entities;

import Toolbox.Math.Vec3;
import Toolbox.Necessities.Mouse;

import static Render.DisplayManager.getWindow;
import static org.lwjgl.glfw.GLFW.*;

public class Camera {

    Vec3 position;
    float rX;
    float rY;
    float rZ;
    float speed = 0.1f;

    public Camera(Vec3 position, float rX, float rY, float rZ) {

        this.position = position;
        this.rX = rX;
        this.rY = rY;
        this.rZ = rZ;
    }

    public void move() {

        if(glfwGetKey(getWindow(), GLFW_KEY_W) == GLFW_PRESS) {
            position.z += -speed;
        }

        if(glfwGetKey(getWindow(), GLFW_KEY_S) == GLFW_PRESS) {
            position.z += speed;
        }

        rX += Mouse.getDY();
        rY += Mouse.getDX();
    }

    public Vec3 getPosition() {
        return position;
    }

    public float getRX() {
        return rX;
    }

    public float getRY() {
        return rY;
    }

    public float getRZ() {
        return rZ;
    }
}
