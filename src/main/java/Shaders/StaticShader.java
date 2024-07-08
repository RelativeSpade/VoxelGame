package Shaders;

import Toolbox.Math.Matrix4;

public class StaticShader extends ShaderProgram {

    private static final String VERTEX_FILE = "/shaders/vertexShader.glsl";
    private static final String FRAGMENT_FILE = "/shaders/fragmentShader.glsl";

    int location_transformationMatrix;
    int location_projectionMatrix;

    public StaticShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void getAllUniformLocations() {

        location_transformationMatrix = super.getUniformLocation("transformationMatrix");
        location_projectionMatrix = super.getUniformLocation("projectionMatrix");

    }

    @Override
    protected void bindAttributes() {

        super.bindAttribute("position", 0);
        super.bindAttribute("textureCoords", 1);

    }

    public void loadProjectionMatrix(Matrix4 matrix) {

        super.loadMatrix(location_projectionMatrix, matrix);

    }

    public void loadTransformationMatrix(Matrix4 matrix) {

        super.loadMatrix(location_transformationMatrix, matrix);

    }
}
