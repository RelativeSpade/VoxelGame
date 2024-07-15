package Cube;

public class Block {
    public int x,y,z;

    public static int GRASS = 0;
    public static int DIRT = 1;
    public static int STONE = 2;
    public static int TREEBARK = 3;
    public static int TREELEAF = 4;

    public int type;

    public Block(int x, int y, int z, int type) {

        this.x = x;
        this.y = y;
        this.z = z;

        this.type = type;

    }

}
