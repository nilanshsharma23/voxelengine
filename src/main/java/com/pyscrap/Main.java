package com.pyscrap;

import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

import org.joml.Vector3f;

import com.pyscrap.entities.Camera;
import com.pyscrap.entities.Entity;
import com.pyscrap.models.RawModel;
import com.pyscrap.models.TexturedModel;
import com.pyscrap.renderEngine.DisplayManager;
import com.pyscrap.renderEngine.Loader;
import com.pyscrap.renderEngine.MasterRenderer;
import com.pyscrap.textures.ModelTexture;

public class Main {
        static float deltaTime;

        public static void main(String[] args) {
                DisplayManager.createDisplay();

                Loader loader = new Loader();

                float[] vertices = {
                                -0.5f, 0.5f, -0.5f,
                                -0.5f, -0.5f, -0.5f,
                                0.5f, -0.5f, -0.5f,
                                0.5f, 0.5f, -0.5f,

                                -0.5f, 0.5f, 0.5f,
                                -0.5f, -0.5f, 0.5f,
                                0.5f, -0.5f, 0.5f,
                                0.5f, 0.5f, 0.5f,

                                0.5f, 0.5f, -0.5f,
                                0.5f, -0.5f, -0.5f,
                                0.5f, -0.5f, 0.5f,
                                0.5f, 0.5f, 0.5f,

                                -0.5f, 0.5f, -0.5f,
                                -0.5f, -0.5f, -0.5f,
                                -0.5f, -0.5f, 0.5f,
                                -0.5f, 0.5f, 0.5f,

                                -0.5f, 0.5f, 0.5f,
                                -0.5f, 0.5f, -0.5f,
                                0.5f, 0.5f, -0.5f,
                                0.5f, 0.5f, 0.5f,

                                -0.5f, -0.5f, 0.5f,
                                -0.5f, -0.5f, -0.5f,
                                0.5f, -0.5f, -0.5f,
                                0.5f, -0.5f, 0.5f

                };

                float[] textureCoords = {
                                0, 0,
                                0, 1,
                                1, 1,
                                1, 0,
                                0, 0,
                                0, 1,
                                1, 1,
                                1, 0,
                                0, 0,
                                0, 1,
                                1, 1,
                                1, 0,
                                0, 0,
                                0, 1,
                                1, 1,
                                1, 0,
                                0, 0,
                                0, 1,
                                1, 1,
                                1, 0,
                                0, 0,
                                0, 1,
                                1, 1,
                                1, 0

                };

                int[] indices = {
                                0, 1, 3,
                                3, 1, 2,
                                4, 5, 7,
                                7, 5, 6,
                                8, 9, 11,
                                11, 9, 10,
                                12, 13, 15,
                                15, 13, 14,
                                16, 17, 19,
                                19, 17, 18,
                                20, 21, 23,
                                23, 21, 22

                };

                RawModel model = loader.loadToVAO(vertices, indices, textureCoords);
                ModelTexture texture = new ModelTexture(loader.loadTexture("dirt.png"));
                TexturedModel texturedModel = new TexturedModel(model, texture);

                Entity entity = new Entity(texturedModel, new Vector3f(0, 0, -3), new Vector3f(0, 0, 0),
                                new Vector3f(1, 1, 1));

                Camera camera = new Camera();

                MasterRenderer renderer = new MasterRenderer();

                while (!glfwWindowShouldClose(DisplayManager.window)) {
                        entity.increaseRotation(1f, 1f, 1f);
                        renderer.processEntity(entity);

                        renderer.render(camera);
                        DisplayManager.updateDisplay();
                }

                renderer.cleanUp();
                loader.cleanUp();
                DisplayManager.closeDisplay();
        }
}