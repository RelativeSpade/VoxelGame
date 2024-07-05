package Render;

import Models.RawModel;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

public class Loader {

    static List<Integer> vaos = new ArrayList<>(); // List to store VAO IDs; a VAO holds VBOs, essentially a list of VBOs
    static List<Integer> vbos = new ArrayList<>(); // List to store VBO IDs; a VBO holds model data such as vertices

    public RawModel loadToVAO(float[] vertices, int[] indices) {
        int vaoID = createVAO(); // Create and bind a new VAO
        storeDataInAttributeList(vertices, 0, 3); // Store vertex data in a VBO and link to VAO attribute 0
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
    }
}
