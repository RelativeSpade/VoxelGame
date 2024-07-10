package Render;

import Entities.Entity;
import Shaders.StaticShader;
import Toolbox.Math.Matrix4;
import org.lwjgl.opengl.GL11;

@SuppressWarnings("unused")
public class MasterRender {

    public MasterRender(StaticShader shader) {

        createProjectionMatrix();
        shader.start();
        shader.loadProjectionMatrix(projectionMatrix);
        shader.stop();

    }

    Matrix4 projectionMatrix;

    private static float FOV = 70f;
    private static float NEAR_PLANE = 0.1f;
    private static float FAR_PLANE = 1000f;

    public void prepare() {

        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);


        GL11.glClearColor(0.4f, 0.7f, 1.0f, 1);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

    }

    public void render(Entity model, StaticShader shader) {

        EntityRender.render(model, shader);

    }

    public void createProjectionMatrix() {

        projectionMatrix = new Matrix4();

        float aspect = DisplayManager.getAspectRatio();
        float yScale = (float) (1f / Math.tan(Math.toRadians(FOV / 2f)));
        float xScale = yScale / aspect;
        float zp = FAR_PLANE + NEAR_PLANE;
        float zm = FAR_PLANE - NEAR_PLANE;

        projectionMatrix.m00 = xScale;
        projectionMatrix.m11 = yScale;
        projectionMatrix.m22 = -zp/zm;
        projectionMatrix.m23 = -1;
        projectionMatrix.m32 = -(2*FAR_PLANE*NEAR_PLANE)/zm;
        projectionMatrix.m33 = 0;

    }

}
