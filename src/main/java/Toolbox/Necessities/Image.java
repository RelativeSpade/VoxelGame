package Toolbox.Necessities;

import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.stb.STBImage.stbi_load;

public class Image {

    public ByteBuffer get_image() {
        return image;
    }

    public int get_width() {
        return width;
    }

    public int get_heigh() {
        return heigh;
    }

    private ByteBuffer image;
    private int width, heigh;

    Image(int width, int heigh, ByteBuffer image) {
        this.image = image;
        this.heigh = heigh;
        this.width = width;
    }
    public static Image load_image(String path) {
        ByteBuffer image;
        int width, heigh;
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer comp = stack.mallocInt(1);
            IntBuffer w = stack.mallocInt(1);
            IntBuffer h = stack.mallocInt(1);

            image = stbi_load(path, w, h, comp, 4);
            if (image == null) {
                System.err.println("Could not load Image: " + image);
                System.exit(-1);
            }
            width = w.get();
            heigh = h.get();
        }
        return new Image(width, heigh, image);
    }
}
