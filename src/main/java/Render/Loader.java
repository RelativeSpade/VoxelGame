package Render;

import Models.RawModel;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;


public class Loader {

    static List<Integer> textures = new ArrayList<>(); // List to store textures
    static List<Integer> vaos = new ArrayList<>(); // List to store VAO IDs; a VAO holds VBOs, essentially a list of VBOs
    static List<Integer> vbos = new ArrayList<>(); // List to store VBO IDs; a VBO holds model data such as vertices
    static String absPath = "C:/Users/Spade/Documents/Voxel/src/main/resources/textures/";

    public RawModel loadToVAO(float[] vertices, int[] indices, float[] uv) {
        int vaoID = createVAO(); // Create and bind a new VAO
        storeDataInAttributeList(vertices, 0, 3); // Store vertex data in a VBO and link to VAO attribute 0
        storeDataInAttributeList(uv, 1, 2); // Store texture mapping data in VBO and link to VAO attribute 1
        bindIndicesBuffer(indices); // Bind the indices to the buffer
        GL30.glBindVertexArray(0); // Unbind the current VAO

        return new RawModel(vaoID, indices.length); // Return the model with its VAO ID and vertex count
    }

    private int createVAO() {
        int vaoID = GL30.glGenVertexArrays(); // Generate a new VAO ID
        vaos.add(vaoID); // Add the new VAO ID to the list
        GL30.glBindVertexArray(vaoID); // Bind the new VAO for use

        return vaoID;
    }

    public int loadTexture(String filePath) {
        int textureId = 0;

        try (MemoryStack stack = MemoryStack.stackPush()) {
        IntBuffer widthBuffer = stack.mallocInt(1);
        IntBuffer heightBuffer = stack.mallocInt(1);
        IntBuffer channelsBuffer = stack.mallocInt(1);

        // Load image data
        ByteBuffer image = STBImage.stbi_load(absPath + filePath, widthBuffer, heightBuffer, channelsBuffer, 4);
        if (image == null) {
            throw new RuntimeException("Failed to load texture file: " + STBImage.stbi_failure_reason());
        }

        // Generate texture ID
        textureId = GL11.glGenTextures();
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureId);

        // Set texture parameters
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

        // Upload texture data
        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, widthBuffer.get(0), heightBuffer.get(0), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, image);

        // Free image data
        STBImage.stbi_image_free(image);
    } catch (Exception e) {
        e.printStackTrace();
        System.err.println("Failed to load texture: " + filePath);
        System.exit(-1);
    }

        return textureId;
}

    private void storeDataInAttributeList(float[] data, int attributeNumber, int dimensions) {
        int vboID = GL15.glGenBuffers(); // Generate a new VBO ID
        vbos.add(vboID); // Add the new VBO ID to the list
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID); // Bind the new VBO for use
        FloatBuffer buffer = storeDataInFloatBuffer(data); // Convert the float array to a FloatBuffer
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW); // Store the data in the VBO
        GL20.glVertexAttribPointer(attributeNumber, dimensions, GL11.GL_FLOAT, false, 0, 0); // Link the VBO to the specified attribute of the VAO
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0); // Unbind the current VBO
    }

    private void  bindIndicesBuffer(int[] indices) {

        int vboID = GL15.glGenBuffers();
        vbos.add(vboID);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
        IntBuffer buffer = storeDataInIntegerBuffer(indices);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);

    }

    private IntBuffer storeDataInIntegerBuffer(int[] data) {
        IntBuffer buffer = BufferUtils.createIntBuffer(data.length); // Create a IntBuffer with the provided length
        buffer.put(data); // Put the data into the buffer
        buffer.flip(); // Prepare the buffer for reading (flip it)

        return  buffer; // Return the prepared buffer
    }

    private FloatBuffer storeDataInFloatBuffer(float[] data) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length); // Create a FloatBuffer with the specified length
        buffer.put(data); // Put the data into the buffer
        buffer.flip(); // Prepare the buffer for reading (flip it)

        return buffer; // Return the prepared buffer
    }

    public void cleanUp() {
        for (int vao : vaos) {
            GL30.glDeleteVertexArrays(vao); // Delete each VAO stored in the list
        }

        for (int vbo : vbos) {
            GL15.glDeleteBuffers(vbo); // Delete each VBO stored in the list
        }

        for (int texture : textures) {
            GL11.glDeleteTextures(texture);
        }
    }
}
