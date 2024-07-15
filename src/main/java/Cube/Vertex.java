package Cube;

import Toolbox.Math.*;

public class Vertex {

    public Vec3 positions, normals;
    public Vec2 uvs;

    public Vertex(Vec3 positions, Vec2 uvs, Vec3 normals) {

        this.positions = positions;
        this.normals = normals;
        this.uvs = uvs;

    }
}
