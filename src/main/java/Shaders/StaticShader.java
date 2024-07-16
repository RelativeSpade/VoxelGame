package Shaders;

import Entities.Camera;
import Toolbox.Math.Matrix4;

import static Toolbox.Math.Transformation.createViewMatrix;

public class StaticShader extends ShaderProgram {

    private static final String VERTEX_FILE = "/shaders/vertexShader.glsl";
    private static final String FRAGMENT_FILE = "/shaders/fragmentShader.glsl";

    int location_transformationMatrix;
    int location_projectionMatrix;
    int location_viewMatrix;

    public StaticShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void getAllUniformLocations() {

        location_transformationMatrix = super.getUniformLocation("transformationMatrix");
        location_projectionMatrix = super.getUniformLocation("projectionMatrix");
        location_viewMatrix = super.getUniformLocation("viewMatrix");

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

    public void loadViewMatrix(Camera camera) {

        super.loadMatrix(location_viewMatrix, createViewMatrix(camera));

    }

    public void getProjectionMatrix(Matrix4 matrix) {

        super.getMatrix(location_projectionMatrix, matrix);

    }
}
