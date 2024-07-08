package Render;

import Entities.Entity;
import Shaders.StaticShader;
import Toolbox.Math.Matrix4;
import org.lwjgl.opengl.GL11;

public class MasterRender {

    Matrix4 projectionMatrix;

    public void prepare() {

        GL11.glClearColor(0.4f, 0.7f, 1.0f, 1);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

    }

    public void render(Entity model, StaticShader shader) {

        EntityRender.render(model, shader);

    }

    public void createProjectionMatrix() {

    }

}
