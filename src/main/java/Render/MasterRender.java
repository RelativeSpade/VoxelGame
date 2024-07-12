package Render;

import Entities.Camera;
import Entities.Entity;
import Models.TextureModel;
import Shaders.StaticShader;
import Toolbox.Math.Matrix4;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class MasterRender {
    Matrix4 projectionMatrix;

    private static final float FOV = 70f;
    private static final float NEAR_PLANE = 0.1f;
    private static final float FAR_PLANE = 1000f;
    StaticShader shader = new StaticShader();
    EntityRender renderer = new EntityRender();
    Map<TextureModel, List<Entity>> entities = new HashMap<>();

    public MasterRender() {

        createProjectionMatrix();
        shader.start();
        shader.loadProjectionMatrix(projectionMatrix);
        shader.stop();

    }

    public void prepare() {

        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        //GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);

        GL11.glClearColor(0.4f, 0.7f, 1.0f, 1);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

    }

    public void render(Camera camera) {

        prepare();
        shader.start();
        shader.loadViewMatrix(camera);

        renderer.render(entities);

        shader.stop();
        entities.clear();

    }

    public void addEntity(Entity entity) {

        TextureModel model = entity.getModel();

        List<Entity> batch = entities.get(model);

        if(batch != null) {

            batch.add(entity);

        } else {

            List<Entity> newBatch = new ArrayList<>();
            newBatch.add(entity);
            entities.put(model, newBatch);

        }

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
