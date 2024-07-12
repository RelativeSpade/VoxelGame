package Models;

import Entities.Entity;
import Toolbox.Math.Vec3;

import java.util.List;

public class Chunk {

    private List<Entity> blocks;
    private Vec3 origin;

    public Chunk(List<Entity> blocks, Vec3 origin) {

        this.blocks = blocks;
        this.origin = origin;

    }

    public List<Entity> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<Entity> blocks) {
        this.blocks = blocks;
    }

    public Vec3 getOrigin() {
        return origin;
    }
}
