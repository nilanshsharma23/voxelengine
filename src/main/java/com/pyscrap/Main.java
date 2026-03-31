package com.pyscrap;

import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

import com.pyscrap.renderEngine.DisplayManager;

public class Main {
    public static void main(String[] args) {
        DisplayManager.createDisplay();

        while (!glfwWindowShouldClose(DisplayManager.window)) {
            DisplayManager.updateDisplay();
        }

        DisplayManager.closeDisplay();
    }
}