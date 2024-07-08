package Entities;

import Models.TextureModel;
import Toolbox.Math.Vec3D;

@SuppressWarnings("unused")
public class Entity{

    TextureModel model;
    Vec3D position;
    float rX, rY, rZ;
    float scale;

    public Entity(TextureModel model, Vec3D position, float rX, float rY, float rZ, float scale) {

        this.model = model;
        this.position = position;
        this.rX = rX;
        this.rY = rY;
        this.rZ = rZ;
        this.scale = scale;

    }

    public void changePosition(float dx, float dy, float dz) {

        this.position.x += dx;
        this.position.y += dy;
        this.position.z += dz;

    }

    public void changeRotation(float dx, float dy, float dz) {

        this.rX += dx;
        this.rY += dy;
        this.rZ += dz;

    }

    public void changeScale(float scale) {

        this.scale += scale;

    }

    public TextureModel getModel() {
        return model;
    }

    public void setModel(TextureModel model) {
        this.model = model;
    }

    public Vec3D getPosition() {
        return position;
    }

    public void setPosition(Vec3D position) {
        this.position = position;
    }

    public float getrX() {
        return rX;
    }

    public void setrX(float rX) {
        this.rX = rX;
    }

    public float getrY() {
        return rY;
    }

    public void setrY(float rY) {
        this.rY = rY;
    }

    public float getrZ() {
        return rZ;
    }

    public void setrZ(float rZ) {
        this.rZ = rZ;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }
}
