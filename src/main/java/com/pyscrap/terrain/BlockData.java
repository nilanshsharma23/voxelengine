package com.pyscrap.terrain;

import org.joml.Vector3i;

public class BlockData {
        public static int[] indices = { // +Z FACE
                        0, 1, 2, 2, 3, 0, };

        public static Float[] positiveZVertices = {
                        // +Z FACE (front, normal: 0, 0, +1)
                        -0.5f, -0.5f, 0.5f, // 0: bottom-left
                        0.5f, -0.5f, 0.5f, // 1: bottom-right
                        0.5f, 0.5f, 0.5f, // 2: top-right
                        -0.5f, 0.5f, 0.5f, // 3: top-left
        };

        public static Float[] negativeZVertices = {
                        // -Z FACE (back, normal: 0, 0, -1)
                        0.5f, -0.5f, -0.5f, // 4: bottom-left
                        -0.5f, -0.5f, -0.5f, // 5: bottom-right
                        -0.5f, 0.5f, -0.5f, // 6: top-right
                        0.5f, 0.5f, -0.5f, // 7: top-left
        };

        public static Float[] positiveYVertices = {
                        // +Y FACE (top, normal: 0, +1, 0)
                        -0.5f, 0.5f, 0.5f, // 16: front-left
                        0.5f, 0.5f, 0.5f, // 17: front-right
                        0.5f, 0.5f, -0.5f, // 18: back-right
                        -0.5f, 0.5f, -0.5f, // 19: back-left
        };

        public static Float[] negativeYVertices = {
                        // -Y FACE (bottom, normal: 0, -1, 0)
                        -0.5f, -0.5f, -0.5f, // 20: back-left
                        0.5f, -0.5f, -0.5f, // 21: back-right
                        0.5f, -0.5f, 0.5f, // 22: front-right
                        -0.5f, -0.5f, 0.5f, // 23: front-left
        };

        public static Float[] postiveXVertices = {
                        // +X FACE (right, normal: +1, 0, 0)
                        0.5f, -0.5f, 0.5f, // 12: bottom-left
                        0.5f, -0.5f, -0.5f, // 13: bottom-right
                        0.5f, 0.5f, -0.5f, // 14: top-right
                        0.5f, 0.5f, 0.5f, // 15: top-left
        };

        public static Float[] negativeXVertices = {
                        -0.5f, -0.5f, -0.5f, // 8: bottom-left
                        -0.5f, -0.5f, 0.5f, // 9: bottom-right
                        -0.5f, 0.5f, 0.5f, // 10: top-right
                        -0.5f, 0.5f, -0.5f, // 11: top-left
        };

        public static Float[] positiveZTextureCoords = {
                        // +Z FACE
                        0.0f, 0.0f, // 0: bottom-left
                        1.0f, 0.0f, // 1: bottom-right
                        1.0f, 1.0f, // 2: top-right
                        0.0f, 1.0f, // 3: top-left
        };

        public static Float[] negativeZTextureCoords = {

                        // -Z FACE
                        0.0f, 0.0f, // 4: bottom-left
                        1.0f, 0.0f, // 5: bottom-right
                        1.0f, 1.0f, // 6: top-right
                        0.0f, 1.0f, // 7: top-left
        };

        public static Float[] negativeXTextureCoords = {

                        0.0f, 0.0f, // 8: bottom-left
                        1.0f, 0.0f, // 9: bottom-right
                        1.0f, 1.0f, // 10: top-right
                        0.0f, 1.0f, // 11: top-left
        };

        public static Float[] positiveXTextureCoords = {
                        0.0f, 0.0f, // 12: bottom-left
                        1.0f, 0.0f, // 13: bottom-right
                        1.0f, 1.0f, // 14: top-right
                        0.0f, 1.0f, // 15: top-left
        };

        public static Float[] positiveYTextureCoords = {
                        0.0f, 0.0f, // 16: front-left
                        1.0f, 0.0f, // 17: front-right
                        1.0f, 1.0f, // 18: back-right
                        0.0f, 1.0f, // 19: back-left
        };

        public static Float[] negativeYTextureCoords = {
                        0.0f, 0.0f, // 20: back-left
                        1.0f, 0.0f, // 21: back-right
                        1.0f, 1.0f, // 22: front-right
                        0.0f, 1.0f, // 23: front-left
        };

        public static byte getBlockType(byte[][][] world, Vector3i position) {
                try {
                        return world[position.x][position.y][position.z];
                } catch (Exception e) {
                        return BlockType.AIR;
                }
        }
}
