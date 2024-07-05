package Render;

import Models.RawModel;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class EntityRender {

    public void render(RawModel model) {

        GL30.glBindVertexArray(model.getVaoID()); // Bind Vertex Array to VaoID
        GL20.glEnableVertexAttribArray(0); // Enable Vertex Attribute Array
        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, model.getVertexCount()); // Draw Model Array
        GL20.glDisableVertexAttribArray(0); // Disable Vertex Attribute Array
        GL30.glBindVertexArray(0); // Unbind Vertex Array

    }

}
