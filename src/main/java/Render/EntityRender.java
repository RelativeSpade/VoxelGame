package Render;

import Entities.Entity;
import Shaders.StaticShader;
import Toolbox.Math.Matrix4;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import static Toolbox.Math.Transformation.createTransformationMatrix;

public class EntityRender {

    public static void render(Entity model, StaticShader shader) {

        GL30.glBindVertexArray(model.getModel().getModel().getVaoID()); // Bind Vertex Array to VaoID
        GL20.glEnableVertexAttribArray(0); // Enable Vertex Attribute Array
        GL20.glEnableVertexAttribArray(1); // Enable UV Attribute Array

        Matrix4 transformationMatrix = createTransformationMatrix(model.getPosition(), model.getrX(), model.getrY(), model.getrZ(), model.getScale());
        shader.loadTransformationMatrix(transformationMatrix);

        GL13.glActiveTexture(GL13.GL_TEXTURE0); // Set active texture
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getModel().getTexture().getTextureID());
        GL11.glDrawElements(GL11.GL_TRIANGLES, model.getModel().getModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);

        GL20.glDisableVertexAttribArray(0); // Disable Vertex Attribute Array
        GL20.glDisableVertexAttribArray(1); // Disable UV Attribute Array
        GL30.glBindVertexArray(0); // Unbind Vertex Array

    }

}
