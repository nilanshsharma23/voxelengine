package com.pyscrap;

import static org.lwjgl.glfw.GLFW.glfwGetTime;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector3f;

import com.pyscrap.entities.Camera;
import com.pyscrap.entities.Entity;
import com.pyscrap.models.RawModel;
import com.pyscrap.models.TexturedModel;
import com.pyscrap.renderEngine.DisplayManager;
import com.pyscrap.renderEngine.Loader;
import com.pyscrap.renderEngine.MasterRenderer;
import com.pyscrap.terrain.World;
import com.pyscrap.textures.ModelTexture;

public class Main {
        static float deltaTime;

        public static void main(String[] args) {
                DisplayManager.createDisplay();

                double lastTime = glfwGetTime();

                Loader loader = new Loader();

                float[] vertices = { // +Z FACE (front, normal: 0, 0, +1)
                                -0.5f, -0.5f, 0.5f, // 0: bottom-left
                                0.5f, -0.5f, 0.5f, // 1: bottom-right
                                0.5f, 0.5f, 0.5f, // 2: top-right
                                -0.5f, 0.5f, 0.5f, // 3: top-left

                                // -Z FACE (back, normal: 0, 0, -1)
                                0.5f, -0.5f, -0.5f, // 4: bottom-left
                                -0.5f, -0.5f, -0.5f, // 5: bottom-right
                                -0.5f, 0.5f, -0.5f, // 6: top-right
                                0.5f, 0.5f, -0.5f, // 7: top-left

                                // -X FACE (left, normal: -1, 0, 0)
                                -0.5f, -0.5f, -0.5f, // 8: bottom-left
                                -0.5f, -0.5f, 0.5f, // 9: bottom-right
                                -0.5f, 0.5f, 0.5f, // 10: top-right
                                -0.5f, 0.5f, -0.5f, // 11: top-left

                                // +X FACE (right, normal: +1, 0, 0)
                                0.5f, -0.5f, 0.5f, // 12: bottom-left
                                0.5f, -0.5f, -0.5f, // 13: bottom-right
                                0.5f, 0.5f, -0.5f, // 14: top-right
                                0.5f, 0.5f, 0.5f, // 15: top-left

                                // +Y FACE (top, normal: 0, +1, 0)
                                -0.5f, 0.5f, 0.5f, // 16: front-left
                                0.5f, 0.5f, 0.5f, // 17: front-right
                                0.5f, 0.5f, -0.5f, // 18: back-right
                                -0.5f, 0.5f, -0.5f, // 19: back-left

                                // -Y FACE (bottom, normal: 0, -1, 0)
                                -0.5f, -0.5f, -0.5f, // 20: back-left
                                0.5f, -0.5f, -0.5f, // 21: back-right
                                0.5f, -0.5f, 0.5f, // 22: front-right
                                -0.5f, -0.5f, 0.5f, // 23: front-left
                };

                float[] textureCoords = {
                                // +Z FACE
                                0.0f, 0.0f, // 0: bottom-left
                                1.0f, 0.0f, // 1: bottom-right
                                1.0f, 1.0f, // 2: top-right
                                0.0f, 1.0f, // 3: top-left

                                // -Z FACE
                                0.0f, 0.0f, // 4: bottom-left
                                1.0f, 0.0f, // 5: bottom-right
                                1.0f, 1.0f, // 6: top-right
                                0.0f, 1.0f, // 7: top-left

                                // -X FACE
                                0.0f, 0.0f, // 8: bottom-left
                                1.0f, 0.0f, // 9: bottom-right
                                1.0f, 1.0f, // 10: top-right
                                0.0f, 1.0f, // 11: top-left

                                // +X FACE
                                0.0f, 0.0f, // 12: bottom-left
                                1.0f, 0.0f, // 13: bottom-right
                                1.0f, 1.0f, // 14: top-right
                                0.0f, 1.0f, // 15: top-left

                                // +Y FACE
                                0.0f, 0.0f, // 16: front-left
                                1.0f, 0.0f, // 17: front-right
                                1.0f, 1.0f, // 18: back-right
                                0.0f, 1.0f, // 19: back-left

                                // -Y FACE
                                0.0f, 0.0f, // 20: back-left
                                1.0f, 0.0f, // 21: back-right
                                1.0f, 1.0f, // 22: front-right
                                0.0f, 1.0f, // 23: front-left

                };

                int[] indices = {
                                // +Z FACE
                                0, 1, 2, 2, 3, 0,

                                // -Z FACE
                                4, 5, 6, 6, 7, 4,

                                // -X FACE
                                8, 9, 10, 10, 11, 8,

                                // +X FACE
                                12, 13, 14, 14, 15, 12,

                                // +Y FACE
                                16, 17, 18, 18, 19, 16,

                                // -Y FACE
                                20, 21, 22, 22, 23, 20,

                };

                List<ModelTexture> textures = new ArrayList<>();

                for (int y = 0; y < 2; y++) {
                        for (int x = 0; x < 4; x++) {
                                textures.add(new ModelTexture(loader.loadTexture(x, y)));
                        }
                }

                RawModel model = loader.loadToVAO(vertices, indices, textureCoords);
                ModelTexture texture = new ModelTexture(loader.loadTexture(1, 0));
                TexturedModel texturedModel = new TexturedModel(model, texture);

                Entity entity = new Entity(texturedModel, new Vector3f(0, 0, -3), new Vector3f(0, 0, 0),
                                new Vector3f(1, 1, 1));

                Camera camera = new Camera();

                MasterRenderer renderer = new MasterRenderer();
                World world = new World(textures, renderer);

                while (!glfwWindowShouldClose(DisplayManager.window)) {
                        double currentTime = glfwGetTime();
                        deltaTime = (float) (currentTime - lastTime);
                        lastTime = currentTime;
                        camera.move(deltaTime);

                        world.render();

                        renderer.render(camera);
                        DisplayManager.updateDisplay();
                }

                renderer.cleanUp();
                loader.cleanUp();
                DisplayManager.closeDisplay();
        }
}