package Chunks;


import Cube.Block;
import Cube.Vertex;
import Models.CubeModel;
import Toolbox.Math.Vec2;
import Toolbox.Math.Vec3;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ChunkMesh {

    private List<Vertex> vertices;
    private List<Float> positionsList;
    private List<Float> uvsList;
    private List<Float> normalsList;

    public float[] positions, uvs, normals;
    public Chunk chunk;

    public ChunkMesh(Chunk chunk) {
        this.chunk = chunk;


        vertices = new ArrayList<>();
        positionsList = new ArrayList<>();
        uvsList = new ArrayList<>();
        normalsList = new ArrayList<>();


        buildMesh();
        populateLists();
    }

    public void update(Chunk chunk) {
        this.chunk = chunk;


        buildMesh();
        populateLists();
    }

    private void buildMesh() {
        List<Vec3> blockPositions = new ArrayList<>();
        for (Block block : chunk.getBlocks()) {
            blockPositions.add(new Vec3(block.x, block.y, block.z));
        }

        for (Block block : chunk.getBlocks()) {
            boolean px = blockPositions.contains(new Vec3(block.x + 1, block.y, block.z));
            boolean nx = blockPositions.contains(new Vec3(block.x - 1, block.y, block.z));
            boolean py = blockPositions.contains(new Vec3(block.x, block.y + 1, block.z));
            boolean ny = blockPositions.contains(new Vec3(block.x, block.y - 1, block.z));
            boolean pz = blockPositions.contains(new Vec3(block.x, block.y, block.z + 1));
            boolean nz = blockPositions.contains(new Vec3(block.x, block.y, block.z - 1));

            if (!px) addFaceVertices(block, CubeModel.PX_POS, CubeModel.UV, CubeModel.NORMALS);
            if (!nx) addFaceVertices(block, CubeModel.NX_POS, CubeModel.UV, CubeModel.NORMALS);
            if (!py) addFaceVertices(block, CubeModel.PY_POS, CubeModel.UV, CubeModel.NORMALS);
            if (!ny) addFaceVertices(block, CubeModel.NY_POS, CubeModel.UV, CubeModel.NORMALS);
            if (!pz) addFaceVertices(block, CubeModel.PZ_POS, CubeModel.UV, CubeModel.NORMALS);
            if (!nz) addFaceVertices(block, CubeModel.NZ_POS, CubeModel.UV, CubeModel.NORMALS);
        }
    }


    private void addFaceVertices(Block block, Vec3[] positions, Vec2[] uvs, Vec3[] normals) {
        for (int i = 0; i < 6; i++) {
            vertices.add(new Vertex(
                    new Vec3(positions[i].x + block.x, positions[i].y + block.y, positions[i].z + block.z),
                    uvs[i],
                    normals[i]
            ));
        }
    }

    private void populateLists() {
        for (Vertex vertex : vertices) {
            positionsList.add(vertex.positions.x);
            positionsList.add(vertex.positions.y);
            positionsList.add(vertex.positions.z);


            uvsList.add(vertex.uvs.x);
            uvsList.add(vertex.uvs.y);


            normalsList.add(vertex.normals.x);
            normalsList.add(vertex.normals.y);
            normalsList.add(vertex.normals.z);
        }

        positions = convertListToArray(positionsList);
        uvs = convertListToArray(uvsList);
        normals = convertListToArray(normalsList);
    }

    private float[] convertListToArray(List<Float> list) {
        float[] array = new float[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }
}

