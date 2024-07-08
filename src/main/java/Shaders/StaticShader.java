package Shaders;

import Toolbox.Math.Matrix4D;

public class StaticShader extends ShaderProgram {

    private static final String VERTEX_FILE = "/shaders/vertexShader.glsl";
    private static final String FRAGMENT_FILE = "/shaders/fragmentShader.glsl";

    int location_transformationMatrix;

    public StaticShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void getAllUniformLocations() {

        location_transformationMatrix = super.getUniformLocation("transformationMatrix");

    }

    @Override
    protected void bindAttributes() {

        super.bindAttribute("position", 0);
        super.bindAttribute("textureCoords", 1);

    }

    public void loadTransformationMatrix(Matrix4D matrix) {

        super.loadMatrix(location_transformationMatrix, matrix);

    }
}
