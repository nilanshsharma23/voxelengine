package com.pyscrap;

import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

import com.pyscrap.models.RawModel;
import com.pyscrap.models.TexturedModel;
import com.pyscrap.renderEngine.DisplayManager;
import com.pyscrap.renderEngine.Loader;
import com.pyscrap.renderEngine.Renderer;
import com.pyscrap.shaders.StaticShader;
import com.pyscrap.textures.ModelTexture;

public class Main {
    public static void main(String[] args) {
        DisplayManager.createDisplay();

        Loader loader = new Loader();
        Renderer renderer = new Renderer();
        StaticShader shader = new StaticShader();

        float[] vertices = {
                -0.5f, 0.5f, 0,
                -0.5f, -0.5f, 0,
                0.5f, -0.5f, 0,
                0.5f, 0.5f, 0f
        };

        int[] indices = {
                0, 1, 3,
                3, 1, 2
        };

        float[] textureCoords = {
                0, 0,
                0, 1,
                1, 1,
                1, 0
        };

        RawModel model = loader.loadToVAO(vertices, indices, textureCoords);
        ModelTexture texture = new ModelTexture(loader.loadTexture("atlas.png"));
        TexturedModel texturedModel = new TexturedModel(model, texture);

        while (!glfwWindowShouldClose(DisplayManager.window)) {
            renderer.prepare();
            shader.start();
            renderer.render(texturedModel);
            shader.stop();

            DisplayManager.updateDisplay();
        }

        shader.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();
    }
}