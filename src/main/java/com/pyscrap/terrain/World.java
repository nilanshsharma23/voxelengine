package com.pyscrap.terrain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.pyscrap.renderEngine.MasterRenderer;
import com.pyscrap.textures.ModelTexture;

public class World {
    public static int CHUNK_LENGTH = 16;
    public static int CHUNK_HEIGHT = 16;
    public static int NUMBER_OF_CHUNKS_X = 16;
    public static int NUMBER_OF_CHUNKS_Z = 16;

    List<Chunk> chunks = new ArrayList<>();

    public World(List<ModelTexture> textures, MasterRenderer renderer) {
        Random random = new Random();
        NoiseGenerator noiseGenerator = new NoiseGenerator();
        noiseGenerator.setSeed(random.nextInt());

        byte[][][] blocks = new byte[NUMBER_OF_CHUNKS_X * CHUNK_LENGTH][CHUNK_HEIGHT][NUMBER_OF_CHUNKS_Z
                * CHUNK_LENGTH];

        for (int x = 0; x < CHUNK_LENGTH * NUMBER_OF_CHUNKS_X; x++) {
            for (int z = 0; z < CHUNK_LENGTH * NUMBER_OF_CHUNKS_Z; z++) {
                for (int y = 0; y < CHUNK_HEIGHT; y++) {
                    double noise = noiseGenerator.noise(x, y);
                    if (y < noise) {
                        blocks[x][y][z] = BlockType.DIRT;
                    }
                }
            }
        }

        for (int x = 0; x < NUMBER_OF_CHUNKS_X; x++) {
            for (int z = 0; z < NUMBER_OF_CHUNKS_Z; z++) {
                Chunk chunk = new Chunk(x, z, blocks, textures, renderer);

                chunks.add(chunk);
            }
        }
    }

    public void render() {
        for (int i = 0; i < chunks.size(); i++) {
            chunks.get(i).render();
        }
    }
}
