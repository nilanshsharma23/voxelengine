package com.pyscrap;

import static org.lwjgl.glfw.GLFW.glfwGetTime;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

import java.util.ArrayList;
import java.util.List;

import com.pyscrap.entities.Camera;
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

                List<ModelTexture> textures = new ArrayList<>();

                for (int y = 0; y < 2; y++) {
                        for (int x = 0; x < 4; x++) {
                                textures.add(new ModelTexture(loader.loadTexture(x, y)));
                        }
                }

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