package Chunks;

import Cube.Block;
import Entities.Entity;
import Toolbox.Math.Vec3;

import java.util.List;

public class Chunk {

    private List<Block> blocks;
    private Vec3 origin;

    public Chunk(List<Block> blocks, Vec3 origin) {

        this.blocks = blocks;
        this.origin = origin;

    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public Vec3 getOrigin() {
        return origin;
    }
}
