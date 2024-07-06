#version 400 core

in vec2 textureCoords;

uniform sampler2D textureSampler;

out vec4 out_color;

void main(void) {

    out_Color = texture(textureSampler, textureCoords);

}
