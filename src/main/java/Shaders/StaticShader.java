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

    }

    @Override
    protected void bindAttributes() {

        location_transformationMatrix = super.getUniformLocation("transformationMatrix");

    }

    public void loadTranformationMatrix(Matrix4D matrix) {

        super.loadMatrix(location_transformationMatrix, matrix);

    }
}
