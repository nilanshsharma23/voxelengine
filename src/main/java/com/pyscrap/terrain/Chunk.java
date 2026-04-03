package com.pyscrap.terrain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.joml.Vector3i;

import com.pyscrap.models.RawModel;
import com.pyscrap.renderEngine.Loader;
import com.pyscrap.renderEngine.MasterRenderer;
import com.pyscrap.textures.ModelTexture;
import com.pyscrap.toolbox.Methods;

public class Chunk {
    List<Block> blocks = new ArrayList<Block>();
    MasterRenderer renderer;
    byte[][][] world;

    public Chunk(int xOffset, int zOffset, byte[][][] world, List<ModelTexture> textures, MasterRenderer renderer) {
        this.renderer = renderer;
        this.world = world;

        for (int x = 0; x < World.CHUNK_LENGTH; x++) {
            for (int z = 0; z < World.CHUNK_HEIGHT; z++) {
                for (int y = 0; y < World.CHUNK_LENGTH; y++) {
                    int xOffsetted = x + (xOffset * World.CHUNK_LENGTH);
                    int zOffsetted = z + (zOffset * World.CHUNK_LENGTH);

                    Vector3i position = new Vector3i(xOffsetted, y, zOffsetted);

                    if (BlockData.getBlockType(world, position) == BlockType.AIR) {
                        return;
                    }

                    blocks.add(new Block(textures, getRawModel(position), position,
                            BlockData.getBlockType(world, position)));
                }
            }
        }
    }

    RawModel getRawModel(Vector3i position) {
        Loader loader = new Loader();
        return loader.loadToVAO(getVertices(position), BlockData.indices,
                getTextureCoords(position));
    }

    float[] getVertices(Vector3i position) {
        List<Float> output = new ArrayList<>();

        if (BlockData.getBlockType(world, new Vector3i(position.x, position.y, position.z + 1)) == BlockType.AIR) {
            output.addAll(Arrays.asList(BlockData.positiveZVertices));
        }

        if (BlockData.getBlockType(world, new Vector3i(position.x, position.y, position.z - 1)) == BlockType.AIR) {
            output.addAll(Arrays.asList(BlockData.negativeZVertices));
        }

        if (BlockData.getBlockType(world, new Vector3i(position.x - 1, position.y, position.z)) == BlockType.AIR) {
            output.addAll(Arrays.asList(BlockData.negativeXVertices));
        }

        if (BlockData.getBlockType(world, new Vector3i(position.x + 1, position.y, position.z)) == BlockType.AIR) {
            output.addAll(Arrays.asList(BlockData.postiveXVertices));
        }

        if (BlockData.getBlockType(world, new Vector3i(position.x, position.y + 1, position.z)) == BlockType.AIR) {
            output.addAll(Arrays.asList(BlockData.positiveYVertices));
        }

        if (BlockData.getBlockType(world, new Vector3i(position.x, position.y - 1, position.z)) == BlockType.AIR) {
            output.addAll(Arrays.asList(BlockData.negativeYVertices));
        }

        return Methods.FloatListToArray(output);
    }

    float[] getTextureCoords(Vector3i position) {
        List<Float> output = new ArrayList<>();

        if (BlockData.getBlockType(world, new Vector3i(position.x, position.y, position.z + 1)) == BlockType.AIR) {
            output.addAll(Arrays.asList(BlockData.positiveZTextureCoords));
        }

        if (BlockData.getBlockType(world, new Vector3i(position.x, position.y, position.z - 1)) == BlockType.AIR) {
            output.addAll(Arrays.asList(BlockData.negativeZTextureCoords));
        }

        if (BlockData.getBlockType(world, new Vector3i(position.x - 1, position.y, position.z)) == BlockType.AIR) {
            output.addAll(Arrays.asList(BlockData.negativeXTextureCoords));
        }

        if (BlockData.getBlockType(world, new Vector3i(position.x + 1, position.y, position.z)) == BlockType.AIR) {
            output.addAll(Arrays.asList(BlockData.positiveXTextureCoords));
        }

        if (BlockData.getBlockType(world, new Vector3i(position.x, position.y + 1, position.z)) == BlockType.AIR) {
            output.addAll(Arrays.asList(BlockData.positiveYTextureCoords));
        }

        if (BlockData.getBlockType(world, new Vector3i(position.x, position.y - 1, position.z)) == BlockType.AIR) {
            output.addAll(Arrays.asList(BlockData.negativeYTextureCoords));
        }

        return Methods.FloatListToArray(output);
    }

    public void render() {
        for (int i = 0; i < blocks.size(); i++) {
            renderer.processEntity(blocks.get(i));
        }
    }
}
