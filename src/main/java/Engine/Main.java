package Engine;

import Chunks.Chunk;
import Chunks.ChunkMesh;
import Cube.Block;
import Entities.Camera;
import Entities.Entity;
import Models.*;
import Render.CrosshairRender;
import Render.DisplayManager;
import Render.Loader;
import Render.MasterRender;
import Shaders.StaticShader;
import Textures.ModelTexture;
import Toolbox.Math.Matrix4;
import Toolbox.Math.Vec3;
import Toolbox.Necessities.Mouse;
import Toolbox.Necessities.PerlinNoiseGenerator;
import org.lwjgl.BufferUtils;

import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static Toolbox.Math.Transformation.createOrthographicMatrix;
import static Toolbox.Math.Transformation.createViewMatrix;
import static org.lwjgl.glfw.GLFW.*;

public class Main {

    public static Loader loader1 = null;
    public static StaticShader shader1 = null;
    public static int FPS = -1;
    public static double previousTime = glfwGetTime();
    public static float VOXEL_SIZE = 1f;
    public static int CHUNK_SIZE = 32;
    static List<ChunkMesh> chunks = Collections.synchronizedList(new ArrayList<>());
    static List<Entity> entities = new ArrayList<>();
    static CrosshairRender crosshairRender = null;
    static Vec3 camPos = new Vec3(0, 0, 0);
    static List<Vec3> usedPos = Collections.synchronizedList(new ArrayList<>());

    static final int WORLD_SIZE = 4 * CHUNK_SIZE;
    public static void main(String[] args){

        DisplayManager.createDisplay();
        Loader loader = new Loader();
        loader1 = loader;
        shader1 = new StaticShader();
        MasterRender renderer = new MasterRender();
        ModelTexture textureAtlas = new ModelTexture(loader.loadTexture("Atlas.png"));
        long window = DisplayManager.getWindow();

        Mouse.init();

        Camera camera = new Camera(new Vec3(0, 0, 0), 0, 0, 0);

        PerlinNoiseGenerator perlin = new PerlinNoiseGenerator();

        crosshairRender = new CrosshairRender();

        IntBuffer widthBuffer = BufferUtils.createIntBuffer(1);
        IntBuffer heightBuffer = BufferUtils.createIntBuffer(1);
        glfwGetWindowSize(window, widthBuffer, heightBuffer);

        int width = widthBuffer.get(0);
        int height = heightBuffer.get(0);

        float floatWidth = (float) width;
        float floatHeight = (float) height;

        Matrix4 orthoMatrix = createOrthographicMatrix(0, floatWidth, floatHeight, 0, -1, 1);

        new Thread(() -> {

            while(!glfwWindowShouldClose(window)) {

                for (int x = (int) ((camPos.x - WORLD_SIZE) / CHUNK_SIZE); x < ((camPos.x + WORLD_SIZE) / CHUNK_SIZE); x++){
                    for (int z = (int) ((camPos.z - WORLD_SIZE) / CHUNK_SIZE); z < ((camPos.z + WORLD_SIZE) / CHUNK_SIZE); z++) {

                        if(!usedPos.contains(new Vec3(x*CHUNK_SIZE, 0,z*CHUNK_SIZE))) {

                            List<Block> blocks = new ArrayList<>();

                            for(int i = 0; i < CHUNK_SIZE; i++){
                                for(int j = 0; j < CHUNK_SIZE; j++){

                                    blocks.add(new Block(i, (int) perlin.generateHeight(i + (x * CHUNK_SIZE), j + (z * CHUNK_SIZE)),j, Block.GRASS));

                                }
                            }
                            Chunk tempChunk = new Chunk(blocks, new Vec3(x*CHUNK_SIZE, 0, z*CHUNK_SIZE));
                            chunks.add(new ChunkMesh(tempChunk));
                            usedPos.add(new Vec3(x*CHUNK_SIZE, 0,z*CHUNK_SIZE));

                        }
                    }
                }
            }
        }).start();

        int index = 0;
        while (!glfwWindowShouldClose(window)) {

            double currentTime = glfwGetTime();
            FPS++;

            Mouse.update();
            camera.move();

            camPos = camera.getPosition();

            if(currentTime - previousTime >= 1.0) {

                DisplayManager.updateWinName(FPS);
                FPS = 0;
                previousTime = currentTime;
            }

            if(index < chunks.size()){

                ChunkMesh chunkMesh = chunks.get(index);
                RawModel tempModel = loader.loadToVAO(chunkMesh.positions, chunkMesh.uvs);
                TextureModel textureModel1 = new TextureModel(tempModel, textureAtlas);
                Entity entity = new Entity(textureModel1, chunkMesh.chunk.getOrigin(), 0, 0, 0, VOXEL_SIZE);
                entities.add(entity);

                chunks.get(index).positions = null;
                chunks.get(index).normals = null;
                chunks.get(index).uvs = null;

                index++;
            }

            glfwPollEvents();

            for (int i = 0; i < entities.size(); i++) {

                Vec3 origin = entities.get(i).getPosition();

                int distX = (int) (camPos.x - origin.x);
                int distZ = (int) (camPos.z - origin.z);

                if (distX < 0) {
                    distX *= -1;
                }

                if (distZ < 0) {
                    distZ *= -1;
                }

                if((distX <= WORLD_SIZE) && (distZ <= WORLD_SIZE)) {

                    renderer.addEntity(entities.get(i));

                }
            }

            renderer.render(camera);

            shader1.loadProjectionMatrix(orthoMatrix);

            crosshairRender.render();

            //shader1.loadProjectionMatrix(renderer.projectionMatrix);

            if(glfwGetKey(window, GLFW_KEY_ESCAPE) == GLFW_PRESS){
                DisplayManager.closeDisplay();
            }

            if(glfwGetMouseButton(window, GLFW_MOUSE_BUTTON_2) == GLFW_PRESS){
                //TODO: Implement Block Placement Logic
            }

            DisplayManager.updateDisplay();

        }

        DisplayManager.closeDisplay();
    }
}