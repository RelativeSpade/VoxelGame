package Render;

import Models.RawModel;
import Models.TextureModel;
import Shaders.StaticShader;
import Textures.ModelTexture;
import Toolbox.Math.Matrix4;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

import java.nio.IntBuffer;

import static Engine.Main.loader1;
import static Engine.Main.shader1;
import static Toolbox.Math.Transformation.createOrthographicMatrix;
import static org.lwjgl.glfw.GLFW.glfwGetWindowSize;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;

public class CrosshairRender {

        private StaticShader shader;
        private RawModel crosshair;
        private ModelTexture crosshairTexture;
        private TextureModel textureCrosshair;
        private Matrix4 orthoMatrix;

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

            textureCrosshair = new TextureModel(crosshair, crosshairTexture);

            IntBuffer widthBuffer = BufferUtils.createIntBuffer(1);
            IntBuffer heightBuffer = BufferUtils.createIntBuffer(1);
            glfwGetWindowSize(DisplayManager.getWindow(), widthBuffer, heightBuffer);

            int width = widthBuffer.get(0);
            int height = heightBuffer.get(0);

            float floatWidth = (float) width;
            float floatHeight = (float) height;

            orthoMatrix = createOrthographicMatrix(0, floatWidth, floatHeight, 0, -1, 1);
        }

        public void render() {
            shader.start();

            shader.loadProjectionMatrix(orthoMatrix);

            GL11.glDisable(GL_DEPTH_TEST);

            GL30.glBindVertexArray(textureCrosshair.getModel().getVaoID());

            GL20.glEnableVertexAttribArray(0); // Position
            GL20.glEnableVertexAttribArray(1); // UVs

            GL13.glActiveTexture(GL13.GL_TEXTURE0);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureCrosshair.getTexture().getTextureID());

            GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, textureCrosshair.getModel().getVertexCount());

            GL20.glDisableVertexAttribArray(0);
            GL20.glDisableVertexAttribArray(1);
            GL30.glBindVertexArray(0);

            GL11.glEnable(GL_DEPTH_TEST);

            shader.stop();
        }
    }

