package Models;

import Textures.ModelTexture;

public class TextureModel {

    RawModel model;
    ModelTexture texture;

    public TextureModel(RawModel model, ModelTexture texture) {
        this.model = model;
        this.texture = texture;
    }

    public ModelTexture getTexture() {
        return texture;
    }

    public RawModel getModel() {
        return model;
    }
}
