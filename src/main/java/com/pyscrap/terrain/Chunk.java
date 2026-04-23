package com.pyscrap.terrain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.joml.Vector3i;

import com.pyscrap.Globals;
import com.pyscrap.entities.Camera;
import com.pyscrap.models.RawModel;
import com.pyscrap.renderEngine.Loader;
import com.pyscrap.renderEngine.MasterRenderer;
import com.pyscrap.textures.ModelTexture;
import com.pyscrap.toolbox.Methods;

public class Chunk {
    List<Block> blocks = new ArrayList<Block>();
    MasterRenderer renderer;
    byte[][][] world;
    int xCoord = 0;
    int zCoord = 0;

    Camera camera;

    public Chunk(int xOffset, int zOffset, byte[][][] world, List<ModelTexture> textures, MasterRenderer renderer,
            Camera camera) {
        this.renderer = renderer;
        this.world = world;
        this.xCoord = xOffset;
        this.zCoord = zOffset;
        this.camera = camera;

        for (int x = 0; x < World.CHUNK_LENGTH; x++) {
            for (int y = 0; y < World.CHUNK_HEIGHT; y++) {
                for (int z = 0; z < World.CHUNK_LENGTH; z++) {
                    int xOffsetted = x + (xOffset * World.CHUNK_LENGTH);
                    int zOffsetted = z + (zOffset * World.CHUNK_LENGTH);

                    Vector3i position = new Vector3i(xOffsetted, y, zOffsetted);

                    if (BlockData.getBlockType(world, position) == BlockType.AIR) {
                        continue;
                    }

                    RawModel model = getRawModel(position);

                    if (model == null) {
                        continue;
                    }

                    blocks.add(new Block(textures, model, position, BlockData.getBlockType(world, position)));
                }
            }
        }
    }

    RawModel getRawModel(Vector3i position) {
        boolean showPosZ = BlockData.getBlockType(world,
                new Vector3i(position.x, position.y, position.z + 1)) == BlockType.AIR;
        boolean showNegZ = BlockData.getBlockType(world,
                new Vector3i(position.x, position.y, position.z - 1)) == BlockType.AIR;
        boolean showNegX = BlockData.getBlockType(world,
                new Vector3i(position.x - 1, position.y, position.z)) == BlockType.AIR;
        boolean showPosX = BlockData.getBlockType(world,
                new Vector3i(position.x + 1, position.y, position.z)) == BlockType.AIR;
        boolean showPosY = BlockData.getBlockType(world,
                new Vector3i(position.x, position.y + 1, position.z)) == BlockType.AIR;
        boolean showNegY = BlockData.getBlockType(world,
                new Vector3i(position.x, position.y - 1, position.z)) == BlockType.AIR;

        if (!showPosZ && !showNegZ && !showNegX && !showPosX && !showPosY && !showNegY) {
            return null;
        }

        List<Float> vertexList = new ArrayList<>();
        List<Float> texList = new ArrayList<>();
        List<Integer> indexList = new ArrayList<>();

        int quadOffset = 0;

        if (showPosZ) {
            vertexList.addAll(Arrays.asList(BlockData.positiveZVertices));
            texList.addAll(Arrays.asList(BlockData.positiveZTextureCoords));
            for (int index : BlockData.indices) {
                indexList.add(index + quadOffset);
            }
            quadOffset += 4;
        }

        if (showNegZ) {
            vertexList.addAll(Arrays.asList(BlockData.negativeZVertices));
            texList.addAll(Arrays.asList(BlockData.negativeZTextureCoords));
            for (int index : BlockData.indices) {
                indexList.add(index + quadOffset);
            }
            quadOffset += 4;
        }

        if (showNegX) {
            vertexList.addAll(Arrays.asList(BlockData.negativeXVertices));
            texList.addAll(Arrays.asList(BlockData.negativeXTextureCoords));
            for (int index : BlockData.indices) {
                indexList.add(index + quadOffset);
            }
            quadOffset += 4;
        }

        if (showPosX) {
            vertexList.addAll(Arrays.asList(BlockData.postiveXVertices));
            texList.addAll(Arrays.asList(BlockData.positiveXTextureCoords));
            for (int index : BlockData.indices) {
                indexList.add(index + quadOffset);
            }
            quadOffset += 4;
        }

        if (showPosY) {
            vertexList.addAll(Arrays.asList(BlockData.positiveYVertices));
            texList.addAll(Arrays.asList(BlockData.positiveYTextureCoords));

            for (int index : BlockData.indices) {
                indexList.add(index + quadOffset);
            }

            quadOffset += 4;
        }

        if (showNegY) {
            vertexList.addAll(Arrays.asList(BlockData.negativeYVertices));
            texList.addAll(Arrays.asList(BlockData.negativeYTextureCoords));
            for (int index : BlockData.indices) {
                indexList.add(index + quadOffset);
            }
            quadOffset += 4;
        }

        Loader loader = new Loader();
        return loader.loadToVAO(Methods.FloatListToArray(vertexList),
                Methods.IntListToArray(indexList),
                Methods.FloatListToArray(texList));
    }

    public void render() {
        int offset = 1;

        Boolean xCoordMatch = (xCoord - offset <= Globals.CHUNK_COORD_X) && (Globals.CHUNK_COORD_X <= xCoord + offset);
        Boolean zCoordMatch = (zCoord - offset <= Globals.CHUNK_COORD_Z) && (Globals.CHUNK_COORD_Z <= zCoord + offset);

        if (xCoordMatch && zCoordMatch) {
            for (int i = 0; i < blocks.size(); i++) {
                renderer.processEntity(blocks.get(i));
            }
        }
    }
}
