package Chunks;

import java.util.ArrayList;
import java.util.List;
import Cube.Block;
import Cube.Vertex;
import Models.CubeModel;
import Toolbox.Math.Vec2;
import Toolbox.Math.Vec3;

public class ChunkMesh {

    private final List<Vertex> vertices;
    private final List<Float> positionsList;
    private final List<Float> uvsList;
    private final List<Float> normalsList;

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
        vertices.clear();
        positionsList.clear();
        uvsList.clear();
        normalsList.clear();
        buildMesh();
        populateLists();
    }

    private void buildMesh() {
        for (Block block : chunk.getBlocks()) {
            boolean px = isFaceVisible(block, 1, 0, 0);
            boolean nx = isFaceVisible(block, -1, 0, 0);
            boolean py = isFaceVisible(block, 0, 1, 0);
            boolean ny = isFaceVisible(block, 0, -1, 0);
            boolean pz = isFaceVisible(block, 0, 0, 1);
            boolean nz = isFaceVisible(block, 0, 0, -1);

            addFace(block, CubeModel.PX_POS, CubeModel.UV_PX, CubeModel.NORMALS, px);
            addFace(block, CubeModel.NX_POS, CubeModel.UV_NX, CubeModel.NORMALS, nx);
            addFace(block, CubeModel.PY_POS, CubeModel.UV_PY, CubeModel.NORMALS, py);
            addFace(block, CubeModel.NY_POS, CubeModel.UV_NY, CubeModel.NORMALS, ny);
            addFace(block, CubeModel.PZ_POS, CubeModel.UV_PZ, CubeModel.NORMALS, pz);
            addFace(block, CubeModel.NZ_POS, CubeModel.UV_NZ, CubeModel.NORMALS, nz);
        }
    }

    private boolean isFaceVisible(Block block, int dx, int dy, int dz) {
        for (Block other : chunk.getBlocks()) {
            if (block.x + dx == other.x && block.y + dy == other.y && block.z + dz == other.z) {
                return false;
            }
        }
        return true;
    }

    private void addFace(Block block, Vec3[] positions, Vec2[] uvs, Vec3[] normals, boolean visible) {
        if (visible) {
            for (int i = 0; i < 6; i++) {
                vertices.add(new Vertex(
                        new Vec3(positions[i].x + block.x, positions[i].y + block.y, positions[i].z + block.z),
                        uvs[(block.type * 6) + i],
                        normals[i]
                ));
            }
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

        cleanup();
    }

    private float[] convertListToArray(List<Float> list) {
        float[] array = new float[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public void cleanup() {
        normalsList.clear();
        uvsList.clear();
        positionsList.clear();
        vertices.clear();
    }
}
