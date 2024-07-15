package Engine;

import Chunks.Chunk;
import Chunks.ChunkMesh;
import Cube.Block;
import Entities.Camera;
import Entities.Entity;
import Models.*;
import Render.DisplayManager;
import Render.Loader;
import Render.MasterRender;
import Shaders.StaticShader;
import Textures.ModelTexture;
import Toolbox.Math.Vec3;
import Toolbox.Necessities.Mouse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;

public class Main {

    public static Loader loader1 = null;
    public static StaticShader shader1 = null;
    public static int FPS = -1;
    public static double previousTime = glfwGetTime();
    public static float VOXEL_SIZE = 1f;
    static List<ChunkMesh> chunks = Collections.synchronizedList(new ArrayList<>());
    static List<Entity> entities = new ArrayList<>();
    static Vec3 camPos = new Vec3(0, 0, 0);
    static List<Vec3> usedPos = Collections.synchronizedList(new ArrayList<>());

    static final int WORLD_SIZE = 4 * 16;
    public static void main(String[] args){

        DisplayManager.createDisplay();
        Loader loader = new Loader();
        loader1 = loader;
        shader1 = new StaticShader();
        MasterRender renderer = new MasterRender();

        RawModel model = loader.loadToVAO(CubeModel.vertices, CubeModel.indices, CubeModel.uvs);
        ModelTexture texture = new ModelTexture(loader.loadTexture("dirt.png"));
        TextureModel textureModel = new TextureModel(model, texture);

        long window = DisplayManager.getWindow();

        Mouse.init();

        Camera camera = new Camera(new Vec3(0, 0, 0), 0, 0, 0);

        new Thread(() -> {

            while(!glfwWindowShouldClose(window)) {

                for (int x = (int) ((camPos.x - WORLD_SIZE) / 16); x < ((camPos.x + WORLD_SIZE) / 16); x++){
                    for (int z = (int) ((camPos.z - WORLD_SIZE) / 16); z < ((camPos.z + WORLD_SIZE) / 16); z++) {

                        if(!usedPos.contains(new Vec3(x*16, 0,z*16))) {

                            List<Block> blocks = new ArrayList<>();

                            for(int i =0; i < 16; i++){
                                for(int j = 0; j < 16; j++){

                                    blocks.add(new Block(i,0,j, Block.TYPE.DIRT));

                                }
                            }
                            Chunk tempChunk = new Chunk(blocks, new Vec3(x*16, 0, z*16));
                            chunks.add(new ChunkMesh(tempChunk));
                            usedPos.add(new Vec3(x*16, 0,z*16));

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
                TextureModel textureModel1 = new TextureModel(tempModel, texture);
                Entity entity = new Entity(textureModel1, chunkMesh.chunk.getOrigin(), 0, 0, 0, VOXEL_SIZE);
                entities.add(entity);

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

            if(glfwGetKey(window, GLFW_KEY_ESCAPE) == GLFW_PRESS){
                DisplayManager.closeDisplay();
            }

            DisplayManager.updateDisplay();

        }

        DisplayManager.closeDisplay();
    }
}