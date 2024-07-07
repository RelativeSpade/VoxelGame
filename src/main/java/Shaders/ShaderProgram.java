package Shaders;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import java.io.*;
import java.util.Vector;

public abstract class ShaderProgram {

    int programID;
    int vertexShaderID;
    int fragmentShaderID;

    public ShaderProgram(String vertexFile, String fragmentFile) {

        programID = GL20.glCreateProgram();
        vertexShaderID = loadShader(vertexFile, GL20.GL_VERTEX_SHADER);
        fragmentShaderID = loadShader(fragmentFile, GL20.GL_FRAGMENT_SHADER);

        GL20.glAttachShader(programID, vertexShaderID);
        GL20.glAttachShader(programID, fragmentShaderID);
        bindAttributes();
        GL20.glLinkProgram(programID);
        GL20.glValidateProgram(programID);

        getAllUniformLocations();

    }

    protected abstract void getAllUniformLocations();

    protected int getUniformLocation(String varName) {

        return GL20.glGetUniformLocation(programID, varName);

    }

    protected void loadFloat(int location, float value) {

        GL20.glUniform1f(location,value);

    }

    protected void load2DVector(int location, Vector vec) {


    }

    protected abstract void bindAttributes();

    protected void bindAttribute(String variableName, int attribute) {

        GL20.glBindAttribLocation(programID, attribute, variableName);

    }

    public void start() {
        GL20.glUseProgram(programID);
    }

    public void stop() {
        GL20.glUseProgram(0);
    }

    public void cleanUp() {

        stop();
        GL20.glDetachShader(programID, vertexShaderID);
        GL20.glDetachShader(programID, fragmentShaderID);
        GL20.glDeleteShader(vertexShaderID);
        GL20.glDeleteShader(fragmentShaderID);
        GL20.glDeleteProgram(programID);

    }

    private int loadShader(String file, int type) {

        StringBuilder shaderSource = new StringBuilder();

        InputStream in = getClass().getResourceAsStream(file);

        if (in == null) {
            System.err.println("Could not find shader file: " + file);
            System.exit(-1);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                shaderSource.append(line).append("//\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Could not load shader file!");
            System.exit(-1);
        }

        int shaderID = GL20.glCreateShader(type);
        GL20.glShaderSource(shaderID, shaderSource);
        GL20.glCompileShader(shaderID);

        if (GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {

            System.out.println(GL20.glGetShaderInfoLog(shaderID, 1000));
            System.err.println("Could not compile shader!");
            System.exit(-1);

        }

        return shaderID;
    }
}
