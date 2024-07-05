package org.spade.Render;

import org.lwjgl.opengl.GL11;

public class MasterRender {

    public void prepare() {

        GL11.glClearColor(0.4f, 0.7f, 1.0f, 1);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

    }
}
