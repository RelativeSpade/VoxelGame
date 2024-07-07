#version 400 core

in vec2 pass_texturecoords;

uniform sampler2D textureSampler;

out vec4 out_color;

void main(void) {

    out_color = texture(textureSampler, pass_texturecoords);

}
