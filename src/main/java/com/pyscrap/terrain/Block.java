package com.pyscrap.terrain;

import java.util.List;

import org.joml.Vector3f;
import org.joml.Vector3i;

import com.pyscrap.entities.Entity;
import com.pyscrap.models.RawModel;
import com.pyscrap.models.TexturedModel;
import com.pyscrap.textures.ModelTexture;

public class Block extends Entity {

    public Block(List<ModelTexture> textures, RawModel rawModel, Vector3i position, byte blockType) {
        super(new TexturedModel(rawModel, textures.get(blockType - 1)), new Vector3f(position),
                new Vector3f(0, 0, 0),
                new Vector3f(1, 1, 1), new Vector3f(1, 1, 1));
    }
}
