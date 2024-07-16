package Render;

import Models.RawModel;
import Shaders.StaticShader;
import Textures.ModelTexture;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

import static Engine.Main.loader1;
import static Engine.Main.shader1;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;

public class CrosshairRender {

        private StaticShader shader;
        private RawModel crosshair;
        private ModelTexture crosshairTexture;

        public CrosshairRender(StaticShader shader2) {
            shader = shader2;

            float[] vertices = {
                    -0.5f,  0.5f, 0.0f,  // Top-left
                    0.5f,  0.5f, 0.0f,  // Top-right
                    -0.5f, -0.5f, 0.0f,  // Bottom-left
                    0.5f, -0.5f, 0.0f   // Bottom-right
            };

            float[] textureCoords = {
                    0.0f, 0.0f,   // Top-left
                    1.0f, 0.0f,   // Top-right
                    0.0f, 1.0f,   // Bottom-left
                    1.0f, 1.0f    // Bottom-right
            };


            crosshair = loader1.loadToVAO(vertices, textureCoords);

            crosshairTexture = new ModelTexture(loader1.loadTexture("crosshair.png"));
        }

        public void render() {
            shader.start();

            GL11.glDisable(GL_DEPTH_TEST);

            GL30.glBindVertexArray(crosshair.getVaoID());

            GL20.glEnableVertexAttribArray(0); // Position
            GL20.glEnableVertexAttribArray(1); // UVs

            GL13.glActiveTexture(GL13.GL_TEXTURE0);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, crosshairTexture.getTextureID());

            GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, crosshair.getVertexCount());

            GL20.glDisableVertexAttribArray(0);
            GL20.glDisableVertexAttribArray(1);
            GL30.glBindVertexArray(0);

            GL11.glEnable(GL_DEPTH_TEST);
            shader.stop();
        }
    }

