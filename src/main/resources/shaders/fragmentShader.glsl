#version 400 core

in vec2 pass_texturecoords;

uniform sampler2D textureSampler;

out vec4 out_color;

void main(void) {

    vec4 textureColor = texture(textureSampler, pass_texturecoords);
    if (textureColor.a < 0.1) {
        discard; // Discard pixels with low alpha to avoid rendering them
    }
    out_color = textureColor;

}
