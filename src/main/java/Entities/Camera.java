package Entities;

import Toolbox.Math.Vec3;
import Toolbox.Necessities.Mouse;

import static Render.DisplayManager.getWindow;
import static Toolbox.Math.Clamp.clamp;
import static org.lwjgl.glfw.GLFW.*;

public class Camera {

    Vec3 position;
    float rX;
    float rY;
    float rZ;
    float speed = 0.1f;
    float moveAt = 0;
    float turnSpeed  = 0.75f;
    float maxTurnSpeed = 5f;

    public Camera(Vec3 position, float rX, float rY, float rZ) {

        this.position = position;
        this.rX = rX;
        this.rY = rY;
        this.rZ = rZ;
    }

    public void move() {
        if (glfwGetKey(getWindow(), GLFW_KEY_W) == GLFW_PRESS) {
            moveAt = -speed;
        }
        else if (glfwGetKey(getWindow(), GLFW_KEY_S) == GLFW_PRESS) {
            moveAt = speed;
        } else {
            moveAt = 0;
        }

        float dx = (float) -(moveAt * Math.sin(Math.toRadians(rY)));
        float dy = (float) (moveAt * Math.sin(Math.toRadians(rX)));
        float dz = (float) (moveAt * Math.cos(Math.toRadians(rY)));

        position.x += dx;
        position.y += dy;
        position.z += dz;

        float deltaX = Mouse.getDX() * turnSpeed;
        float deltaY = Mouse.getDY() * turnSpeed;

        deltaX = clamp(deltaX, -maxTurnSpeed, maxTurnSpeed);
        deltaY = clamp(deltaY, -maxTurnSpeed, maxTurnSpeed);

        System.out.println("Mouse DX: " + deltaX + ", Mouse DY: " + deltaY);

        rX += deltaY;
        rY += deltaX;
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
